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

import client.ArduinoClassContainer;


public class SketchParserTest {

	@Test
	/**
	 * asserts behavior for Wifi example sketch
	 * which is very long, and contains different kinds of fields
	 * @param args not used
	 */
	public void testSketchConversionWifi() {
		compareSketch("WifiExample.txt","SketchParserExample.txt");
	}
	
	@Test
	/**
	 * asserts behavior for Morse exmaple sketch, which has missing comments
	 * and the newline coding style
	 * @param args not used
	 */
	public void testSketchConversionMorse() {
		compareSketch("Morse.txt","MorseParsedExample.txt");
	}
	
	@Test
	/**
	 *  checks that the automatically generated class from the morse code example file
	 *  matches the correct strings
	 */
	public void compareGeneratedClassMorse() {
		//load the Example Sketch from file
		ScriptEditor helper = new ScriptEditor("Morse.txt");
		String contents = helper.toString();
		//create SketchParser object to get fields from sketch
		SketchParser parser=new SketchParser(contents);
		ArduinoClassContainer cont=parser.getContainer("Morse", true);
		
		//load correct fields from memory and replace special characters necessary for file comparisons
		ScriptEditor helper2 = new ScriptEditor("Morse.cpp");//was WifiExample.txt
		String correctBody=helper2.toString().replaceAll("\r", "");
		ScriptEditor helper3 = new ScriptEditor("Morse.h");//was WifiExample.txt
		String correctHeader=helper3.toString().replaceAll("\r", "");
		ScriptEditor helper4 = new ScriptEditor("MorseKeywords.txt");//was WifiExample.txt
		String correctKeywords=helper4.toString().replaceAll("\r", "");
		
		//generatedFields=generatedFields.replaceAll("\r", "\n");
		
		//assert the the correct fields equal the generated fields
		//duck tape, I don't know why I have to trim header and body
		assertEquals(correctHeader.trim(),cont.getHeader());
		assertEquals(correctBody.trim(),cont.getBody());
		assertEquals(correctKeywords,cont.getKeywords());
	}
	
	/**
	 * Helper test method to sue a file to check sketch parsing
	 * @param exampleSketch the file containing the sketch to be parsed
	 * @param parsedExampleSketch the file containing the correctly parsed fields
	 */
	private void compareSketch(String exampleSketch,String parsedExampleSketch) {
		//load the Example Sketch from file
		ScriptEditor helper = new ScriptEditor(exampleSketch);
		String contents = helper.toString();
		//create SketchParser object to get fields from sketch
		SketchParser parser=new SketchParser(contents);
		String generatedFields=parser.toString();
		//load correct fields from memory
		ScriptEditor correctFile=new ScriptEditor(parsedExampleSketch);
		String correctFields=correctFile.toString();
		//replace special characters necessary for file comparisons
		correctFields=correctFields.replaceAll("\r", "");
		generatedFields=generatedFields.replaceAll("\r", "\n");
		//assert the the correct fields equal the generated fields
		assertEquals(correctFields,generatedFields);
	}

}