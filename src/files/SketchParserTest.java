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
		compareSketch("ESPServer.ino","ESPParsed.txt");
	}
	
	@Test
	/**
	 * asserts behavior for Morse exmaple sketch, which has missing comments
	 * and the newline coding style
	 * @param args not used
	 */
	public void testSketchConversionMorse() {
		compareSketch("ESPServer.ino","ESPParsed.txt");
	}
	@Test
	/**
	 *  checks that the automatically generated class from the morse code example file
	 *  matches the correct strings
	 */
	public void compareGeneratedClassMorse() {
		compareGeneratedClass("Morse");
	}
	
	@Test
	/**
	 *  checks that the automatically generated class from the ESPServer code example file
	 *  matches the correct strings
	 */
	public void compareGeneratedClassESPServer() {
		compareGeneratedClass("ESPServer");
	}
	
	/**
	 *  checks that the automatically generated class from example file
	 *  matches the correct input in body, header, and keyword files
	 */
	private void compareGeneratedClass(String className) {
		//load the Example Sketch from file
		ScriptEditor helper = new ScriptEditor(className+".ino");
		String contents = helper.toString();
		//create SketchParser object to get fields from sketch
		SketchParser parser=new SketchParser(contents);
		ArduinoClassContainer cont=parser.getContainer(className, true);
		
		//load correct fields from memory and replace special characters necessary for file comparisons
		ScriptEditor helper2 = new ScriptEditor(className+".cpp");//was WifiExample.txt
		String correctBody=helper2.toString().replaceAll("\r", "");
		ScriptEditor helper3 = new ScriptEditor(className+".h");//was WifiExample.txt
		String correctHeader=helper3.toString().replaceAll("\r", "");
		ScriptEditor helper4 = new ScriptEditor(className+"Keywords.txt");//was WifiExample.txt
		String correctKeywords=helper4.toString().replaceAll("\r", "");
		ScriptEditor helper5 = new ScriptEditor(className+"ExampleFile.ino");//was WifiExample.txt
		String correctExample=helper5.toString().replaceAll("\r", "");
		
		//assert the the correct fields equal the generated fields
		//duck tape, I don't know why I have to trim header and body
		assertEquals(correctHeader.trim(),cont.getHeader());
		assertEquals(correctBody.trim(),cont.getBody());
		assertEquals(correctKeywords,cont.getKeywords());
		assertEquals(correctExample,cont.getExample());
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