package com.riker.listener;

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
	 * sting location for configuration of log4j, this data would like be stored
	 * in the config file.
	 */
	//TODO remove once reading from config file
	public static final String LOG_CONFIG_LOCATION = "log4j-properties-location";

	/**
	 * Initialize log4j when the application is being started
	 * 
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("logger config listener initialized");

		// get servlet context
		ServletContext context = event.getServletContext();
		
		//load path to prop file
		pathToFile(context);

		
	}

	/**
	 * @param context 
	 * @param event 
	 * 
	 */
	private void pathToFile(ServletContext context) {
		
		//TODO replace with properties stuff later
		String configPath = context.getInitParameter("log4j-config-location") + "";
		configPath.trim();
		
		if (configPath.length() > 0){
			
			//if a path to a file exists the listener will search for that 
			//configuration
			
			System.out.println("the Logger config file path is: **" + 
					configPath + "**");

			String fullPath = context.getRealPath("/") + configPath;

			System.out.println("the full Logger config file  path is: **" + 
					fullPath +"**");

			PropertyConfigurator.configure(fullPath);

			log.info("NameAppLogListener has initalized the logger and its running");
		}else{
			//if the path is blank or null it will use the basic config for log4j
			System.out.println("the log4j path is blank please check the web.xml"
					+ " is: ***" + configPath +"** basic configurator used");
			BasicConfigurator.configure();
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
