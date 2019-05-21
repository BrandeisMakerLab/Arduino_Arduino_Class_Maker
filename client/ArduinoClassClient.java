package client;
import java.util.Scanner;

import enums.ArduinoClassExample;
import enums.ArduinoClassExample;
import enums.SpecialChar;

/**Name: Jacob Smith 
 * Date 5/14/2049 Personal Study, uses ArduinoClasses to autaomtically generate header, class, and keyword files
 * Bugs: array position hardcoded
 * Sources: Multline input scheme all code from https://stackoverflow.com/questions/42429134/reading-multiple-lines-into-a-scanner-object-in-java*/
public class ArduinoClassClient implements Prompter {
	public static final String consoleToken="!";
	
	public static void main (String[]args) {
				//This example generates a class represented as a string
				//The user can decide how these string will be inputted
				//These fields are the minimum required to generate an arduino class
				ArduinoClassClient client=new ArduinoClassClient();
				//use any of examples
				//client.simplestExample();
				client.prompt();
				
	}
	
	/**
	 * Creates an arduino cpp, header, and keyword file strings with no input or output
	 * */
	public void simplestExample(){
	
		//call helper method to display the generated files
		ArduinoClassContainer cont=new ArduinoClassContainer(ArduinoClassExample.CLASSNAME.toString(),
				ArduinoClassExample.AUTHOR.toString(),
				ArduinoClassExample.ORGANIZATION.toString(),
				true,
				ArduinoClassExample.HEADERCOMMENTS.toString(),
				ArduinoClassExample.SUPPORTEDBOARDS.toString(),
				ArduinoClassExample.VARIABLES.toString(),
				ArduinoClassExample.PRIVATEMETHODS.toString(),
				ArduinoClassExample.PUBLICMETHODS.toString());
		System.out.println(cont);
	}
	
	/**
	 * Creates an arduino cpp, header, and keyword file strings using Scanner as input
	 * @return the created ArduinoClassContainer for testing purposes
	 * */
	public ArduinoClassContainer prompt() {
		
		//initalize a new scanner to read keyboard input
		Scanner reader=new Scanner(System.in);
		//generate an array of field names
		ArduinoClassExample []fields=ArduinoClassExample.values();
		ArduinoClassExample[]examples=ArduinoClassExample.values();
		//create an array of the same length to hold the user's answers
		String []userAnswers=new String[fields.length];
		
		System.out.println("Welcome to Arduino Class Generator by Brandeis Automation Laborotory");
		System.out.println("Please enter information from each prompt and end with "+consoleToken);
		populate(fields,examples,userAnswers,reader);
		return endConsole(userAnswers,reader);
	
	}
	
	/**
	 * Populates the UserNaswers array with Scanner input using promprts from given enums
	 * no return type because arrrays are passed by reference
	 */
	private static void populate(ArduinoClassExample []fields, ArduinoClassExample[]examples,String[]userAnswers,Scanner reader){
		//temporary variable for code readability
		ArduinoClassExample field;
		ArduinoClassExample example;
		
		//iterate through all the fields, displaying prompt, example formatting, and reading user input
		for(int i=0;i<fields.length;i++){
			field=fields[i];
			example=examples[i];
			//display prompt
			System.out.println(field.getPrompt()+"\n"+example.toString()+consoleToken);
			userAnswers[i]=getUserResponce(reader,example);
			
			
		}
		
	}
	
	/**
	 * executes the entire process of reading and validating the user's responce
	 */
	private static String getUserResponce(Scanner reader, ArduinoClassExample example){
		boolean validated=false;
		String message;
		String responce=null;
		while(!validated){
			responce=readAndValidateUserResponce(reader);
			message=example.validate(responce);
			if(message==null){
				validated=true;
				//convert "null" to null
				responce= processUserResponce(responce);
			}else{
				System.out.println(message);
			}
		}
		return responce;
	}
	/**
	 * handles the exit conditions of the  cient scanner demonstration by either returning
	 * a Arduino Class Container object or catching an exception
	 */
	public static ArduinoClassContainer endConsole(String[]userAnswers,Scanner reader){
		ArduinoClassContainer cont=null;
		try{
			//call helper method to display the generated files, array positions hardcoded
			cont=new ArduinoClassContainer(userAnswers[0], userAnswers[1], userAnswers[2], true,userAnswers[3],userAnswers[4], userAnswers[5], userAnswers[6], userAnswers[7]);
			System.out.println("loading...");
			sleepNoError(7000);
			System.out.println(cont.getHeader()+"\n");
			sleepNoError(7000);
			System.out.println(cont.getBody()+"\n");
			sleepNoError(7000);
			System.out.println(cont.getKeywords()+"\n");
			sleepNoError(7000);
			
		}catch (Exception e){
			System.out.println("Sorry, there was a formatting error in your input, couldn't make class");
			System.out.println("Would you like to view the error? Y/N");
			String answer=reader.next().toLowerCase();
			//if user doens't enter y or n, display error prompt
			while(!(answer.equals("y") | answer.equals("n"))){
				System.out.print("Please enter Y/N:");
				answer=reader.next().toLowerCase();
			}
			if(answer.equals("y")){
				e.printStackTrace();
				sleepNoError(500);
			}
			
		}
		reader.close();
		System.out.println("Thank you, closing down now");
		return cont;
	}
	
