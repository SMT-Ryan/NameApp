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
	//TODO possibly add my name as default to properties file
	public static final String RYAN = "Ryan R.";

	/**
	 * creates an instance of the logger
	 */
	static Logger log = Logger.getLogger(ModelName.class);

	/**
	 * This method generates a much less hard coded name</p>
	 * @param req the request object passed into the method by the parent. 
	 */
	public void getName(HttpServletRequest req){
		String name = req.getParameter("name") + "";
		name = name.trim();
		log.debug("Name: " + name);

		if (name.length() == 0){
			log.debug("step default name :**" + name + "**");
			name = RYAN;
			log.debug("the string is: " + name);
		}

		log.debug("step set name :**" + name + "**");
		req.setAttribute("name", name);
		log.debug("the string name set to request objects attribue is: " + name);
	}


}
