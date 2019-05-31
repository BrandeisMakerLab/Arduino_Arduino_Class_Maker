/**Name: Jacob Smith
  *Email:jsmith2021@brandeis.edu 
  *Date: May 23, 2019
  *Assignment:	Personal Study, Tests the Parsed Method class
  *Bugs:
  *Sources:
  */
package parsing;

import static org.junit.Assert.*;

import org.junit.Test;

import testBackgroundCode.AssertMethods;

public class ParsedMethodTest {
    //a method representation using newLine characters
	private String methodRepN=
		"//a test method to average two numbers\n"
		+"int average(int a, int b) {\n"
		+"  int sum=a+b;\n"
		+"  int average=sum/2;\n"
		+"  return average;\n"
	    +"}";
	//a method representation using R characters for newline
	private String methodRepR=
		"//a test method to average two numbers\r"
		+"int average(int a, int b) {\r"
		+"  int sum=a+b;\r"
		+"  int average=sum/2;\r"
		+"  return average;\r"
	    +"}";
	//the correctly formatted method for both strings
	private String correctParsedMethod=
			"int|average|int a, int b|a test method to average two numbers|\n"
			+"int sum=a+b;\n"
			+"int average=sum/2;\n"
			+"return average;\n\n";
	
	@Test
	/**
	 * makes sure that the method string using \n characters
	 * can be correcly parsed
	 */
	public void testParsedMethodN() {
		//Creating a ParsedMethod object 
		ParsedMethod p=new ParsedMethod(methodRepN);
		//assert that the created object generates the same method output as the correct string
		assertEquals(correctParsedMethod,p.toString());
	}
	
	@Test
	/**
	 * makes sure that the method string using \r characters
	 * can be correcly parsed
	 */
	public void testParsedMethodR() {
		//Creating a ParsedMethod object 
		ParsedMethod p=new ParsedMethod(methodRepR);
		//assert that the created object generates the same method output as the correct string
		AssertMethods.assertEqualsFeedback(correctParsedMethod, p.toString());
		assertEquals(correctParsedMethod,p.toString());
	}
}
