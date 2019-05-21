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
