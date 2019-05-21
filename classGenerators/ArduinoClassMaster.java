package classGenerators;
/* Name: Jacob Smith
Date: May 12 2019
Assignment: Personal study, allows the user to edit an arduino library using a template, 
	and lets me practice my JavaCodebaseComponent
Email: jsmith2021@brandeis.edu
Notes: needed eclipse debugger to find template
I am separating the scanner ineraec from the class
https://compiler.javatpoint.com/opr/test.jsp?filename=CurrentDateTimeExample1
I have to just write code, not worry about 18 steps ahead
I'm usnig the ide, also appreciating how powerful a praser is
*/

import java.time.format.DateTimeFormatter; 
import java.time.LocalDateTime;

import parsing.MiniScanner;

public abstract class ArduinoClassMaster{
	
	protected MiniScanner reader;
	protected String arduinoClass;
	//determines whether the date will be hard coded, necessary for testing the class
	//with example file made on may 15 2019
	boolean hardCodeDate;
	

	/**
	* Loads an example class into memory given date for testing purposes
	* and parses it into header comment, methods, header file
	*/
	
	public ArduinoClassMaster(String className,String author,String organization,boolean hardCodeDate,String headerComments,String supportedBoards){
		this.hardCodeDate=hardCodeDate;
		init(className,author,organization,headerComments,supportedBoards);
		
	}
	
	/**
	* Loads an example class into memory given date
	* and parses it into header comment, methods, header file
	*/
	
	public ArduinoClassMaster(String className,String author,String organization,String headerComments,String supportedBoards){
		init(className,author,organization,headerComments,supportedBoards);
	}
	
	/** 
	 * initializes an ArduinoClassMaster class
	 * this isn't a constructor to get aroudn the 
	 * chaining must be first line error
	 */
	private void init(String className,String author,String organization,String headerComments,String supportedBoards){
		reader=new MiniScanner();
		arduinoClass="";
		arduinoClass+=generateHeaderComment(author,organization,headerComments,supportedBoards);
	}
	
	/** Generates the header comment of an arduino class given
	necessary strings*/
	protected String generateHeaderComment(String author,String organization, String headerComments,String supportedBoards){
		String date=genDate();
		String headerComment="/* Written by "+author+" for "+organization+" "+date+"\n";
		headerComment+=headerComments+"\n";
		headerComment+="Boards supported: "+supportedBoards+"*/\n\n";
		return headerComment;	
	}

	/**
	 * Helper method to get the current date and return it as a string
	 * for the header comment
	 * The date can be hardcoded for testing purposes
	 * */
	private String genDate(){
		if(hardCodeDate){
			return "2019/05/15";
		}else{
			//date code from link
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDateTime now = LocalDateTime.now();
			return dtf.format((now));
		}
		
	}
	
	/** Generates the board definition that allows a class to not cause compilation errors for the long board*/
	protected String generateBoardDefInitial(String supportedBoards){
		//check if all boards are allowed, if not go to more complicated helper method
		if(supportedBoards.equals("ALL")){
			return "//this should work on all boards, so there is no preprocessor directive here\n\n";
		}else{
			return generateBoardDef(supportedBoards);
		}
	}
	
	/** Generates the string that will only compile the class if the board is correct*/
	protected String generateBoardDef(String supportedBoards){
		//input comment
		String boardDef="//only compile this class if the board is correct\n";
		reader.prime(supportedBoards," ");
		String board;
		//generate board statement for first board
		boardDef+="#if defined(";
		if(reader.hasNext()){
			board=reader.next();
			boardDef+=board+") ";
		}
		//generate board define statement for all additional boards with or
		while(reader.hasNext()){
			board=reader.next();
			boardDef+="| defined ("+board+")";
		}
		boardDef+="\n\n";
		return boardDef;
	}
		
	/** Determines what libraries should be included by
	analyzing the type of variables*/
	protected String startLibraryIncludes(String variables,String className){
		//add comment and include for header file
		String includes="//includes the libraries of code necessary to make this one work\n";
		includes+="#include <"+className+".h>\n";
		return includes;
	}
	
	/** Generate method bodies based on input Strings*/
	protected String generateMethods(String className,String methods,boolean isPublic){
		//return a blank string if inputs are null,useful because a class could not have any private methods
		if(methods==null){return "";}
		String methodString="";
		String [] methodParts;
		reader.prime(methods,"\n\n");
		while(reader.hasNext()){
			methodParts=parseMethod(reader.next());
			methodString+=genMethod(className,methodParts,isPublic);
			
		}
		
		return methodString;
	}
	
	/** parses a method into a body, dataType, comment*/
	protected String [] parseMethod(String methodInfo){
		MiniScanner methodReader=new MiniScanner();
		methodReader.prime(methodInfo, "|");
		String[] methodParts=new String[4];
		methodReader.hasNext();
		methodParts[0]=methodReader.next();//dataType
		methodReader.hasNext();
		methodParts[1]=methodReader.next();//name
		methodReader.hasNext();
		methodParts[2]=methodReader.next();//comment
		methodReader.hasNext();
		methodParts[3]=methodReader.next();//body
		return methodParts;
	}
	
	/** Generate method bodies based on input Strings, will be implrmented by later classes*/
	protected abstract String genMethod(String className,String []methods,boolean isPublic);
	

	/**
	* Returns a string representation of the class
	*/
	public String toString(){
		return arduinoClass;
	}
	
	
}