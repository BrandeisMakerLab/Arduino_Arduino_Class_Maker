package parsing;
import static org.junit.Assert.*;

import org.junit.Test;

import testBackgroundCode.AssertMethods;


/**
 * Name: Jacob Smith Email:jsmith2021@brandeis.edu Assignment: Tests the correct
 * function of the MiniScanner class, especially important because it is the
 * basis of all parsing in the Arduino Project Date: May 15, 2019 Sources: Bugs:
 */

public class MiniScannerTest {

	@Test
	/**
	 * Tests that scanner will parse correctly when hasNext is not called
	 * 
	 */
	public void testNoHasNext() {
		// this example iterates over a base stirng with spaces used as a token
		MiniScanner reader = new MiniScanner();
		//test that a scanner won't throw exception if hasNext is called 6 times
		reader.prime("This is a test", " ");
		String[] correct = { "This", "is", "a", "test" };
		String[]parsed=new String[4];
		parsed[0]=reader.next();
		parsed[1]=reader.next();
		parsed[2]=reader.next();
		parsed[3]=reader.next();
		boolean result=AssertMethods.arrEquals(correct,parsed);
		assertEquals(result,true);
		
	}
	@Test
	/**
	 * Tests that the scanner won't show an exception when hasNext is called many times
	 * and when next is called many times
	 * 
	 */
	public void exceptionSafeNext() {
		// this example iterates over a base stirng with spaces used as a token
		MiniScanner reader = new MiniScanner();

		//test that a scanner won't throw exception if hasNext is called 6 times
		reader.prime("This is a test", " ");
		for(int i=0;i<5;i++){
			assertExceptionReader(reader,false,false);
		}
		
	}
	
	@Test
	/**
	 * Tests that the scanner won't rhow an exception when hasNext is called many times
	 * and when next is called many times
	 * 
	 */
	public void exceptionSafeHasNext() {
		// this example iterates over a base stirng with spaces used as a token
		MiniScanner reader = new MiniScanner();

		//test that a scanner won't throw exception if hasNext is called 6 times
		reader.prime("This is a test", " ");
		for(int i=0;i<5;i++){
			assertExceptionReader(reader,true,false);
		}
		
	}
	
	@Test
	/**
	 * Tests that the scanner won't throw an exception if base string is null
	 * on method call hasNext
	 * 
	 */
	public void nullBaseHasNext() {
		// this example iterates over a base stirng with spaces used as a token
		MiniScanner reader = new MiniScanner();

		//test that a scanner won't throw exception if hasNext is called 6 times
		reader.prime(null, " ");
		for(int i=0;i<5;i++){
			assertExceptionReader(reader,true,false);
		}
		
	}
	
	@Test
	/**
	 * Tests that the scanner won't throw an exception if base string is null
	 * with call to next method
	 */
	public void nullBaseNext() {
		// this example iterates over a base stirng with spaces used as a token
		MiniScanner reader = new MiniScanner();

		//test that a scanner won't throw exception if hasNext is called 6 times
		reader.prime(null, " ");
		for(int i=0;i<5;i++){
			assertExceptionReader(reader,false,false);
		}
		
	}
	
	@Test
	/**
	 * Tests that the scanner can parse one string corredctly, be reset, and parse another string correctly
	 */
	public void testTokens() {
		// this example iterates over a base stirng with spaces used as a token
		MiniScanner reader = new MiniScanner();

		// test prime with base string separated by spaces
		reader.prime("This is a test", " ");
		String[] correct = { "This", "is", "a", "test" };
		assertReader(reader,correct);
		
		// test prime with base string separated by colons
		reader.prime("This:is:a:test", ":");
		assertReader(reader,correct);
		
	
		
	}
	
