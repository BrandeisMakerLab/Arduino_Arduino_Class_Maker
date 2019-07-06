package classGenerators;
import static org.junit.Assert.*;

import org.junit.Test;

import cc.arduinoclassgenerator.ArduinoClassCpp;
import cc.arduinoclassgenerator.ArduinoClassExample;
import cc.arduinoclassgenerator.libraryOptionalFields;
import testBackgroundCode.AssertMethods;
import enums.ArduinoClassHardCoded;


/**
 * Name: Jacob Smith Email:jsmith2021@brandeis.edu Assignment: Personal Study,
 * makes sure that the ArduinoClass CPP returns a proprly formatted classs when
 * given the correct inputs Date: May 15, 2019 Sources: Bugs:
 * Rights: Copyright (C) 2019 Jacob Smith
 *  	   License is GPL-3.0, included in License.txt of this github project
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

		//create libraryOptionalFields object for fewer parameters
		libraryOptionalFields fields=new libraryOptionalFields(ArduinoClassExample.AUTHOR.toString(),
				ArduinoClassExample.ORGANIZATION.toString(), 
				true,ArduinoClassExample.SUPPORTEDBOARDS.toString());
		
		ArduinoClassCpp template = new ArduinoClassCpp(ArduinoClassExample.CLASSNAME.toString(),
				fields,
				ArduinoClassExample.HEADERCOMMENTS.toString(),
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
		
		//create libraryOptional fields object o hold fields
		libraryOptionalFields fields=new libraryOptionalFields(ArduinoClassExample.AUTHOR.toString(),
				ArduinoClassExample.ORGANIZATION.toString(), 
				true,"ALL");
		
		ArduinoClassCpp template = new ArduinoClassCpp(ArduinoClassExample.CLASSNAME.toString(),fields,		
				ArduinoClassExample.HEADERCOMMENTS.toString(), 
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