	/**
	 * reads the user's responce from the console, and only returns result if valid
	 * otherwise, will display error message and re-cosnume user input
	 */
	private static String readAndValidateUserResponce(Scanner reader){
		boolean valid=false;

		String responce=null;
		SpecialChar mistake;
		//loop readings uer input, and either quit or display error message
		while(!valid){
			
			
			responce=readAndTokenizeUserResponce(reader);
			//check for mistaken input
			mistake=checkMistakes(responce);
			if(mistake==null){
				valid=true;
			}else{
				System.out.println("Error, the responce can't only be "+mistake.getseqName()+" .");
				System.out.println("Please try again");
			}
			
		}
		return responce;
	
		
	}
	
	/**
	 * reads the user responce from the console and gaurantees that it can be tokenized
	 * by asking for correct input until it can be
	 */
	private static String readAndTokenizeUserResponce(Scanner reader){
		boolean tokenized=false;
		String responce="";
		while(!tokenized){
			//read user input
			responce=readUserResponce(reader);
			if(responce==null){
				System.out.println("Error reading your responce, did you have two of "+consoleToken+"?");
			}else{
				tokenized=true;
			}
		}
		return responce;
	}
	
	/**
	 * validation method to iterate over a sequence of common mistake Strings
	 * and prompt the user if they are not allowed
	 * @return null if input is correct, the SpecialChar if it is not
	 */
	private static SpecialChar checkMistakes(String responce){
		SpecialChar []mistakeChars=SpecialChar.values();
		for(int i=0;i<mistakeChars.length;i++){
			//if the responce is only mistake characters, return true
			if(containsOnly(responce,mistakeChars[i])){
				return mistakeChars[i];
			}
		}
		//if all mistake characters where executed,return c for correct
		return null;
		
		
	}
	
	/**
	 * validation method to return whether string contains only a string
	 * which could be a blank string or a bunch  of newline, either of which are not
	 * valud input
	 */
	private static boolean containsOnly(String s,SpecialChar c){
		//check for null string
		if(s==null){return false;}
		//check for blank string manually
		if(s.isEmpty()&&c.getSequence().equals("")){return true;}
		//otherwise, iterate through the base string and see if it is only special characters
		String temp;
		for(int i=0;i<s.length();i++){
			//get the one element string at that index
			temp=s.substring(i,i+1);
			//if the special character doesn't equal the temps string, return false
			if(!c.getSequence().equals(temp)){
				return false;
			}
		}
		//if the whole string was iterated through, return true
		return true;
	}
	
	/**
	 * process user responce, converting data types such
	 * as "null" to null
	 */
	private static String processUserResponce(String responce){
		if(responce.toLowerCase().equals("null")){
			return null;
		}else{
			return responce;
		}
	}
	
	/**
	 * reads and assembles responces from the user that can consist fo many lines
	 */
	private static String readUserResponce(Scanner reader){
		String line="dummy";
		String input="";
		boolean tokenFound=false;
		//keep reading user input until the token is found
		while(!tokenFound){
			//consume next line of input
			line=reader.nextLine();
			//check for multiple tokens being found in one entry
			if(containsMultiple(line,consoleToken)){
				return null;
		    //see if token was found	
			}else if(line.contains(consoleToken)){
				//set flag to true
				tokenFound=true;
				//remove all instances of the token
				line=line.replaceAll(consoleToken, "");
				//add the line without a newline character at the end
				input+=line;
			}else{
				//add the token free input to string
				input+=line+"\n";
			}
			
			
		}
		//return the total string of input
		return input;
	}
	/**
	 * helper method to tell if a string contains multiple of a susbtring
	 * helpful for input validation
	 */
	private static boolean containsMultiple(String base, String sequence){
		int firstIndex=base.indexOf(sequence);
		int secondIndex= base.indexOf(sequence, firstIndex+1);
		return secondIndex>firstIndex;
		
	}
	/**
	 * Helper method to tell computer to sleep without needing throws declaration
	 */
	private static void sleepNoError(int sleepTime){
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e1) {
			System.out.println("Error with sleep statement");
		}
		
	}

}