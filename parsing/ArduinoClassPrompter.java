package parsing;

import java.util.Scanner;

import client.ArduinoClassClient;
import client.ArduinoClassContainer;
import client.Prompter;
import enums.ExampleLineByLine;
import parsing.Validator;

/**
 * Name: Jacob Smith Email:jsmith2021@brandeis.edu Assignment: Personal Study,
 * an enum of class fields with user prompts and error messages. This enum
 * allows all of the field information to be in one place, encapsulation Date:
 * May 15, 2019 Sources:
 * https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html Bugs: notes:
 * enum has nice automatic toString, name field
 */

public class ArduinoClassPrompter implements Prompter {

	
	/**@Override
	 * Prompts the user to enter line by line input for the specific field
	 */
	public ArduinoClassContainer prompt() {
		//create new scanner to read keyboard input
		Scanner reader=new Scanner(System.in);
		//get an array of responce strings
		String[]responces=getResponces(reader);
		//use the ArduinoClass Client method to create an ArduinoClassContainer
		return ArduinoClassClient.endConsole(responces, reader);
	}
	
	/**
	 * Prompts the user to enter line by line input for the specific field
	 * gets user responces in the form of an array
	 */
	private String[] getResponces(Scanner reader) {
		String[] responces = new String[8];
		System.out.print(ExampleLineByLine.CLASSNAME.getPrompt());
		responces[0] = readField(reader, false);
		System.out.print(ExampleLineByLine.AUTHOR.getPrompt());
		responces[1] = readField(reader, true);
		System.out.print(ExampleLineByLine.ORGANIZATION.getPrompt());
		responces[2] = readField(reader, true);
		System.out.print(ExampleLineByLine.HEADERCOMMENTS.getPrompt());
		responces[3] = readField(reader, true);
		responces[3] += "\n"+readMultiInput(reader, '\n', true,true);
		//remove trailing newline character
		responces[3]=responces[3].substring(0,responces[3].length()-1);
		System.out.print(ExampleLineByLine.SUPPORTEDBOARDS.getPrompt());
		responces[4] = readField(reader, false);
		if (!responces[4].toLowerCase().equals("all")) {
			responces[4] += " " + readMultiInput(reader, ' ', false,true);
		}
		//remove trailing whitespace
		responces[4]=responces[4].trim();
		responces[5]=getVariables(reader);
		responces[6]=getMethod(reader,ExampleLineByLine.PRIVATEMETHODS);
		//add constructor to public methods
		System.out.println("Please enter constructor body");
		responces[7]="";
		responces[7]+=readField(reader,true);
		responces[7]+="\n"+readMultiInput(reader,'\n',true,true)+"\n";
		responces[7]+=getMethod(reader,ExampleLineByLine.PUBLICMETHODS);
		
		//return the generated array of strings
		return responces;
	}
	
	/**
	 * prompts the user to enter the private variable information of the class
	 * @param reader the Scanner used to prompt the reader
	 * @return a correctly fromatted string holding variable information
	 */
	private static String getVariables(Scanner reader) {
		boolean done = false;
		String responce="";
		while (!done) {
			System.out.print(ExampleLineByLine.VARIABLES.getAlternatePrompt());
			String temp = reader.nextLine().toLowerCase();
			if(!temp.equals("y") && !temp.equals("n")) {
				System.out.println("Please enter Y or N");
			}else if (temp.equals("y")) {
				String prompt = ExampleLineByLine.VARIABLES.getPrompt();
				String[] prompts = prompt.split("-");
				responce += readMultiInputPredefined(reader, prompts, '|',false)+"\n";
			} else {
				done = true;
			}
		}
		return responce;
	}
	
