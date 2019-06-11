/**Name: Jacob Smith
  *Email:jsmith2021@brandeis.edu 
  *Date: May 29, 2019
  *Assignment:Personal Study, tests the ArduinoClassExampleSketch class
  *Bugs:
  *Sources:
  *Rights: Copyright (C) 2019 Jacob Smith
  *  	   License is GPL-3.0, included in License.txt of this github project
  */
package classGenerators;

import org.junit.Test;

import cc.ArduinoClassGenerator.ArduinoClassExample;
import cc.ArduinoClassGenerator.ArduinoClassExampleSketch;
import cc.ArduinoClassGenerator.ExampleSketch;
import testBackgroundCode.AssertMethods;

public class ArduinoClassExampleSketchTest {

	@Test
	/**
	 * asserts that the class can generate an example file for the Timer class
	 */
	public void testExampleFile() {
		//create an ArduinoClassExampleSketch object using hardcoded example fields
		ArduinoClassExampleSketch template = new ArduinoClassExampleSketch(ArduinoClassExample.CLASSNAME.toString(),
			ArduinoClassExample.AUTHOR.toString(),
			ArduinoClassExample.ORGANIZATION.toString(), 
			true,
			ArduinoClassExample.HEADERCOMMENTS.toString(), 
			ArduinoClassExample.SUPPORTEDBOARDS.toString(),
			ExampleSketch.SKETCHMETHODS.toString(),ArduinoClassExample.PUBLICMETHODS.toString());
		//print the generated example sketch
		String generatedExample=template.toString();
		AssertMethods.compareFiletoString("testing_files\\TimerExample.ino", generatedExample);
	}

}