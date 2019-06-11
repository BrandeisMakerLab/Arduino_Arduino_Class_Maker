/**Name: Jacob Smith
  *Email:jsmith2021@brandeis.edu 
  *Date: May 21, 2019
  *Assignment:	Personal Study, tests the methods of the ArudinoParser class
  *Bugs:
  *Sources:
  *Rights: Copyright (C) 2019 Jacob Smith
  *  	   License is GPL-3.0, included in License.txt of this github project
  */
package parsing;

import static org.junit.Assert.*;

import org.junit.Test;

import cc.ArduinoClassGenerator.ArduinoParser;

public class ArduinoParserTest {	
	@Test
	/**
	 * asserts that the correctClosingIndex
	 */
	public void testGetClosingIndex() {
		//build array of exmaple strings and correct indecies
		String[] testStrings = { "{}", "{{}}", "{{{}}}", "{{}{}}", "{{}}{}","{{}"};
		int[] correctClosingIndices = { 1, 3, 5, 5, 3,-1 };
		int returnedClosingIndex;
		//loop through array of examples
		for (int i = 0; i < testStrings.length; i++) {
			//get closing index
			returnedClosingIndex = ArduinoParser.getClosingIndex(testStrings[i], 0);
			//assert that closing index equals correct index
			assertEquals(correctClosingIndices[i],returnedClosingIndex);
		}
	}
	
	@Test
	/**
	 * asserts that the correctClosingIndex works even when starting index is wrong
	 */
	public void testGetClosingIndexWrongStartIndex() {
		//build array of exmaple strings and correct indecies
		String[] testStrings = { "  {}", "  {{}}", "  {{{}}}", "  {{}{}}", "  {{}}{}","  {{}"};
		int[] correctClosingIndices = { 3, 5, 7, 7, 5,-1 };
		int returnedClosingIndex;
		//loop through array of examples
		for (int i = 0; i < testStrings.length; i++) {
			//get closing index
			returnedClosingIndex = ArduinoParser.getClosingIndex(testStrings[i], 0);
			//assert that closing index equals correct index
			assertEquals(correctClosingIndices[i],returnedClosingIndex);
		}
	}
}
