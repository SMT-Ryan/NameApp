package com.riker.model;

import java.util.Properties;

import javax.servlet.ServletContext;
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
	public static final String NO_DEFAULT = "No, Default name listed in the "
			+ "properites file";

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
			// get servlet context
			ServletContext context = req.getSession().getServletContext();

			//creates an instance of properties
			Properties np = new Properties();
			np = (Properties) context.getAttribute("properties");

			//gets the log4j properties file path from the properties object.
			String defaultName = np.getProperty("DefaultName");
			defaultName.trim();

			name= defaultName;
			log.debug("default name :**" + name + "**");
			if (defaultName.length() == 0){
				name = NO_DEFAULT;
				log.debug("the string is: " + name);
			}
		}

		req.setAttribute("name", name);
		log.debug("the string name set to request objects attribue is: " + name);
	}


}
