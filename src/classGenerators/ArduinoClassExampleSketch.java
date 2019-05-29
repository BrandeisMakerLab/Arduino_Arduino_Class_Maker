package classGenerators;
import enums.ArduinoClassExample;
import enums.ExampleSketch;
import parsing.ArduinoParser;

 /* Name: Jacob Smith
Date: May 12 2019
Assignment: Personal study, allows the user to create an Arduino Class sketch given necesary information
Email: jsmith2021@brandeis.edu
*/

public class ArduinoClassExampleSketch extends ArduinoClassMaster{
	
	/**
	* Loads an example class into memory with hardcoded date
	* and parses it into header comment, methods, header file
	*/
	public ArduinoClassExampleSketch(String className,String author,String organization,boolean hardCodeDate,String headerComments,String supportedBoards,String sketchMethods){
		super(className,author,organization,hardCodeDate,headerComments,supportedBoards);
		init(className,sketchMethods);
		
	}
	
	/**
	* Loads an example class into memory with automatic date
	* and parses it into header comment, methods, header file
	*/
	public ArduinoClassExampleSketch(String className,String author,String organization,String headerComments,String supportedBoards,String sketchMethods){
		super(className,author,organization,headerComments,supportedBoards);
		init(className,sketchMethods);
		
	}
	
	/**
	 * Helper method to intialize class specific methods and allow date to be hardcoded
	 */
	private void init(String className,String sketchMethods){
		arduinoClass+=super.startLibraryIncludes(null, className)+"\n";
		arduinoClass+=genVariable(className);
		arduinoClass+=generateMethods(className,sketchMethods,true);	
	}
		
	/**
	 * creates the class initialization and comment
	 * @param className the name of the class
	 * @return the object used to interface with the class
	 */
	protected String genVariable(String className) {
		String var="//The object used to interfact with the class\n";
		var+=className+" "+className.toLowerCase()+"();\n\n";
		return var;
	}
	
	/**Generates a method given the dataType, methodName, parameters (which can be blank) comment, and body*/
	protected String genMethod(String className,String []methodParts,boolean isPublic){
		String methodString="";
		methodString+="//"+methodParts[3]+"\n";//comment
		methodString+=methodParts[0];//data type
		methodString+=" "+methodParts[1]+"("+methodParts[2]+") {";//name and parameters
		methodString+=ArduinoParser.insertTabs(methodParts[4],1,false);//body
		methodString+="\n}\n\n";
		return methodString;
		
	}
	
	/**
	* Shows an example file with this class
	*/
	public static void main (String [] args){
		//create an ArduinoClassExampleSketch object using hardcoded example fields
		ArduinoClassExampleSketch template = new ArduinoClassExampleSketch(ArduinoClassExample.CLASSNAME.toString(),
			ArduinoClassExample.AUTHOR.toString(),
			ArduinoClassExample.ORGANIZATION.toString(), 
			true,
			ArduinoClassExample.HEADERCOMMENTS.toString(), 
			ArduinoClassExample.SUPPORTEDBOARDS.toString(),
			ExampleSketch.SKETCHMETHODS.toString());
		//print the generated example sketch
		System.out.println(template);
		
	}
		
}