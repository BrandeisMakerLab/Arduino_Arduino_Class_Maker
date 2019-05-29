/**Name: Jacob Smith
  *Email:jsmith2021@brandeis.edu 
  *Date: May 21, 2019
  *Assignment:	Personal Study, Loads an Arduino Sketch to convert it to an arduino class
  *This would support recommended workflow
  *Bugs:
  *Sources:https://stackoverflow.com/questions/15633228/how-to-remove-all-white-spaces-in-java/36444332
  * https://docs.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html#quote(java.lang.String)
  * https://stackoverflow.com/questions/17462146/java-patternsyntaxexception-illegal-repetition-on-string-substitution
  */
package files;

import client.ArduinoClassContainer;
import parsing.ArduinoParser;
import parsing.MiniScanner;
import parsing.ParsedMethod;
import parsing.VariableParser;

public class SketchParser {
	private String sketchMethods;
	private String headerComment;
	private String libraries;
	private String variables;
	private String publicMethods;
	private String privateMethods;
	
	/**
	 * creates a new sketchParser object
	 * @param contents a string representing the sketch file to convert
	 */
	public SketchParser(String contents) {
		//replace rs with newlines
		contents=contents.replaceAll("\r\n", "\n");
		//replace the newLine bracket style with inline
		//can't be done later because the scanner would read the bracket and header as different lines
		contents=SketchParser.replaceAllSimple(contents,")\n{","){");
		MiniScanner scanner=new MiniScanner();
		scanner.prime(contents,"\n");
		libraries="";
		variables="";
		String temp;
		String comment;
		String unsortedMethods="";
		sketchMethods="";
		headerComment="";
		while(scanner.hasNext()) {
			//get next line, and make sure it doens't have a newline character
			temp=scanner.next().replaceAll("\n+", "");
			//look for comment
			if(temp.contains("//")) {
				//remove comment characters and add to comment
				comment=temp.substring(2, temp.length());
				//update the temp with the next input
				temp=scanner.next().replaceAll("\n", "");
			//otherwise reset the comment string
			}else {
				comment="";
			}
			
			//look for start of header comment
			if(temp.contains("/*")) {
				parseHeaderComment(temp,scanner);
			//look for library	
			} else if(temp.contains("#include")) {
				libraries+=comment.trim()+"|"+temp.trim();
			//check for sketch methods
			}else if (temp.contains("void setup()") |temp.contains("void loop()")) {
				sketchMethods+=consumeAndFormatMethod(comment+"\n"+temp,scanner);
			//look for method, second clause is to rule out array declaration
			}else if (temp.contains("{") && !temp.contains(";")) {
				unsortedMethods+=consumeAndFormatMethod(comment+"\n"+temp,scanner);
			//do nothing if character is a newline
			}else if (temp.equals("\r")|temp.equals("")) {
			//assume whatever is left is a variable because there are hard to stop
			}else {
				//use variableParser class to reformat the variable
				VariableParser p=new VariableParser(comment,temp);
				variables+=p.toString();
			}	
		}
		
		variables=variables.replaceAll("\r","");
		variables=variables.replaceAll(";", "");
		variables=variables.replaceAll("\r\r\r", "");
		libraries=libraries.replaceAll("\r", "");
		
		//divide methods into public and private based on whether reffered to in setup and loop
		//this has to be done after circular evaluation because the setup and loop methods need to be parsed first
		MiniScanner methodReader=new MiniScanner();
		MiniScanner nameReader=new MiniScanner();
		String method;
		publicMethods="";
		privateMethods="";
		String name;
		methodReader.prime(unsortedMethods,"\n\n");
		while(methodReader.hasNext()) {
			method=methodReader.next();
			nameReader.prime(method,"|");
			//ignore the data type
			nameReader.next("Data Type");
			name=nameReader.next("methodName");
			if(sketchMethods.contains(name)) {
				publicMethods+=method+"\n\n";
			}else {
				privateMethods+=method+"\n\n";
			}
			
		}
		//add newline for constructor later, duck tape
		publicMethods="\n"+publicMethods;
		
	}
	
