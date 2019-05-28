package classGenerators;
import static org.junit.Assert.*;

import org.junit.Test;

import testBackgroundCode.AssertMethods;

import enums.ArduinoClassExample;
import enums.ArduinoClassHardCoded;


/**
 * Name: Jacob Smith Email:jsmith2021@brandeis.edu Assignment: Personal Study,
 * makes sure that the ArduinoClass CPP returns a proprly formatted classs when
 * given the correct inputs Date: May 15, 2019 Sources: Bugs:
 */

public class ArduinoClassCppTest {

	@Test
	/**Establishes correct example strings and confirms the output of the cpp class with correct
	 * tempalte file
	 * */
	public void testCppOutput() {

		// This example generates a class represented as a string
		// The user can decide how these string will be inputted
		// These fields are the minimum required to generate an arudino class

		ArduinoClassCpp template = new ArduinoClassCpp(ArduinoClassExample.CLASSNAME.toString(),
				ArduinoClassExample.AUTHOR.toString(),
				ArduinoClassExample.ORGANIZATION.toString(), 
				true,
				ArduinoClassExample.HEADERCOMMENTS.toString(), 
				ArduinoClassExample.SUPPORTEDBOARDS.toString(),
				ArduinoClassExample.VARIABLES.toString(),
				ArduinoClassExample.PRIVATEMETHODS.toString(),
				ArduinoClassExample.PUBLICMETHODS.toString());
		//get the String representation of the   cpp file
		String generatedClass = template.toString();
		String correctClass=ArduinoClassHardCoded.CPP_FILE.toString();
		//use an enum with the correct class file to test this output
		System.out.print("Cpp File Test:");
		System.out.println(AssertMethods.assertEqualsFeedback(correctClass, generatedClass));
		assertEquals(correctClass,generatedClass);


	}
	
	@Test
	/**Establishes correct example strings and confirms the output of the cpp class with correct
	 * template file when all boards are allowed
	 * */
	public void testCppOutputAllBoards() {

		// This example generates a class represented as a string
		// The user can decide how these string will be inputted
		// These fields are the minimum required to generate an arudino class

		ArduinoClassCpp template = new ArduinoClassCpp(ArduinoClassExample.CLASSNAME.toString(),
				ArduinoClassExample.AUTHOR.toString(),
				ArduinoClassExample.ORGANIZATION.toString(), 
				true,
				ArduinoClassExample.HEADERCOMMENTS.toString(), 
				"ALL",
				ArduinoClassExample.VARIABLES.toString(),
				ArduinoClassExample.PRIVATEMETHODS.toString(),
				ArduinoClassExample.PUBLICMETHODS.toString());
		//get the String representation of the   cpp file
		String generatedClass = template.toString();
		String correctClass=ArduinoClassHardCoded.CPP_FILE_All_BOARDS.toString();
		//use an enum with the correct class file to test this output
		System.out.print("Cpp File Test All Boards:");
		System.out.println(AssertMethods.assertEqualsFeedback(correctClass, generatedClass));
		assertEquals(correctClass,generatedClass);


	}




}