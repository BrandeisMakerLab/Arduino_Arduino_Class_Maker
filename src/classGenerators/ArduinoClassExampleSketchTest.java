/**Name: Jacob Smith
  *Email:jsmith2021@brandeis.edu 
  *Date: May 29, 2019
  *Assignment:Personal Study, tests the ArduinoClassExampleSketch class
  *Bugs:
  *Sources:
  */
package classGenerators;

import org.junit.Test;

import enums.ArduinoClassExample;
import enums.ExampleSketch;
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
			ExampleSketch.SETUPMETHOD.toString(),
			ExampleSketch.LOOPMETHOD.toString());
		//print the generated example sketch
		String generatedExample=template.toString();
		AssertMethods.compareFiletoString("TimerExample.ino", generatedExample);
	}

}
