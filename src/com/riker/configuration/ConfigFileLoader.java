package com.riker.configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/****************************************************************************
 * <b>Title</b>: ConfigFileLoader.java <p/>
 * <b>Project</b>: Mountain Spider <p/>
 * <b>Description: </b> This method will connect with a local file and load 
 * configuration data into the parent class.
 * <p/>
 * <b>Copyright:</b> Copyright (c) 2014<p/>
 * <b>Company:</b> Silicon Mountain Technologies<p/>
 * @author Ryan Riker
 * @version 2.0
 * @since Oct 8, 2014<p/>
 * @updates:
 ****************************************************************************/
public class ConfigFileLoader {

	Map<String, String> properties = new HashMap<String, String>();
	String confilFilePath = null;

	/**
	 * This constructor makes an instance of the ConfigFileLoader class.
	 * @param mg 
	 */
	public ConfigFileLoader() {

	}

	/**
	 * Locates the configuration file in the scripts folder, and loads the 
	 * file as a hash map with =' separating key from value.
	 * 
	 * @throws IOException if the config file can not be read, throws a
	 * file not found exception if the config file can not be opened.
	 */
	public void configData(String keySeparator) throws IOException{


		int separator = 0;

		BufferedReader br = new BufferedReader(
				new FileReader(confilFilePath));
		String line = br.readLine();

		//iterate over each line in the config file and load the has map
		//with keys from the left side of the =' and values from the right 
		//side.
		while (line != null) {
			separator = line.indexOf(keySeparator);
			if (separator != 0 && separator != -1){
				//filling the property map
				properties.put(line.substring(0, separator), 
						line.substring(separator + 2));
			}
			line = br.readLine();
		}
		br.close();
	}

	/**
	 * Returns the hash map of keys and values loaded from the file loader.
	 * 
	 * @return a hash map
	 */
	public Map<String, String> getProperties() {
		return properties;
	}

	/**
	 * sets the hash map of keys and values
	 * 
	 * @param proprties the altered hash map
	 */
	public void setProperties(Map<String, String> proprties) {
		this.properties = proprties;
	}

	/**
	 * allows the parent class to set the location of the config file
	 * 
	 * @param CONFIG_FILE_PATH
	 */
	public void setConfigFilePath(String CONFIG_FILE_PATH) {
		confilFilePath = CONFIG_FILE_PATH;
	}
}
