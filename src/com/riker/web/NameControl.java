package com.riker.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

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
	public static final String RESULTS_PATH = "WEB-INF/include/results.jsp";
	
	/**
	 * Variable that will hold the location of the target jsp
	 */
	public static String resultsPath = null;

	/**
	 * This method over rides the init calls the configure method to 
	 * load properties
	 */
	public void init(ServletConfig config) throws ServletException {
		log.info("init name control called");
		configApp();
		log.info("configure method called at init");
		
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
		//ConfigFileLoader cfl = new ConfigFileLoader();
		log.info("config file loader instance created");
		//cfl.setServletContext(this.getServletContext());
		log.info("context sent to config file loader");
		//cfl.setConfigFilePath(CONFIG_FILE_PATH);

		/*try {
			cfl.configData(KEY_SEPARATOR);
		} catch (IOException e) {
			log.error("An error has occured, the file isnt found or the file"
					+ " isnt readable.");
			e.printStackTrace();
		}*/
		
		//gets the property stored for the results path
		//resultsPath = cfl.getProperties().get(RESULTS_PATH);
		log.debug("results path is: " + resultsPath);
	}
}
