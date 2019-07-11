package enums;
//import junit.framework.TestCase;
/**Name: Jacob Smith
 *Email:jsmith2021@brandeis.edu 
 *Date: Jul 10, 2019
 *Assignment: Personal Study, verifies the contents of the TODO enum	
 *Bugs:
 *Sources:
 *Rights:  Copyright (C) 2019 Jacob Smith
 *  		License is GPL-3.0, included in License.txt of this github project
 */

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cc.arduinoclassmaker.TODOs;

public class testTODOS  {

	@Test
	public void testTODOS() {
		//verify contents of all enum fields
		assertEquals(TODOs.HeaderComment.toString(),"TODO: EXPLAIN WHAT PROGRAM IS FOR AND GIVE YOUR NAME AND WHO CAN USE THIS PROGRAM");
		assertEquals(TODOs.Method.toString(),"TODO: EXPLAIN WHAT METHOD DOES");
		assertEquals(TODOs.PropertiesField.toString(),"TODO: SET THIS LIBRARY PROPERTIES FIELD");
		assertEquals(TODOs.Variable.toString(),"TODO: EXPLAIN WHAT VARIABLE IS FOR");
		assertEquals(TODOs.VariableValue.toString(),"TODO: SET INITIAL VARIABLE VALUE");
		//verify that there aern't any more fields
		assertEquals(TODOs.values().length,5);
	
	}

}

