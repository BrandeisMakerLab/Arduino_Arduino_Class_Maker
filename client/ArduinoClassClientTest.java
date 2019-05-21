/** Name: Jacob Smith
 *  Email:jsmith2021@brandeis.edu
 *  Assignment: 
 *  Date: May 16, 2019
 *  Sources: redirecting system.in to string: https://stackoverflow.com/questions/6415728/junit-testing-with-simulated-user-input
 *  redirexting system.out to string:https://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
 *  Bugs:
 */
package client;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;

import testBackgroundCode.AssertMethods;
import enums.ArduinoClassExample;
import enums.ArduinoClassHardCoded;
import enums.ExampleLineByLine;
import enums.PromptResponces;
import parsing.ArduinoClassPrompter;

public class ArduinoClassClientTest {
	@Test
	/**
	 *Test the ArduinoClass Client scanner example 
	 */
	public void testScannerExample () {
		//make the inputTester be ArduinoClassClient
		Prompter prompter=new ArduinoClassClient();
		//choose any enum of Arduino class example to be the responces array
		PromptResponces responces=ArduinoClassExample.AUTHOR;
		testScannerExample(prompter,responces);
	}
	
	@Test
	/**
	 *Test the ArduinoClassPrompts prompt method
	 *which prompts the user to enter input from keyboard to create
	 *an arduino class
	 */
	public void testEnumPrompter () {
		//make the inputTester be ArduinoClassClient
		Prompter prompter=(Prompter) new ArduinoClassPrompter();
	
		//choose any enum of Arduino class example to be the responces array
		PromptResponces responces=ExampleLineByLine.AUTHOR;
		testScannerExample(prompter,responces);
	}
	
	
	/**
	 * @param responces an object that will provide the input stream as a string
	 * @param tester an object guranteed to have a scanner example method 
	 * for testing use rinput
	 * Verifies that the ArdunoClass Client Scanner example and arduino class prompts
	 * produced the hardcoded correct body header and keyword files
	 * when primed with an input stream of correct inputs
	 */
	public void testScannerExample (Prompter tester,PromptResponces responces) {
		//save the old input and output streams
		InputStream keyboard=System.in;
		
		setStream(responces);
		
		//run the console printing example
		ArduinoClassContainer cont=tester.prompt();
		resetStream(keyboard);
		
		//verify that files where created correctly
		assertContainer(cont);
		
		
	}
	/**
	 * @param responces the class that will provide the string of responces simualting
	 * what the user would enter
	 * Sets the input and output streams of the system for testing
	 * System.in redirects to a predifined string of user responces
	 * System.out redirects to 
	 */
	private static void setStream(PromptResponces responces){
		//set the System.in input stream to predfined input for testing
		System.setIn(getInputStream(responces));
	}
	
	/**
	 * generates an input stream corresponding to what the user could enter to
	 * automatically generate an arduino class
	 * @return the input stream used
	 */
	private static ByteArrayInputStream getInputStream(PromptResponces responces){
		//build a string of all the responces needed to create an arduino class
		String allResponces=responces.getResponces();
		//set System.in to feed in that stream
		ByteArrayInputStream in = new ByteArrayInputStream(allResponces.getBytes());
		return in;
	}
	/**
	 * resets the keyboard to correct input
	 */
	private static void resetStream(InputStream keyboard){
		// optionally, reset System.in and out
		System.setIn(keyboard);
	}
	
	/**
	 * asserts that the contents of the ArduinoClassContainer matches the hardcoded exmaple files
	 * @param cont the ArduinoClassContainer to verify
	 */
	private static void assertContainer(ArduinoClassContainer cont){
		System.out.println(AssertMethods.assertEqualsFeedback(ArduinoClassHardCoded.CPP_FILE.toString(), cont.getBody()));
		assertEquals(ArduinoClassHardCoded.CPP_FILE.toString(),cont.getBody());
		System.out.print("Header File Test Console:");
		System.out.println(AssertMethods.assertEqualsFeedback(ArduinoClassHardCoded.H_FILE.toString(), cont.getHeader()));
		assertEquals(ArduinoClassHardCoded.KEYWORDS_FILE.toString(),cont.getKeywords());
		System.out.print("Keywords File Test Console:");
		System.out.println(AssertMethods.assertEqualsFeedback(ArduinoClassHardCoded.KEYWORDS_FILE.toString(), cont.getKeywords()));
		assertEquals(ArduinoClassHardCoded.KEYWORDS_FILE.toString(),cont.getKeywords());
	}
}
