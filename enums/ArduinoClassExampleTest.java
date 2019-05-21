/** Name: Jacob Smith
 *  Email:jsmith2021@brandeis.edu
 *  Assignment: Test the validation method of ArduinoClass example
 *  Which is useful in validating user input
 *  Date: May 17, 2019
 *  Sources: 
 *  Bugs:
 */
package enums;

import static org.junit.Assert.*;

import org.junit.Test;

import testBackgroundCode.AssertMethods;

public class ArduinoClassExampleTest {
	
	/**
	 * adds the character to every element of examples to test input validator
	 * and stores the validation message
	 */
	 private static String[] getValidationMessage(char c){
		  ArduinoClassExample[]examples=ArduinoClassExample.values();
		  String example;
		  String incorrectExample;
		  String[]validationMessages=new String[examples.length];
		  //iterate through all possible examples
		  for(int i=0;i<examples.length;i++){
			  //get the example string
			  example=examples[i].toString();
			  //make the example invalid by adding the character to it
			  incorrectExample=example+c;
			  //get the validation message for that example
			  validationMessages[i]=examples[i].validate(incorrectExample);
		  }
		  return validationMessages;
	  } 
	  
	@Test
	/**
	 * checks the validation messages generated if 
	 * one extra newLine is entered into a valid responce
	 */
	public void testNewLines() {
		String []generatedMessages=getValidationMessage('\n');
		String []correctMessages={
				"Error, Expected 0 newlines and you entered 1",
				"Error, Expected 0 newlines and you entered 1",
				"Error, Expected 0 newlines and you entered 1",
				"Error, Expected 0 newlines and you entered 1",
				"Error, Expected 0 newlines and you entered 1",
				"Error, Expected 1 newlines and you entered 2",
				"Error, Expected 0 newlines and you entered 1",
				"Error, Expected 13 newlines and you entered 14"};
		assertEquals(AssertMethods.arrEquals(generatedMessages,correctMessages),true);
		
	}
	
	@Test
	/**
	 * checks the validation messages generated if
	 *  one extra bar is entered into a valid responce
	 */
	public void testBars() {
		
		String []generatedMessages=getValidationMessage('|');
		String []correctMessages={
				"Error, Expected 0 bars and you entered 1",
				"Error, Expected 0 bars and you entered 1",
				"Error, Expected 0 bars and you entered 1",
				"Error, Expected 0 bars and you entered 1",
				"Error, Expected 0 bars and you entered 1",
				"Error, Expected 4 bars and you entered 5",
				"Error, Expected 0 bars and you entered 1",
				"Error, Expected 9 bars and you entered 10"};
		assertEquals(AssertMethods.arrEquals(generatedMessages,correctMessages),true);
		
	}
	
	@Test
	/**
	 * checks the validation messages generated if
	 *  one extra space is entered into a valid responce
	 */
	public void testSpaces() {
		//spaces aren't enforced that often
		//because any comment the user writes will have unkown number of spaces
		String []generatedMessages=getValidationMessage(' ');
		String []correctMessages={
				"Error, Expected 0 spaces and you entered 1",
				"Error, Expected 1 spaces and you entered 2",
				null,
				null,
				null,
				null,
				null,
				null};
		assertEquals(AssertMethods.arrEquals(generatedMessages,correctMessages),true);
		
	}


}
