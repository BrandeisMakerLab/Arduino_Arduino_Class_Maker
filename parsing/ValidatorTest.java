/**Name: Jacob Smith
  *Email:jsmith2021@brandeis.edu 
  *Date: May 19, 2019
  *Assignment:	
  *Bugs:
  *Sources:
  */
package parsing;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidatorTest {

	@Test
	/**
	 * asserts that an error message will be returned if user enters
	 * blank input
	 */
	public void testIsAllowedBlankInput() {
		//makes sure that an input of only a space is an error, even when spaces are allowed
		String message = Validator.getErrorMessage(" ", true, null);
		assertEquals("input can't be blank", message);

		//makes sure that a blank input is an error
		message = Validator.getErrorMessage("", true, null);
		assertEquals("input can't be blank", message);

	}
	
	@Test
	/**
	 * make sure that getError message returns null when entered a null string
	 * instead of throwing an exception
	 */
	public void testNullArgumentErrorMessage() {
		//initialize message to blank string, but it should be null after method call
		String message="";
		//first, makre sure no exception is thrown with null argument
		try {
			message=Validator.getErrorMessage("Test", true, null);
		} catch (Exception e) {
			fail("This shouldn't have thrown an exception");
		}
		//if no exception is thrown, also make sure result is null;
		assertEquals(message,null);
	}

	@Test
	/**
	 * asserts that spaces can be allowed or not as specified
	 */
	public void testIsAllowedSpaces() {
		// make sure spaces are allowed when specified
		String s = "This is a test";
		String message = Validator.getErrorMessage(s, true, null);
		assertEquals(null, message);

		// assert that a message is presented when spaces are forbidden
		// shows that the same string can result on different behavior depending on what
		// user wants
		message = Validator.getErrorMessage(s, false, null);
		assertEquals("Character \"space\" is not allowed", message);

	}
	
	@Test
	/**
	 * asserts that special characters like newline are printed in human readable
	 * form
	 */
	public void testIsAllowedSpecialPrinting() {
		// make sure " " is printed as space, don't allow spaces to force error message
		String message = Validator.getErrorMessage("This is a test", false, null);
		assertEquals("Character \"space\" is not allowed", message);

		// make sure "\n" is printed as new line
		message = Validator.getErrorMessage("This\nis a\ntest", false, null);
		assertEquals("Character \"new line\" is not allowed", message);

		// make sure "\t" is printed as tab
		message = Validator.getErrorMessage("This	is a\ntest", false, null);
		assertEquals("Character \"tab\" is not allowed", message);

	}

	@Test
	/**
	 * asserts that non letter characters like | or ; aren't allowed This is
	 * important for parsing user input
	 */
	public void testForbiddenCharacters() {
		// see testIsAllowed special printing for error message with special characters
		// like newline

		// make sure ";" is allowed because of body statements
		String message = Validator.getErrorMessage("Test;", false, null);
		assertEquals(null, message);

		// make sure "|" is not allowed
		message = Validator.getErrorMessage("Test|", true, null);
		assertEquals("Character \"|\" is not allowed", message);
		
		//make sure _ is allowed
		message = Validator.getErrorMessage("Test_", true, null);
		assertEquals(null, message);
		
	}
	
	@Test
	/**
	 * asserts that an error message will be created if
	 * user enters a forbidden word
	 */
	public void testForbiddenWords() {
		//create array of forbidden words, and allow spaces so error will have to be about
		//the forbidden words
		String[]forbiddenWords= {"null","all"};
		
		//makes sure that input not containing forbidden words is allowed
		String message = Validator.getErrorMessage("should not generate an error", true, forbiddenWords);
		assertEquals(null, message);
		
		// make sure null is not allowed
		message = Validator.getErrorMessage("null should generate an error", true, forbiddenWords);
		assertEquals("word \"null\" is not allowed", message);

		// make sure all is not allowed
		message = Validator.getErrorMessage("all should generate an error", true, forbiddenWords);
		assertEquals("word \"all\" is not allowed", message);
		

	}

}
