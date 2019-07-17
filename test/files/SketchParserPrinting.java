/**Name: Jacob Smith
  *Email:jsmith2021@brandeis.edu 
  *Date: Jul 14, 2019
  *Assignment:	Personal STudy, verifies the console behavior of SketchParser class.
  *When integrated to the Arduino IDE, system.out.printlnt is an acceptable output.
  *Bugs:
  *Sources:https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
  *Rights:  Copyright (C) 2019 Jacob Smith
  *  		License is GPL-3.0, included in License.txt of this github project
  */
package files;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SketchParserPrinting {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setErr(originalErr);
	}
	//sample test cases:

	@Test
	public void out() {
	    System.out.print("hello");
	    assertEquals("hello", outContent.toString());
	}

	@Test
	public void err() {
	    System.err.print("hello again");
	    assertEquals("hello again", errContent.toString());
	}
}
