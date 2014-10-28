package com.riker.listener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/****************************************************************************
 * <b>Title</b>: ConfigListener.java <p/>
 * <b>Project</b>: NameApp <p/>
 * <b>Description: </b>This class searches for config file based on the path in 
 * 		the xml. loads a properties object with data stored in file
 * <p/>
 * <b>Copyright:</b> Copyright (c) 2014<p/>
 * <b>Company:</b> Silicon Mountain Technologies<p/>
 * @author Ryan Riker
 * @version 2.0
 * @since Oct 20, 2014<p/>
 * @updates:
 ****************************************************************************/
@WebListener
public class ConfigListener implements ServletContextListener {

	//the soon to be filled properties object
	Properties prop = new Properties();	
	static String configFilePath = null;
	static String fullPath = null;
	static String separator = null;

	/**
	 * Blank Path Error message for web app.  would normally be found in a message
	 * class or constant class.
	 */
	private static final String BLANK_PATH_ERROR = "the config path is blank please check the web.xml"
			+ " is: **" + configFilePath +"** configuration not possible";

	/**
	 * IO error message for the web app.   would normally be found in a message
	 * class or constant class.
	 */
	private static final String APP_CANT_BE_CONFIGURED_IO = "App can not be "
			+ "configured, please check that the web.xml, the apps .properties "
			+ "file, and both files locations are correct.";

	/**
	 * Default constructor. 
	 */
	public ConfigListener() {
	}

	/**
	 * This method gets the servlet context and controls the configuration process
	 * 
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) { 

		//gets context
		ServletContext context = event.getServletContext();

		//gets params from web.xml
		getConfigParam(context);

		//changes the internal paths into real paths
		pathToFile(context);

		//loads the content of the config file into a property object
		try {
			configData(separator);
		} catch (IOException e) {
			System.out.println(APP_CANT_BE_CONFIGURED_IO);
			e.printStackTrace();
		}

		//places the properties into context
		context.setAttribute("properties", prop);

	}

	/**
	 * This method changes the file path in the web.xml file into the real 
	 * file path on the local machine.
	 * @param context
	 */
	private void pathToFile(ServletContext context) {

		if (configFilePath.length() > 0){

			//if a path to a file exists the listener will search for that 
			//configuration
			StringBuilder sb = new StringBuilder();

			sb.append(context.getRealPath("/"));
			sb.append(configFilePath);

			fullPath = sb.toString();

			sb.setLength(0);
		}else{
			//if the path is blank or null configuration can not be completed.
			System.out.println(BLANK_PATH_ERROR);
		}

	}

	/**
	 * This method gets the configuration parameters from the web.xml file
	 * @param context 
	 * 
	 */
	private void getConfigParam(ServletContext context) {

		configFilePath = context.getInitParameter("config-location") + "";
		configFilePath.trim();
		separator = context.getInitParameter("config-separator") + "";
		configFilePath.trim();

	}

	/**
	 * This method destroys the listener
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0)  { 
	}

	/**
	 * Locates the configuration file in the file system, and loads the 
	 * file as a properties object with dynamically set separating key
	 * from value.
	 * 
	 * @throws IOException if the config file can not be read, throws a
	 * file not found exception if the config file can not be opened.
	 */
	private void configData(String keySeparator) throws IOException{

		int delimitDigit = 0;
		String key = null;
		String value = null;
		StringBuilder sb = new StringBuilder();

		BufferedReader br = new BufferedReader(
				new FileReader(fullPath));
		String line = br.readLine();

		//iterate over each line in the config file and load the property object
		//with keys from the config file

		while (line != null) {
			delimitDigit = line.indexOf(keySeparator);
			if (delimitDigit != 0 && delimitDigit != -1){
				//filling the property map

				//Concatenate string with blank to avoid null
				sb.append(line.substring(0, delimitDigit));
				sb.append("");

				//store non-null in var
				key = sb.toString();
				//trim away white space
				key.trim();

				//clear the string builder
				sb.setLength(0);

				//Concatenate string with blank to avoid null
				sb.append(line.substring(delimitDigit + 1));
				sb.append("");

				//store non-null in var
				value = sb.toString();
				//trim away white space
				value.trim();

				//clear the string builder
				sb.setLength(0);

				//store the neatly trimmed and non-nulls in properties object
				prop.put(key, value);

			}
			line = br.readLine();
		}
		br.close();
	}


}