	/**
	 * Parses the header comment
	 * @param temp the string containing the first 
	 * @param scanner the Scanner object that iterates over the class
	 */
	private void parseHeaderComment(String temp,MiniScanner scanner) {
		headerComment="";
		temp=ArduinoParser.removeSpecialChars(temp);
		//keep adding the comment until the temp file is contained
		while(!temp.contains("*/")) {
			headerComment+=temp+"\n";
			temp=scanner.next().replaceAll("\n", "");
		}
		//add the last comment
		temp=ArduinoParser.removeSpecialChars(temp);
		headerComment+=temp;
		//remove formatting characters from header comment
		headerComment=ArduinoParser.removeWhiteSpace(headerComment);
	}
	
	/**
	 * Parses a method from sketch String and returns formatted method string
	 * @param temp the first line of the method
	 * @param scanner the MiniScanner object that iterates over the sketch
	 * @return a String representation of the method formatted for Arduino Class
	 * Generator
	 */
	private static String consumeAndFormatMethod(String temp, MiniScanner scanner) {
		//get the method into a string
		String methodBody=consumeMethod(temp,scanner);
		//format the method to be easily parsable
		return formatMethod(methodBody);
	}
	
	/**
	 * Returns the method iterated over
	 * @param temp the string containing the line
	 * @param scanner the scanner which iterates over the sketch
	 * @return the method represented as a string
	 */
	private static String consumeMethod(String temp,MiniScanner scanner) {
		//a string of methods, used here as return and also to search for brackets
		String method=temp;
		//loop as long as the closing index isn't found yet
		while(ArduinoParser.getClosingIndex(method, 0)==-1) {
			//get a new string to search
			temp=scanner.next();
			//add the temp string to the method string
			method+="\n"+temp;
		}
		//add last line
		temp=temp.replaceAll("\r","");
		//duck tape fix for extra bracket
		temp=temp.replaceAll("}", "");
		method+=temp;
		return method;
	}
	
	/**
	 * 
	 * @param methodString a string representation of the method
	 * @return a formatted version of the method suitable for Arduino class generator
	 */
	private static String formatMethod(String methodString) {
		//create a parsed method object to format the method string
		ParsedMethod p=new ParsedMethod(methodString);
		//retunr the tostring of parsedMethod object
		return p.toString();
	}
	
	
	/**
	 * Reads a sketch from computer memory, and parses it into
	 * the necessary fields to generate an arduino class 
	 * @param args not used
	 */
	public static void main(String[] args) {
	
		System.out.println("Example of SketchParser Class");
		System.out.println("This program will load an Arduino Sketch from memory");
		System.out.println("and parse the sketch into relevant fields for a library");

		System.out.println("Reading the file");
		ScriptEditor helper = new ScriptEditor("Morse.ino");
		
		System.out.println("Getting Contents");
		String contents = helper.toString();
	
		SketchParser parser=new SketchParser(contents);
		System.out.println(parser);
		//temp
		ArduinoClassContainer cont=parser.getContainer("ESPServer",false);
		System.out.println("BODY FILE\n"+cont.getBody());
		System.out.println("HEADER FILE\n"+cont.getHeader());
		System.out.println("KEYWORDS FILE\n"+cont.getKeywords());
	}
	
	/**
	 * @return a string representation of the class
	 */
	public String toString() {
		return "\nHEADER: "+headerComment
				+"\nLIBRARIES: "+libraries
				+"\nSKETCHMETHODS: "+sketchMethods
				+"\nPUBLICMETHODS: "+publicMethods
				+"\nPRIVATEMETHODS: "+privateMethods
				+"\nVARIABLES: "+variables;
	}
	
	/**
	 * used to generate arduino files
	 * @return an ArduinoClassCounter capable of generating resulting methods
	 */
	public ArduinoClassContainer getContainer(String className,boolean hardCodeDate) {
		return new ArduinoClassContainer(className, null, null,hardCodeDate,
				headerComment, "ALL", variables,
				privateMethods, publicMethods,sketchMethods);
	}
	
	/**
	 * Helper parsing method, replaces literal sequences instead of regular expressions
	 * @param base the string to itrate over
	 * @param toReplace the unwanted sequence
	 * @param replaceWith the wanted sequence
	 * @return the base string with the unwanted pattern replaced with the wanted pattern
	 */
	private static String replaceAllSimple(String base,String toReplace,String replaceWith) {
		String temp;
		for(int i=0;i<base.length()-toReplace.length();i++) {
			temp=base.substring(i, i+toReplace.length());
			if(temp.equals(toReplace)) {
				base=base.substring(0,i)+replaceWith+base.substring(i+toReplace.length(),base.length());
			}
		}
		return base;
	}
}