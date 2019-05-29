package classGenerators;

import enums.ArduinoClassExample;
import enums.ExampleSketch;
import parsing.ArduinoParser;
import parsing.MiniScanner;

/* Name: Jacob Smith
Date: May 12 2019
Assignment: Personal study, allows the user to create an Arduino Class sketch given necesary information
Email: jsmith2021@brandeis.edu
*/

public class ArduinoClassExampleSketch extends ArduinoClassMaster {

	String[] publicMethodNames;

	/**
	 * Loads an example class into memory with hardcoded date and parses it into
	 * header comment, methods, header file
	 */
	public ArduinoClassExampleSketch(String className, String author, String organization, boolean hardCodeDate,
			String headerComments, String supportedBoards, String sketchMethods, String publicMethods) {
		super(className, author, organization, hardCodeDate, headerComments, supportedBoards);
		publicMethodNames = getPublicMethodNames(className, publicMethods);
		init(className, sketchMethods);

	}

	/**
	 * Helper method to intialize class specific methods and allow date to be
	 * hardcoded
	 */
	private void init(String className, String sketchMethods) {
		arduinoClass += super.startLibraryIncludes(null, className) + "\n";
		arduinoClass += genVariable(className);
		arduinoClass += generateMethods(className, sketchMethods, true);
	}

	/**
	 * 
	 * @param publicMethods
	 *            a formatted string of all the public methods
	 * @return a list of the names of all the public methods for cross checking
	 */
	private String[] getPublicMethodNames(String className, String publicMethods) {
		String methodNames = "";
		MiniScanner methodReader = new MiniScanner();
		MiniScanner nameReader = new MiniScanner();
		methodReader.prime(publicMethods, "\n\n");
		// consume first method, which could be constructor
		String method = methodReader.next("constructor/firstPublicMethod");
		nameReader.prime(method, "|");
		nameReader.next("type");
		String name="";
		if (nameReader.hasNext()) {
			name = nameReader.next()+" ";
		} 
		// if method isn't constructor add it to list of public methods
		if (!name.equals(className)&& !name.equals("")) {
			methodNames += name;
		}
		while (methodReader.hasNext()) {
			method = methodReader.next();
			nameReader.prime(method, "|");
			nameReader.next("type");
			methodNames += nameReader.next("name")+" ";
		}
		return methodNames.split(" ");
	}

	/**
	 * creates the class initialization and comment
	 * 
	 * @param className
	 *            the name of the class
	 * @return the object used to interface with the class
	 */
	protected String genVariable(String className) {
		String var = "//The object used to interfact with the class\n";
		var += className + " " + className.toLowerCase() + "();\n\n";
		return var;
	}

	/**
	 * Generates a method given the dataType, methodName, parameters (which can be
	 * blank) comment, and body
	 */
	protected String genMethod(String className, String[] methodParts, boolean isPublic) {
		String methodString = "";
		methodString += "//" + methodParts[3] + "\n";// comment
		methodString += methodParts[0];// data type
		methodString += " " + methodParts[1] + "(" + methodParts[2] + ") {";// name and parameters
		// insert the object name to public method invocations
		String methodBody = insertObject(className, methodParts[4]);
		methodString += ArduinoParser.insertTabs(methodBody, 1, false);// body
		methodString += "\n}\n\n";
		return methodString;

	}

	/**
	 * Converts global methods into class invocations
	 * 
	 * @param methodBody
	 *            the body of the sketch methods
	 * @return the body of the method with the object inserted
	 */
	protected String insertObject(String className, String methodBody) {
		String methodName;
		// iterate along public method names array
		for (int i = 0; i < publicMethodNames.length; i++) {
			// store the method name
			methodName = publicMethodNames[i];
			// replace occurences of that method name with the method name and object
			methodBody = methodBody.replaceAll(methodName, className.toLowerCase() + "." + methodName);
		}
		// remove duplicate ibject method calls, in case user entered correct method
		// call initially
		methodBody = methodBody.replaceAll(className.toLowerCase() + "." + className.toLowerCase() + ".",
				className.toLowerCase() + ".");
		return methodBody;
	}

	/**
	 * Shows an example file with this class
	 */
	public static void main(String[] args) {
		// create an ArduinoClassExampleSketch object using hardcoded example fields
		ArduinoClassExampleSketch template = new ArduinoClassExampleSketch(ArduinoClassExample.CLASSNAME.toString(),
				ArduinoClassExample.AUTHOR.toString(), ArduinoClassExample.ORGANIZATION.toString(), true,
				ArduinoClassExample.HEADERCOMMENTS.toString(), ArduinoClassExample.SUPPORTEDBOARDS.toString(),
				ExampleSketch.SKETCHMETHODS.toString().trim() + "\nresetTime();",
				ArduinoClassExample.PUBLICMETHODS.toString());
		// print the generated example sketch
		System.out.println(template);

	}

}