	@Test
	/**
	 * asserts that the miniScanner can parse a method header
	 * useful in creating arduino class from method body
	 */
	public void testReaderMethod() {
		MiniScanner methodReader=new MiniScanner();
		
		//create array of correct strings
		String []correct= {"void","loop(){"};
		//set scanner to iterate over test string
		methodReader.prime("void loop(){"," ");
		//assert that the scanner will return the correct tokens
		assertReader(methodReader,correct);
		
		//repeat for alternate method header
		String[]correct2= {"void","loop","()","{"};
		methodReader.prime("void loop () {", " ");
		assertReader(methodReader,correct2);
		
		//repeat for alternate method header
		String[]correct3= {"void","loop","(){"};
		methodReader.prime("void loop (){", " ");
		assertReader(methodReader,correct3);
	}

	@Test
	/**
	 * asserts that the next error checking method
	 * throws an IllegalArgument exception when called and
	 * there is no next String
	 * useful in creating arduino class from method body
	 */
	public void testNextCheckError() {
		MiniScanner reader=new MiniScanner();
		reader.prime("noNextString", " ");
		reader.next();
		try {
			reader.next("word");
			//if an exception is not thrown, control flow will go to next line
			fail("An Exception should have been thrown");
		}catch(IllegalArgumentException e) {
			assertEquals("Error, Expected word but not found",e.getMessage());
		}
	}

	@Test
	/**
	 * Asserts that the getRets method returns the rest of base string
	 */
	public void testGetRest() {
		MiniScanner reader=new MiniScanner();
		reader.prime("This is a test"," ");
		//consume the first word
		reader.next("\"This\"");
		//get the rest
		String rest=reader.getRest();
		assertEquals("is a test",rest);
		
	}
	
	@Test
	/**
	 * Asserts that the getUntil method returns the string up to but not including
	 * the end token
	 */
	public void testGetUntil() {
		MiniScanner reader=new MiniScanner();
		reader.prime("( This is a test ) ;"," ");
		//make sure first token is open paren
		if(!reader.next().equals("(")) {
			fail("First token should be open paren");
		}
		//get the rest
		String rest=reader.getUntil(")");
		assertEquals("This is a test",rest);
		
	}
	
	@Test
	/**
	 * asserts that the MiniScanner can ignore multiple tokens in a roq
	 */
	public void testMultitokens() {
		//prime the MiniScanner with multi token input
		MiniScanner reader=new MiniScanner();
		reader.prime("a  b   c"," ");
		//make sure MiniScanner can skip over multiple spaces
		assertEquals("a",reader.next());
		assertEquals("b",reader.next());
		assertEquals("c",reader.next());
	}
	
	@Test
	/**
	 * asserts that the MiniScanner will return a blank string if token is in a row
	 * This is a special behavior because the token isn't a space, see Multitokens test
	 */
	public void testBlankInput() {
		//prime the MiniScanner with multi token input
		MiniScanner reader=new MiniScanner();
		reader.prime("|a||b|c","|");
		//make sure MiniScanner can return blank string
		assertEquals("",reader.next());
		assertEquals("a",reader.next());
		assertEquals("",reader.next());
		assertEquals("b",reader.next());
		assertEquals("c",reader.next());
	}
	
	/**
	 * asserts that a given reader that has been primed matches
	 * the array of expected results
	 * */
	private void assertReader (MiniScanner reader,String[]correct){
		//create an array to hold the parsed results
		String[]parsed=new String[correct.length];
		//populate an array with the returned results
		int index=0;
			while (reader.hasNext() && index<parsed.length) {
				parsed[index]=reader.next();
				index++;
			}
			//if there is a next token, return false
			boolean result;
			if(reader.hasNext()){
				assertEquals(false,true);//there shouldn't be a next token
			}else{
				result=AssertMethods.arrEquals(parsed,correct);
				assertEquals(result,true);
			}
	}
		
	/**
	 * helper method to test whether an exception was thrown
	 * @testHasNext true to testHasNext, false to test Next
	 */
	private void assertExceptionReader(MiniScanner reader,
			boolean testHasNext,boolean shouldThrow) {
		// set threw based on exception
		boolean threw = false;
		try {
			if(testHasNext){
				reader.hasNext();
			}else{
				reader.next();
			}
			
		} catch (Exception e) {
			threw = true;
		}
		// assert if an exception should have been thrown
		assertEquals(threw, shouldThrow);
	}

}
