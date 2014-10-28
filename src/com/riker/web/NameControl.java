package com.riker.web;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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
	 * starting an instance of the log4j logger
	 */
	public static final Logger log = Logger.getLogger(NameControl.class);

	/**
	 * Variable that will hold the location of the target jsp
	 */
	public static String resultsPath = null;

	public static String reqLength = "this is a test";

	/**
	 * This method over rides the init calls the configure method to 
	 * load properties
	 */
	public void init(ServletConfig config) throws ServletException {
		log.info("init name control called");
	}

	/**
	 * This method takes a doPost call and calls the process method
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		log.debug("doPost call moved to process");
		processRequest(request, response);
	}

	/**
	 * this method takes a doGet call and calls the process method
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		log.debug("doGet call moved to process");
		processRequest(request, response);
	}

	/**
	 * This method is the wrapper method that serves as control for the app.
	 *   
	 * @param req the request object
	 * @param res the response object
	 * @param context2 
	 * @throws IOException 
	 * @throws ServletException
	 */
	public void processRequest(HttpServletRequest req, HttpServletResponse res) 
			throws IOException, ServletException{

		ServletContext context = req.getSession().getServletContext();

		log.info("servlet context in control " );
		Properties np = new Properties();
		np = (Properties) context.getAttribute("properties");
		log.info("properties recovered from context: " + np.size());
		resultsPath = np.getProperty("ResultsPath");

		//creates a new instance of the model, and writes over the 
		// old request object with the altered new request object
		ModelName mn = new ModelName();
		log.info("model name instance created");
		mn.getName(req);
		log.info("model complete");

		log.info("what is content length: " + req.getHeader("content-length"));
		reqLength = req.getHeader("content-length");
		req.setAttribute("reqLength", reqLength);

		log.info("the content length of the responce is: " + reqLength);

		//makes an instance of the view
		RequestDispatcher view = 
				req.getRequestDispatcher(resultsPath);
		log.info("view instance created");
		//sends the request and response objects with new data to the view
		view.forward(req, res);
		log.info("view completed");
	}
}
