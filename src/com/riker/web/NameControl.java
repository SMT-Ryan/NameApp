package com.riker.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.riker.configuration.ConfigFileLoader;
import com.riker.model.ModelName;

/****************************************************************************
 * <b>Title</b>: NameControl.java <p/>
 * <b>Project</b>: NameApp <p/>
 * <b>Description: </b> This is the brains and controls the flow of the 
 * 	name app.  
 * <p/>
 * <b>Copyright:</b> Copyright (c) 2014<p/>
 * <b>Company:</b> Silicon Mountain Technologies<p/>
 * @author Ryan Riker
 * @version 2.0
 * @since Oct 20, 2014<p/>
 * @updates:
 ****************************************************************************/


public class NameControl extends HttpServlet {


	/**
	 * constant serial id for this servlet.  
	 */
	private static final long serialVersionUID = 1138L;
	
	/**
	 * constant location of config file
	 */
	private final String CONFIG_FILE_PATH = "config/NameApp.Properties";
	
	/**
	 * constant key separator for config file
	 */
	private String KEY_SEPARATOR = "=";

	/**
	 * starting an instance of the log4j logger
	 */
	public static final Logger log = Logger.getLogger(NameControl.class);

	/**
	 * String location for view path this data would like be stored
	 * in the config file.
	 */
	public static final String RESULTS_PATH = "/WEB-INF/include/results.jsp";

	/**
	 * This method over rides the init 
	 */
	public void init(ServletConfig config) throws ServletException {
	
		
	}

	/**
	 * This method takes a doPost call and calls the process method
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		processRequest(request, response);
	}

	/**
	 * this method takes a doGet call and calls the process method
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		processRequest(request, response);
	}

	/**
	 * This method is the wrapper method that serves as control for the app.
	 *   
	 * @param req the request object
	 * @param res the response object
	 * @throws IOException 
	 * @throws ServletException
	 */
	public void processRequest(HttpServletRequest req, HttpServletResponse res) 
			throws IOException, ServletException{
		
		configApp();
		
		//creates a new instance of the model, and writes over the 
		// old request object with the altered new request object
		ModelName mn = new ModelName();
		log.info("model name instance, started");
		mn.getName(req);

		//makes an instance of the view
		RequestDispatcher view = 
				req.getRequestDispatcher(RESULTS_PATH);
		//sends the request and response objects with new data to the view
		view.forward(req, res);
	}

	/**
	 * Configures the properties
	 */
	private void configApp() {
		//loads config file and sets up setters for filling data
		ConfigFileLoader cfl = new ConfigFileLoader();
		cfl.setConfigFilePath(CONFIG_FILE_PATH);

		try {
			cfl.configData(KEY_SEPARATOR);
		} catch (IOException e) {
			log.error("An error has occured, the file isnt found or the file"
					+ " isnt readable.");
			e.printStackTrace();
		}
	}
}
