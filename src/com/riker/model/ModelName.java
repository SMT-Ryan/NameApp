package com.riker.model;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/****************************************************************************
 * <b>Title</b>: ModelName.java <p/>
 * <b>Project</b>: NameApp <p/>
 * <b>Description: </b> Stores my name in a variable for dynamic web generation
 * <p/>
 * <b>Copyright:</b> Copyright (c) 2014<p/>
 * <b>Company:</b> Silicon Mountain Technologies<p/>
 * @author Ryan Riker
 * @version 2.0
 * @since Oct 20, 2014<p/>
 * @updates:
 ****************************************************************************/
public class ModelName {

	/**
	 * holds my first name with last initial
	 */
	public static final String RYAN = "Ryan R.";
	/**
	 * creates an instance of the logger
	 */
	static Logger log = Logger.getLogger(ModelName.class);

	/**
	 * <b>Description: </b>This method generates a much less hard coded name</p>
	 * @param req the request object passed into the method by the parent. it is 
	 * 	returned with an added name attribute.
	 * @return req the request object with added name attribute.
	 */
	public HttpServletRequest getName(HttpServletRequest req){
		String name = null;
		name = "";

		//submitted check via in-line if statement
		String val = req.getParameter("submitted") == null ? "false" : 
			req.getParameter("submitted");

		boolean submitted = Boolean.valueOf(val);

		log.info("Submitted: " + submitted);

		//if a name is submitted and not empty use submitted name.
		if (submitted && !req.getParameter("name").equals("")){
			name = req.getParameter("name");
			log.info("the string is: " + name + " submitted: " + submitted);
		} else {
			//if no name is submitted or empty name use default
			name = RYAN;
		}

		req.setAttribute("name", name);

		log.info("the string name set to request objects attribue is: " + name);
		return req;
	}


}
