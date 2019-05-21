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

public class ArduinoClassHTest {

	@Test
	/**Established the correct output of the h file
	 *  using input fields from the ArduinoClassField enum
	 * */
	public void testHOutput() {

		// This example generates a class represented as a string
		// The user can decide how these string will be inputted
		// These fields are the minimum required to generate an arudino class

		ArduinoClassH template = new ArduinoClassH(ArduinoClassExample.CLASSNAME.toString(),
				ArduinoClassExample.AUTHOR.toString(),
				ArduinoClassExample.ORGANIZATION.toString(), 
				true,
				ArduinoClassExample.HEADERCOMMENTS.toString(), 
				ArduinoClassExample.SUPPORTEDBOARDS.toString(),
				ArduinoClassExample.VARIABLES.toString(),
				ArduinoClassExample.PRIVATEMETHODS.toString(),
				ArduinoClassExample.PUBLICMETHODS.toString());
		//get the String representation of the H file
		String generatedClass = template.getHeader();
		String correctClass=ArduinoClassHardCoded.H_FILE.toString();
		//print out a comparison message useful in finding potential error
		System.out.print("H File Test:");
		System.out.println(AssertMethods.assertEqualsFeedback(correctClass, generatedClass));
		//use an enum with the correct h file to test this output
		assertEquals(correctClass,generatedClass);
	}
	
	@Test
	/**Established the correct output of the h file
	 *  using input fields from the ArduinoClassField enum
	 *  wehn all boards are allowed
	 * */
	public void testHOutputAllBoards() {

		// This example generates a class represented as a string
		// The user can decide how these string will be inputted
		// These fields are the minimum required to generate an arudino class

		ArduinoClassH template = new ArduinoClassH(ArduinoClassExample.CLASSNAME.toString(),
				ArduinoClassExample.AUTHOR.toString(),
				ArduinoClassExample.ORGANIZATION.toString(), 
				true,
				ArduinoClassExample.HEADERCOMMENTS.toString(), 
				"ALL",
				ArduinoClassExample.VARIABLES.toString(),
				ArduinoClassExample.PRIVATEMETHODS.toString(),
				ArduinoClassExample.PUBLICMETHODS.toString());
		//get the String representation of the H file
		String generatedClass = template.getHeader();
		String correctClass=ArduinoClassHardCoded.H_FILE_ALL_BOARDS.toString();
		//print out a comparison message useful in finding potential error
		System.out.print("H File Test All Boards:");
		System.out.println(AssertMethods.assertEqualsFeedback(correctClass, generatedClass));
		//use an enum with the correct h file to test this output
		assertEquals(correctClass,generatedClass);
	}
	
	@Test
	/**Established the correct output of the keywords
	 *  file using input fields from the ArduinoClassField enum
	 * */
	public void testKeywordsOutput() {

		// This example generates a class represented as a string
		// The user can decide how these string will be inputted
		// These fields are the minimum required to generate an arudino class

		ArduinoClassH template = new ArduinoClassH(ArduinoClassExample.CLASSNAME.toString(),
				ArduinoClassExample.AUTHOR.toString(),
				ArduinoClassExample.ORGANIZATION.toString(), 
				true,
				ArduinoClassExample.HEADERCOMMENTS.toString(), 
				ArduinoClassExample.SUPPORTEDBOARDS.toString(),
				ArduinoClassExample.VARIABLES.toString(),
				ArduinoClassExample.PRIVATEMETHODS.toString(),
				ArduinoClassExample.PUBLICMETHODS.toString());
		//get the String representation of the keywords file
		String generatedClass = template.getKeywords();
		String correctClass=ArduinoClassHardCoded.KEYWORDS_FILE.toString();
		//use an enum with the correct keyword file to test this output
		System.out.print("Keyword File Test:");
		System.out.println(AssertMethods.assertEqualsFeedback(correctClass, generatedClass));
		assertEquals(correctClass,generatedClass);
	}


}