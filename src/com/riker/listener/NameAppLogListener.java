package com.riker.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.riker.web.NameControl;


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
public class NameAppLogListener implements ServletContextListener {


	/**
	 * starting an instance of the log4j logger
	 */
	public static final Logger log = Logger.getLogger(NameControl.class);

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
		System.out.println("listener context Initialized2");
		//load and config logger

		// initialize log4j here


		ServletContext context = event.getServletContext();
		String log4jConfigPath = context.getInitParameter("log4j-config-location") + "";
		log4jConfigPath.trim();

		if (log4jConfigPath.length() > 0){
				
			//if a path to a file exists the listener will search for that configuration
			
			System.out.println("the log4jConfigFile is: " + log4jConfigPath );

			String fullPath = context.getRealPath("/") + log4jConfigPath;

			System.out.println("the full path is: ***" + fullPath +"**");

			PropertyConfigurator.configure(fullPath);

			log.info("NameAppLogListener has initalized the logger and its running");
		}else{
			//if the path is blank or null it will use the basic config for log4j
			System.out.println("the log4j path is blank please check the web.xml"
					+ " is: ***" + log4jConfigPath +"** basic configurator used");
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
		System.out.println("listener context Destroyed");
		//do nothing let the garbage collector take it
	}


}
