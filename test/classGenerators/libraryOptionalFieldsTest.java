package classGenerators;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cc.arduinoclassmaker.libraryOptionalFields;
import testBackgroundCode.AssertMethods;


/**Name: Jacob Smith
 *Email:jsmith2021@brandeis.edu 
 *Date: Jul 5, 2019
 *Assignment:	personal study, tests the libraryOptional Fields class 
 *Bugs:
 *Sources:
 *Rights: Copyright (C) 2019 Jacob Smith
 *  	   License is GPL-3.0, included in License.txt of this github project
 */

public class libraryOptionalFieldsTest {

	/**
	 * an instance of the libraryOptionalFields class for testing
	 */
	private libraryOptionalFields fields;
	
	@Before
	/**
	 * creares a new librarOptionalFields class with specified fields for testing
	 */
	public void createClass() {
		fields=new libraryOptionalFields("Jacob Smith","Brandeis University", 
				true,"ARDUINO_AVR_UNO");
	}
	
	@Test
	/**
	 * tests the toString method of the libraryOptionalFieldsClass
	 */
	public void testToString() {
		String correctString="";
		correctString+="Author         :"+"Jacob Smith"+"\n";
		correctString+="Organization   :"+"Brandeis University"+"\n";
		correctString+="HardCodeDate   :"+"true"+"\n";
		correctString+="SupportedBoards:"+"ARDUINO_AVR_UNO"+"\n";
		assertEquals(correctString,fields.toString());
	}
		
	@Test
	/** tests the acessors of the libraryOptionalFields class
	 */
	public void testFields() {
		assertEquals("Jacob Smith",fields.getAuthor());
		assertEquals("Brandeis University",fields.getOrganization());
		assertEquals(true,fields.getHardCodeDate());
		assertEquals("ARDUINO_AVR_UNO",fields.getSupportedBoards());
	}
	
}