	/**
	 * 
	 * @param reader the scanner used to read keyboard input
	 * @param example the enum which holds the prompts to display
	 * @return the user's input formatted to make an Arduino Class
	 */
	private static String getMethod(Scanner reader,ExampleLineByLine example) {
		boolean done = false;
		String responce="";
		while (!done) {
			System.out.print(example.getAlternatePrompt());
			String temp = reader.nextLine().toLowerCase();
			if(!temp.equals("y") && !temp.equals("n")) {
				System.out.println("Please enter Y or N");
			}else if (temp.equals("y")) {
				String prompt = example.getPrompt();
				String[] prompts = prompt.split("-");
				responce += readMultiInputPredefined(reader, prompts, '|',true);
				//get the rest of the method body
				System.out.print(prompts[prompts.length-1]);
				responce+= "|\n"+readField(reader, true);
				responce+="\n"+readMultiInput(reader,'\n',true,true)+"\n";
			
			} else {
				done = true;
			}
			
		}
		return responce;
	}
	
	/**
	 * reads user input, looping until valid input
	 * 
	 * @param reader
	 *            the scanner which will read user input
	 * @param allowSpaces
	 *            whether spaces are allowed, as in a comment
	 * @param allowAlternateInput
	 *            whether alternateInput should be allowed
	 * @return valid user input
	 */
	private static String readField(Scanner reader, boolean allowSpaces) {
		String input = null;
		String message = "dummy";
		// keep asking for user input until correct
		while (message != null) {
			input = reader.nextLine();
			// check if alternate input is allowed, if so update forbidden words

			// get an error message with updated forbidden words, because somtimes the user
			// is allowed to enter an alternateInput
			message = Validator.getErrorMessage(input, allowSpaces, null);

			if (message != null) {
				System.out.print(message + ":");
			}
		}
		return input;
	}
	
	/**
	 * helper method to read and put together many lines of user input this is for
	 * when you don't know how many lines of user input will be entred
	 * @param enteredValid whether the user has already entered valid input
	 * @param prompts
	 *            an array of prompts for the user to enter
	 * @return the compiled user responce for that field
	 */
	private static String readMultiInput(Scanner reader, char connecter, boolean allowSpaces,boolean enteredValid) {
		String responce = "";
		String temp = "";
		// loop exit variable
		boolean done = false;
		// only allows the user to exit the loop if they entered something
		boolean enteredDone = false;
		// this variable only allows the user to exit once valid input is entered
		while (!done) {
			// prompt for user resopnce
			System.out.print("Enter next part or DONE:");
			temp = readField(reader, allowSpaces);
			enteredDone = temp.toLowerCase().contains("done");
			// only allow user to leave loop if they entered valid input
			if (enteredDone && enteredValid) {
				done = true;
				// otherwise, display error message
			} else if (enteredDone && !enteredValid) {
				System.out.println("you can't enter done yet");
			} else {
				responce += temp + connecter;
				enteredValid = true;
			}
		}
		return responce;
	}

	/**
	 * Prompts the user for input of predifned length
	 * @param secondToLast whether the method should stop at the last or second to last input
	 * @param reader
	 *            the scanner used for user input
	 * @return user input
	 */
	private static String readMultiInputPredefined(Scanner reader, String[] prompts, char internalToken,boolean secondToLast) {
        //set the stop index based on secondToLast argument
		int stop=1;
		if(secondToLast) {
        	stop=2;
        }
		String input = "";
		for (int i = 0; i < prompts.length - stop; i++) {
			System.out.print(prompts[i]);
			input += readField(reader, false) + internalToken;
		}
		// allow spaces for the last input because it is comment
		System.out.print(prompts[prompts.length - stop]);
		input += readField(reader, true);
		return input;
	}

	/**
	 * Displays capabilities of the enum
	 */
	public static void main(String[] args) {
		//create scanner and ArduinoClassPrompter
		ArduinoClassPrompter prompter=new ArduinoClassPrompter();
		//call the prompt method to read user input 
		ArduinoClassContainer cont=prompter.prompt();
		//print the generated classes
		System.out.println(cont.toString());
	}
}
