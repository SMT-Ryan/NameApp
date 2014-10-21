package com.riker.model;

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
	 * generates a very hard coded constant name
	 * @return the constant name
	 */
	public String getName(){
		//log.info("started the getName method");
		String n = RYAN;
		//log.info("the string is: " + n);
		return n;
	}
	

}
