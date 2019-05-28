/**Name: Jacob Smith
  *Email:jsmith2021@brandeis.edu 
  *Date: May 19, 2019
  *Assignment:	Personal Study, provides a library of helper methods useful in validating user input for the Arduino
  *Class Generator Project
  *Bugs:
  *Sources:http://www.informit.com/articles/article.aspx?p=30241&seqNum=3
  */
package parsing;

public class Validator {

	/**
	 * 
	 * @param input
	 *            the input the user entered
	 * @param allowSpaces
	 *            whether spaces should be allowed in the input
	 * @param forbiddenInputs
	 *            an array of forbidden inputs
	 * @return an error message if input has forbidden element, or null if input is
	 *         valid
	 */
	public static String getErrorMessage(String input, boolean allowSpaces, String[] forbiddenInputs) {
		//check to see if input only contains spaces, which must be speicifcally checked for and is never allowed
		//also check for blank String
		if(containsOnly(input,' ')|input.equals("")) {
			return "input can't be blank";
		}
		// check to see if user entered alternate message
		String message = enteredForbidden(input, forbiddenInputs);
		if (message != null) {
			return message;
		}
		// check for special characters, depending on whether user allowed spaces
		message = Validator.isAllowed(input, allowSpaces);
		// this is the last check so it will either be a string or null if input is
		// allowed
		return message;

	}
	

	/**
	 * checks to see if user entered a forbidden message
	 */
	private static String enteredForbidden(String input, String[] forbiddenMessages) {
		// if array is null, return null
		if (forbiddenMessages == null) {
			return null;
		}
		String forbiddenMessage;
		// iterate through forbidden messages array
		for (int i = 0; i < forbiddenMessages.length; i++) {
			// store lowercase version of forbidden messages
			forbiddenMessage = forbiddenMessages[i].toLowerCase();
			// if input contains a forbidden message, return error messsage
			if (input.toLowerCase().contains(forbiddenMessage)) {
				return "word \"" + forbiddenMessage + "\" is not allowed";
			}
		}
		// if no element in array was a match, return null
		return null;
	}

	/**
	 * @param allowSpaces
	 *            whether spaces should be allowed
	 * @param input
	 *            the string the user entered
	 * @return null if input only has allowed characters, an error message if input
	 *         has unallowed character
	 */
	private static String isAllowed(String input, boolean allowSpaces) {
		char c;
		for (int i = 0; i < input.length(); i++) {
			c = input.charAt(i);
			if (!isAllowed(input.charAt(i), allowSpaces)) {
				return "Character \"" + printSpecialChar(c) + "\" is not allowed";
			}
		}
		// if here, input is correct and return null
		return null;
	}

	/**
	 * helper method to check if a user entered character is allowed
	 * 
	 * @param allowSpaces
	 *            whether spaces should be allowed
	 * @param c
	 *            the character to check
	 * @return whether that character is allowed
	 */
	private static boolean isAllowed(char c, boolean allowSpaces) {
		// letters are not allowed
		if (isLetter(c)) {
			return true;
		// spaces are allowed if specified
		} else if (allowSpaces && c == ' ') {
			return true;
		//allow numbers
		}else if (isNumber(c)) {
			return true;
		//allow the underscore character
		}else if (c=='_') {
			return true;
		//allow programming characters because of method bodies
		}else if (c==';'|c=='('|c==')'|c=='+'|c=='-'|c==','|c=='='|c=='"'|c=='.') {
			return true;
		}
		// otherwise, assume input is not allowed
		return false;
	}

	/**
	 * helper method to return a printable String verison of a specialcharacter so
	 * newline will print as "new line" instead of an actual new line helpful in
	 * printing error messsages form user input
	 * 
	 * @param c
	 *            the special character to print
	 * @return a human readable version of the character
	 */
	private static String printSpecialChar(char c) {
		if (c == '\n') {
			return "new line";
		} else if (c == '\t') {
			return "tab";
		} else if (c == ' ') {
			return "space";
			// if there is some other special character, print it directly
		} else {
			return String.valueOf(c);
		}
	}

	/**
	 * 
	 * @param c
	 *            the characer to check
	 * @return true if character is a letter, false otherwise
	 */
	private static boolean isLetter(char c) {
		// check if character is a lowercase letter
		if (c >= 'a' && c <= 'z') {
			return true;
		}
		// check if character is an upperase letter
		if (c >= 'A' && c <= 'Z') {
			return true;
		}
		// if character is neither, return false
		return false;
	}
	
	/**
	 * @param c the charater to check
	 * @return wheher the character is a number
	 */
	private static boolean isNumber(char c) {
		//return true if the character has the code of a digit
		if(c>='0' && c<='9') {
			return true;
		}
		return false;
	}
	
    /**
    * Helper method for validating user input
    * @param input the string to be checked
    * @param key the ke sequence to be used as baseline
    * @return whether the input contains only the key
    */
	private static boolean containsOnly(String input, char key) {
		//iterata through input string
		for(int i=0;i<input.length();i++) {
			//if any character of the input string doesn't match the key, return false
			if(input.charAt(i)!=key) {
				return false;
			}
		}
		//if the whole string was iterated through can only contains the key, return true
		return true;
	}

	/**
	 * demonstrates some test cases of the class see junit test for complete
	 * documentation
	 * 
	 * @param args
	 *            not used
	 */
	public static void main(String[] args) {
		
		String[] forbiddenInputs = {"all","none"};
		System.out.println("INPUT\t\t\tISALLOWED?");

		String s = "This is a test";
		System.out.println(s + "\t\t" + getErrorMessage(s, false, forbiddenInputs));

		s = "This is a test";
		System.out.println(s + "\t\t" + getErrorMessage(s, true, forbiddenInputs));

		s = "This is all a test";
		System.out.println(s + "\t" + getErrorMessage(s, true, forbiddenInputs));
		
		s = " ";
		System.out.println(s + "\t\t\t" + getErrorMessage(s, true, forbiddenInputs));
	}
}