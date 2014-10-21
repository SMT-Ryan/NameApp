package com.riker.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

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
	 * starting an instance of the log4j logger
	 */
	public static final Logger log = Logger.getLogger(NameControl.class);


	/**
	 * This method over rides the init method to load and configure the 
	 * log4j logger, using data from the web.xml
	 */
	public void init(ServletConfig config) throws ServletException {

		String log4jLocation = config.getInitParameter("log4j-properties-location");

		ServletContext sc = config.getServletContext();

		if (log4jLocation == null) {
			BasicConfigurator.configure();
		} else {
			String webAppPath = sc.getRealPath("/");
			String log4jProp = webAppPath + log4jLocation;
			File file = new File(log4jProp);
			if (file.exists()) {
				PropertyConfigurator.configure(log4jProp);
			} else {
				BasicConfigurator.configure();
			}
		}
		super.init(config);
		log.info("the logger is configured and running");
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
		req = mn.getName(req);

		//makes an instance of the view
		RequestDispatcher view = 
				req.getRequestDispatcher("/WEB-INF/include/results.jsp");
		//sends the request and response objects with new data to the view
		view.forward(req, res);
	}
}
