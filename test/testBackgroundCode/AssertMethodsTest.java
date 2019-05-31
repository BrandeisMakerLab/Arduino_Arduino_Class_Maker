package testBackgroundCode;
import static org.junit.Assert.*;

import org.junit.Test;

/** Name: Jacob Smith
 *  Email:jsmith2021@brandeis.edu
 *  Assignment: Tests the correct function of the MiniScanner class, especially important because 
 *  	it is the basis of all parsing in the Arduino Project
 *  Date: May 15, 2019
 *  Sources: 
 *  Bugs:
 */


public class AssertMethodsTest {

	@Test
	/**makes sure that the arrEquals method throws an exception if either argument is null
	 * */
	public void arrEqualNull() {
	    String []arr={"a","b"};
		assertExceptionArrEquals(null,null,true);
		assertExceptionArrEquals(null,arr,true);
		assertExceptionArrEquals(arr,null,true);
		assertExceptionArrEquals(arr,arr,false);
		
	}
	
	@Test
	/**makes sure that the arrEquals method
	 * doesn't throw an exception when dealing with elements
	 * that are null
	 * */
	public void arrEqualExceptionNullElements() {
		//this behavior can't be tested with one array because
		//the function returns as soon as one element is different
		String []arr1={"a"};
		String[]arr2= {"a"};
		assertExceptionArrEquals(arr1,arr2,false);
		arr1[0]="a";
		arr2[0]= null;
		assertExceptionArrEquals(arr1,arr2,false);
		arr1[0]=null;
		arr2[0]="a";
		assertExceptionArrEquals(arr1,arr2,false);
		arr1[0]=null;
		arr2[0]=null;
		assertExceptionArrEquals(arr1,arr2,false);
	}
	
	@Test
	/**makes sure that the arrEquals method
	 * can compare arrays with null elements
	 * */
	public void arrEqualCorrectnessNullElements() {
		String [] ancd={"a",null,"c","d"};
		String [] ancd2={"a",null,"c","d"};
		String [] ance={"a",null,"c","e"};

		//different last element, false
		boolean result=AssertMethods.arrEquals(ancd, ance);
		assertEquals(result,false);
		//same last element true
		result=AssertMethods.arrEquals(ancd, ancd2);
		assertEquals(result,true);
	}
	
	
	@Test
	/**makes sure that the arrEquals method throws an exception if length of arrays is different
	 * */
	public void arrEqualLength() {
		String []length3={"a","b","c"};
		String[]length4={"a","b","c","d"};
		assertExceptionArrEquals(length3,length3,false);
		assertExceptionArrEquals(length4,length4,false);
		assertExceptionArrEquals(length3,length4,true);
		assertExceptionArrEquals(length4,length3,true);
	}
	
	@Test
	/**makes sure that the arrEquals method returns true if given matches correct and false otherwise
	 * */
	public void arrEqualMethods() {
		String [] abcd={"a","b","c","d"};
		String [] abcd2={"a","b","c","d"};
		String [] abce={"a","b","c","e"};
		//different last element, false
		boolean result=AssertMethods.arrEquals(abcd, abce);
		assertEquals(result,false);
		//same last element true
		result=AssertMethods.arrEquals(abcd, abcd2);
		assertEquals(result,true);
	
	}
	

	/**helper method to allow for testing an exception was thrown
	 * This helper method is in the test class because it can only be used to test one
	 * method
	 * */
	public void assertExceptionArrEquals(String[] givens, String[] correct,
			boolean shouldResults) {
		// set threw based on exception
		boolean threw = false;
		try {
			AssertMethods.arrEquals(givens, correct);
		} catch (Exception e) {
			threw = true;
		}
		// assert if an exception should have been thrown
		assertEquals(shouldResults,threw);
	}
	
	@Test
	/**makes sure that the assertEquals method returns stirng representing the summary comparison pf
	 * two input strings
	 * */
	public void assertEqualsFeedbackTest() {
		//test for correct behavior when string have same length and same shared part
		String message=AssertMethods.assertEqualsFeedback("Jacob", "Jacob");
		assertEquals(message,"Equal");
		
		//test for correct behavior when string have same length and different shared part
		message=AssertMethods.assertEqualsFeedback("Jacob", "Jocob");
		assertEquals(message,"The Strings differed at line:0 character:a index:1");
		
		//test for correct behavior when string have different length and same shared part
		message=AssertMethods.assertEqualsFeedback("Jacob", "JacobS");
		assertEquals(message,"Strings different length");
		
		//test for correct behavior when string have different length and different shared part
		message=AssertMethods.assertEqualsFeedback("Jacob", "JocobS");
		assertEquals(message,"The Strings differed at line:0 character:a index:1");
	}
	
	@Test
	/**makes sure that the line Number of the assertEquals Feedback method works properly
	 * */
	public void assertEqualsFeedbackLineNumTest() {
		//test for correct behavior difference is on line 0
		String message=AssertMethods.assertEqualsFeedback("Jacob", "Jocob");
		assertEquals(message,"The Strings differed at line:0 character:a index:1");
		
		//test for correct behavior difference is on line 1
		message=AssertMethods.assertEqualsFeedback("\nJacob", "\nJocob");
		assertEquals(message,"The Strings differed at line:1 character:a index:2");
				
		//test for correct behavior difference is on line 2
		message=AssertMethods.assertEqualsFeedback("\n\nJacob", "\n\nJocob");
		assertEquals(message,"The Strings differed at line:2 character:a index:3");
	
	}

	



}
