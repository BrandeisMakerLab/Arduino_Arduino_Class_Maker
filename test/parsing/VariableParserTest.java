/**Name: Jacob Smith
  *Email:jsmith2021@brandeis.edu 
  *Date: May 24, 2019
  *Assignment:	Personal Study, tests teh variable parser class,
  *which is a complicated taks because data types can be more than one word
  *and array variables are out of order
  *Bugs:
  *Sources:
  *Rights: Copyright (C) 2019 Jacob Smith
  *  	   License is GPL-3.0, included in License.txt of this github project
  */
package parsing;

import static org.junit.Assert.*;

import org.junit.Test;

import cc.arduinoclassmaker.VariableParser;

public class VariableParserTest {

	private String correctString1 = "const char*|password|the password of the wifi network|\"\"|\n";;
	private String correctString2 = "String|title|The title of the website|\"Deis Robotics Test Web Server\"|\n";
	@Test
	/**
	 * asserts that two example variables can be reformatted
	 * into the format of the arduino class creator
	 */
	public void testVarParsing() {
		String c1="//the password of the wifi network";
		String v1="const char* password = \"\";\r";
		VariableParser p1=new VariableParser(c1,v1);
		assertEquals(correctString1,p1.toString());
		
		String c2="//The title of the website";
		String v2="String title = \"Deis Robotics Test Web Server\"";
		VariableParser p2=new VariableParser(c2,v2);
		assertEquals(correctString2,p2.toString());
	}
	
	@Test
	/**
	 * Tests some strange but legal variable formatting that could
	 * stump the parser
	 */
	public void testVarEdgeCase() {
		//bars in comment and string value, which are parsing token
		String c1="//|||the password of the wifi network";
		String v1="const char* password = \"||\"";
		VariableParser p1=new VariableParser(c1,v1);
		assertEquals(correctString1,p1.toString());
		
		//no space at =
		String c2="//The title of the website";
		String v2="String title=\"Deis Robotics Test Web Server\"";
		VariableParser p2=new VariableParser(c2,v2);
		assertEquals(correctString2,p2.toString());
		
		//declared with parens
		String c3="//Set web server port number to 80";
		String v3="WiFiServer server(80);";
		VariableParser p3=new VariableParser(c3,v3);
		assertEquals("WiFiServer|server|Set web server port number to 80|80|\n",p3.toString());
		
	}
	
	@Test
	/**
	 * Tests parsing of array declaration
	 */
	public void testVarArr() {
		//array declaration with inializer list
		String c1="//an array for testing";
		String v1="String list[] = {\"This\",\"Is\",\"A\",\"Test\"};";
		VariableParser p1=new VariableParser(c1,v1);
		String correctString="String []|list|an array for testing|{\"This\",\"Is\",\"A\",\"Test\"}|\n";
		assertEquals(correctString,p1.toString());
	}
	
	@Test
	/**
	 * Tests parsing of declared but nor initialized value
	 */
	public void testVarDeclared() {
		//array declaration with initializer list
		String c1="//an unitialized variable";
		String v1="int test;";
		VariableParser p1=new VariableParser(c1,v1);
		String correctString="int|test|an unitialized variable||\n";
		assertEquals(correctString,p1.toString());
	}
}
