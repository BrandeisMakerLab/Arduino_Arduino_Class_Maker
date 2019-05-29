/**Name: Jacob Smith
  *Email:jsmith2021@brandeis.edu 
  *Date: May 28, 2019
  *Assignment:	Personal Study, verifies the behavior of the SketchParser class using correct
  *example files
  *Bugs:
  *Sources:
  */
package files;

import static org.junit.Assert.*;

import org.junit.Test;


public class SketchParserTest {

	@Test
	/**
	 * Reads a sketch from computer memory, and parses it into
	 * the necessary fields to generate an arduino class 
	 * @param args not used
	 */
	public void testSketchConversion() {
		//load the Example Sketch from file
		ScriptEditor helper = new ScriptEditor("WifiExample.txt");
		String contents = helper.toString();
		//create SketchParser object to get fields from sketch
		SketchParser parser=new SketchParser(contents);
		String generatedFields=parser.toString();
		//load correct fields from memory
		ScriptEditor correctFile=new ScriptEditor("SketchParserExample.txt");
		String correctFields=correctFile.toString();
		//replace special characters necessary for file comparisons
		correctFields=correctFields.replaceAll("\r", "");
		generatedFields=generatedFields.replaceAll("\r", "\n");
		//assert the the correct fields equal the generated fields
		assertEquals(correctFields,generatedFields);
			
	}

}