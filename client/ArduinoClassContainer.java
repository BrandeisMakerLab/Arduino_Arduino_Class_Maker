/** Name: Jacob Smith
 *  Email:jsmith2021@brandeis.edu
 *  Assignment: Personal Study, a simple client class to store the body, header, and keywords
 *  files of the ArduinoClass generator. This is useful for testing, as my test methods can create
 *  an ArduinoClass Container object, which can be inspected.
 *  Date: May 16, 2019
 *  Sources: 
 *  Bugs:
 */
package client;

import classGenerators.ArduinoClassCpp;
import classGenerators.ArduinoClassH;

public class ArduinoClassContainer {
	/**
	 * the Arduino library header file
	 */
	private String header;
	/**
	 * the Arduino library body file
	 */
	private String body;
	/**
	 * the Arduino library keywords file
	 */
	private String keywords;
	
	/**
	 * creates a new ArduinoClassContainer Object
	 */
	public ArduinoClassContainer(String className, String author, String organization,boolean hardCodeDate,
			String headerComments, String supportedBoards, String variables,
			String privateMethods, String publicMethods){
		
		//generate and store body file
		ArduinoClassCpp cppMaker=new ArduinoClassCpp(className, author, organization,hardCodeDate,
				headerComments, supportedBoards,variables,
				privateMethods,publicMethods);
		body=cppMaker.toString();
		
		//generate and store header file
		ArduinoClassH headerMaker=new ArduinoClassH(className, author, organization,hardCodeDate,
				headerComments, supportedBoards,variables,
				privateMethods,publicMethods);
		header=headerMaker.getHeader();
		//store keywords file
		keywords=headerMaker.getKeywords();
	}
	
	/**
	 * returns the generated body file
	 */
	public String getBody(){
		return body;
	}
	
	/**
	 * returns the generated header file
	 */
	public String getHeader(){
		return header;
	}
	
	/**
	 * returns the generated keywords file
	 */
	public String getKeywords(){
		return keywords;
	}
	
	/**
	 * returns a string representaiton of the object
	 */
	public String toString(){
		return header+"\n\n"+body+"\n\n"+keywords;
	}
}
