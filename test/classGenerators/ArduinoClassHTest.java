package classGenerators;
import static org.junit.Assert.*;

import org.junit.Test;

import cc.arduinoclassmaker.ArduinoClassExample;
import cc.arduinoclassmaker.ArduinoClassH;
import cc.arduinoclassmaker.libraryOptionalFields;
import testBackgroundCode.AssertMethods;
import enums.ArduinoClassHardCoded;


/**
 * Name: Jacob Smith Email:jsmith2021@brandeis.edu Assignment: Personal Study,
 * makes sure that the ArduinoClass CPP returns a proprly formatted classs when
 * given the correct inputs Date: May 15, 2019 Sources: Bugs:
 * Rights: Copyright (C) 2019 Jacob Smith
 *  	   License is GPL-3.0, included in License.txt of this github project
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

		//create libraryOptionalFields object to hold fields
		libraryOptionalFields fields=new libraryOptionalFields(ArduinoClassExample.AUTHOR.toString(),
				ArduinoClassExample.ORGANIZATION.toString(), 
				true,ArduinoClassExample.SUPPORTEDBOARDS.toString());
		
		ArduinoClassH template = new ArduinoClassH(ArduinoClassExample.CLASSNAME.toString(),fields,
				ArduinoClassExample.HEADERCOMMENTS.toString(), 
				ArduinoClassExample.VARIABLES.toString(),
				ArduinoClassExample.PRIVATEMETHODS.toString(),
				ArduinoClassExample.PUBLICMETHODS.toString());
		//get the String representation of the H file
		String generatedClass = template.getHeader();
		//use an enum with the correct h file to test this output
		AssertMethods.compareFiletoString("testing_files\\ArduinoClasses\\HFile.txt", generatedClass.trim());
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

		//create libraryOptionalFields object to hold fields
		libraryOptionalFields fields=new libraryOptionalFields(ArduinoClassExample.AUTHOR.toString(),
				ArduinoClassExample.ORGANIZATION.toString(), 
				true,"ALL");
		
		ArduinoClassH template = new ArduinoClassH(ArduinoClassExample.CLASSNAME.toString(),
				fields,
				ArduinoClassExample.HEADERCOMMENTS.toString(), 
				ArduinoClassExample.VARIABLES.toString(),
				ArduinoClassExample.PRIVATEMETHODS.toString(),
				ArduinoClassExample.PUBLICMETHODS.toString());
		//get the String representation of the H file
		String generatedClass = template.getHeader();
		//compare generated class with known example
		AssertMethods.compareFiletoString("testing_files\\ArduinoClasses\\HFileAllBoards.txt", generatedClass);
	}
	
	@Test
	/**Established the correct output of the keywords
	 *  file using input fields from the ArduinoClassField enum
	 * */
	public void testKeywordsOutput() {

		// This example generates a class represented as a string
		// The user can decide how these string will be inputted
		// These fields are the minimum required to generate an arudino class

		//create libraryOptionalFields object to hold fields
		libraryOptionalFields fields=new libraryOptionalFields(ArduinoClassExample.AUTHOR.toString(),
			ArduinoClassExample.ORGANIZATION.toString(), 
			true,ArduinoClassExample.SUPPORTEDBOARDS.toString());
				
		ArduinoClassH template = new ArduinoClassH(ArduinoClassExample.CLASSNAME.toString(),
				fields,
				ArduinoClassExample.HEADERCOMMENTS.toString(), ArduinoClassExample.VARIABLES.toString(),
				ArduinoClassExample.PRIVATEMETHODS.toString(),
				ArduinoClassExample.PUBLICMETHODS.toString());
		//get the String representation of the keywords file
		String generatedClass = template.getKeywords();
		//compare generated keywords file with known example
		AssertMethods.compareFiletoString("testing_files\\ArduinoClasses\\KeywordsFile.txt", generatedClass);
	}

	@Test
	/**Tests that a header file can still be generated with fewer fields
	 * to allow for generation from a sketch
	 * This also tests behavior that would be the same for the body file because
	 * of inheritance
	 * */
	public void testHOutputFewerFields() {

		// This example generates a class represented as a string
		// The user can decide how these string will be inputted
		// These fields are the minimum required to generate an arudino class
		
		//create libraryOptionalFields object to hold fields
		libraryOptionalFields fields=new libraryOptionalFields(null,
			null, 
			true,"ALL");
		
		ArduinoClassH template = new ArduinoClassH(ArduinoClassExample.CLASSNAME.toString(),fields,
				ArduinoClassExample.HEADERCOMMENTS.toString(), 
				ArduinoClassExample.VARIABLES.toString(),
				ArduinoClassExample.PRIVATEMETHODS.toString(),
				ArduinoClassExample.PUBLICMETHODS.toString());
		//get the String representation of the H file
		String generatedClass = template.getHeader();
		//compare generated class with correct example
		AssertMethods.compareFiletoString("testing_files\\ArduinoClasses\\HeaderFileFewerKeywords.txt", generatedClass);
	}
}