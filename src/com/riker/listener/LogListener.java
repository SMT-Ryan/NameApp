package com.riker.listener;

import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;



/****************************************************************************
 * <b>Title</b>: NameAppLogListener.java <p/>
 * <b>Project</b>: NameApp <p/>
 * <b>Description: </b> This listener will load and configure the log4j logger
 * <p/>
 * <b>Copyright:</b> Copyright (c) 2014<p/>
 * <b>Company:</b> Silicon Mountain Technologies<p/>
 * @author Ryan Riker
 * @version 2.0
 * @since Oct 20, 2014<p/>
 * @updates:
 ****************************************************************************/
@WebListener
public class LogListener implements ServletContextListener {
	
	
	/**
	 * starting an instance of the log4j logger
	 */
	public static final Logger log = Logger.getLogger(LogListener.class);

	/**
	 * Initialize log4j when the application is being started
	 * 
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {

		// get servlet context
		ServletContext context = event.getServletContext();
		
		//load path to prop file
		pathToFile(context);
	
	}

	/**
	 * This method changes the file path in the web.xml file into the real 
	 * file path on the local machine.
	 * 
	 * @param context 
	 * 
	 */
	private void pathToFile(ServletContext context) {
		
		//creates an instance of properties
		Properties np = new Properties();
		np = (Properties) context.getAttribute("properties");
		
    	//gets the log4j properties file path from the properties object.
    	String configPath = np.getProperty("LogPath");
		configPath.trim();
		
		if (configPath.length() > 0){
			
			//if a path to a file exists the listener will search for that 
			//configuration

			String fullPath = context.getRealPath("/") + configPath;

			PropertyConfigurator.configure(fullPath);

			log.info("NameAppLogListener has initalized the logger and its running");
		}else{
			//if the path is blank or null it will use the basic config for log4j
			BasicConfigurator.configure();
			log.error("Log configuration failed to find a file path.  logger will"
					+ "use basic configuration.");
		}
		
	}

	/**
	 * does nothing but alerts the display to the destruction of the log listener
	 * 
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("log listener context Destroyed");
		//do nothing let the garbage collector take it
	}


}
