/** Name: Jacob Smith
 *  Email:jsmith2021@brandeis.edu
 *  Assignment: Personal Study, provides helper methods that can  be used with Junit tests
 *  to provide additional funcitonality and information
 *  Date: May 15, 2019
 *  Sources: 
 *  Bugs:
 *  Notes: I used to have custom methods to compare special characters abd line numbers of strings,
 *  but I decided to use a hardcoded string comparison which is moer explicit and doesn''t need those tools
 */
package testBackgroundCode;


public class AssertMethods {

	/**
	 * Helper method to compare two arrays of strings for Junit testing
	 */
	public static boolean arrEquals(String[] given, String[] correct) {
		if (given == null | correct == null) {
			throw new IllegalArgumentException("arrays can't be null");
		}
		// check lengths because assuming parallel arrays
		if (given.length != correct.length) {
			throw new IllegalArgumentException("arrays aren't same length");
		}
		// iterate through both arrays, if any elements aren't equal return
		// false
		for (int i = 0; i < correct.length; i++) {
			//if elements are both null, do nothing
			//avoid null pointer exception
			if(given[i]==null&&correct[i]==null){
			//the equals method handles other null case
			}else if(given[i]==null &&correct[i]!=null){
				return false;
			//then check if given isnt equal to correct
			}else if (!given[i].equals(correct[i])) {
				return false;
			}
		}
		// if all elements are equal, return true
		return true;
	}
	
	
	/**
	 *Helper method to compare two strings and assert whether they are equal
	 *but provide more helpful information than Junit assertEquals like line number and different character
	 * To use, just compare assertEquals(resultOfThisMehtod,Equal), 
	 */
	public static String assertEqualsFeedback(String a, String b){
		// return different index
		int diffIndex = getDiffIndex(a, b);
		if (diffIndex == -2) {
			return "Strings different length";
		}else if (diffIndex == -1) {
			return "Equal";
		//the test will pass automatically, strings are equal
		}else{
			return getErrorCompare(a,diffIndex);
		}
	}
	
	/**
	 * This isn't the same as getDiffIndex because I wanted to separate the funtionality,
	 * even though I am iterating over the string twice
	 * @param base the string to iterate over
	 * @param index the index to be examined
	 * @return a message containing the different character, index, and line number
	 */
	private static String getErrorCompare(String base, int index){
		char c=' ';
		int lineNum=0;
		for(int i=0;i<=index;i++){
			c=base.charAt(i);
			if(c=='\n'){
				lineNum++;
			}
		}
		return "The Strings differed at line:"+lineNum+" character:"+c+ " index:"+index;
	}

	/**
	 * Helper method to compare two string character by character
	 * 
	 * @return the index that the strings are different, -1 if they are equal,-2
	 *         if strings are different length and the common part is equal
	 */
	private static int getDiffIndex(String a, String b) {
		// check for edge cases and setup variables
		if (a == null | b == null) {
			throw new IllegalArgumentException("String can't be null");
		}
		int length = Math.min(a.length(), b.length());
		char aChar;
		char bChar;
		
		// go through both strings character by character
		for (int i = 0; i < length; i++) {
			aChar = a.charAt(i);
			bChar = b.charAt(i);
			if (aChar != bChar) {
				return i;
			}

		}
		// if strings are different length, return -2
		if (a.length() != b.length()) {
			return -2;
		}
		// otherwise, return -1
		return -1;

	}
	
}
