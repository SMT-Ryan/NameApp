package com.riker.listener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

/****************************************************************************
 * <b>Title</b>: NameAppLogListener.java <p/>
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
	
	Properties prop = new Properties();
	static String configFilePath = null;

    /**
     * Default constructor. 
     */
    public ConfigListener() {
        System.out.println("config listner is constructed");
      
        
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  { 
    	System.out.println("config listner initialized call");
		ServletContext context = event.getServletContext();
		String configPath = context.getInitParameter("config-location") + "";
		configPath.trim();
		
		
		if (configPath.length() > 0){
			
			//if a path to a file exists the listener will search for that 
			//configuration
			
			//TODO remove some of these debugging messages
			System.out.println("the file loader config file path is: **" + 
					configPath + "**");

			String fullPath = context.getRealPath("/") + configPath;

			System.out.println("the file loader config file  path is: **" + 
					fullPath +"**");

			PropertyConfigurator.configure(fullPath);

			System.out.println("NameAppLogListener file loader config has "
					+ "initalized and is running");
		}else{
			//if the path is blank or null it will use the basic config for log4j
			System.out.println("the config path is blank please check the web.xml"
					+ " is: ***" + configPath +"** basic configurator used");
			BasicConfigurator.configure();
		}
		
		
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	System.out.println("config listner destroyed");
    }
	
	/**
	 * Locates the configuration file in the file system, and loads the 
	 * file as a properties object with dynamically set separating key
	 * (normally =) from value.
	 * 
	 * @throws IOException if the config file can not be read, throws a
	 * file not found exception if the config file can not be opened.
	 */
	public void configData(String keySeparator) throws IOException{


		int separator = 0;
		
		//TODO get real path to confilFilePath
		
		BufferedReader br = new BufferedReader(
				new FileReader(configFilePath));
		String line = br.readLine();

		//iterate over each line in the config file and load the has map
		//with keys from the left side of the =' and values from the right 
		//side.
		while (line != null) {
			separator = line.indexOf(keySeparator);
			if (separator != 0 && separator != -1){
				//filling the property map
				prop.put(line.substring(0, separator), 
						line.substring(separator + 1));
			}
			line = br.readLine();
		}
		br.close();
	}


}
