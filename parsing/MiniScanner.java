package parsing;

/* Name: Jacob Smith
 Date: May 13 2019
 Assignment: Personal Study, allows the user to create a scanner object which iterates
 over a base string and returns one token at a time
 Email: jsmith2021@brandeis.edu
 Throw error if hasNext was called twice in a row at end of input
 The expected behavior of the class is that a new instance will be created, then
 the prime method will be called whenever a string needs to be iterated over.
 If the user calls hasNext or next before the scanner is primed, an exception is thrown
 If there is more string to process, hasNext returns true
 Next returns the next String to process, null if there is no next string
 With this scheme, hasNext and next can be called forever with no exception as long as scanner is primed

 */

public class MiniScanner {

	private boolean hasNext;
	private boolean alreadyChecked;
	private boolean primed;
	private String base;
	private String token;
	private int curIndex;

	private String nextString;

	/** instantiates a MiniScanner object */
	public MiniScanner() {
		alreadyChecked = false;
		initClass();
	}

	/**
	 * Helper method to initialize or reset all of the classes instance
	 * variables
	 */
	public void initClass() {
		base = null;
		token = null;
		curIndex = 0;
		nextString = null;
		alreadyChecked = false;
	}

	/**
	 * Shows an example file with this class
	 */
	public static void main(String[] args) {
		// this example iterates over a base stirng with spaces used as a token
		MiniScanner reader = new MiniScanner();

		System.out.println("test prime with base string separated by spaces");
		reader.prime("This is a test", " ");
		while (reader.hasNext()) {
			System.out.println(reader.next());
		}

		System.out.println("test prime with base string separated by colons");
		reader.prime("This:is:also:a:test", ":");
		while (reader.hasNext()) {
			System.out.println(reader.next());
		}

	}

	/** Gets a MiniScanner reader to iterate over the MiniScanner */
	public void prime(String base, String token) {
		alreadyChecked = false;
		this.base = base;
		this.token = token;
		curIndex = 0;
		primed = true;

	}

	/**
	 * returns whether the scanner is reader to iterate over updates the
	 * nextString field
	 */
	public boolean hasNext() {
		// check if the scanner was primed with a base integer
		checkPrimed();
		// if the user called next twice, just return the last result
		if (alreadyChecked) {
			return hasNext;
		} else {// otherwise, state that this was already checked
			alreadyChecked = true;
		}
		// otherwise, check for the next element
		return getNextString();

	}

	/**
	 * helper method iterates along the base string to get the next piece
	 * 
	 * @return the next string to process which can't be null
	 */
	private boolean getNextString() {
		// if the base string is null, set the scanner to return null and sreturn false
		if (base == null) {
			hasNext=false;
			alreadyChecked = true;
			nextString = null;
			return false;
		}

		// return whether there is a next instance of the token
		int nextIndex = base.indexOf(token, curIndex);
		boolean hasNextToken = nextIndex != -1;

		// if there is a next token, update nextString field return true
		if (hasNextToken) {
			nextString = base.substring(curIndex, nextIndex);
			curIndex = nextIndex + token.length();
			return true;
		}
		// otherwise, see if you are at the last index
		// and if you are update nextString with the rets of the string
		if (curIndex < base.length()) {
			nextString = base.substring(curIndex, base.length());
			curIndex = base.length();
			return true;
		}
		// if there is no next String, reset the class
		// but say that already checked is true, becuase there are no more
		// strings to return
		// this means next will return null until a new prime occurs
		initClass();
		alreadyChecked = true;
		return false;
	}

	/**
	 * returns the next string, which will be null if there is no next string
	 * */
	public String next() {
		// if scanner wasn't initially primed, throw exception
		checkPrimed();
		// this gets the next string if hasNext wasn't called
		if (!alreadyChecked) {
			hasNext();
		}
		alreadyChecked = false;
		// return the nextString and set it to null
		String temp = nextString;
		nextString = null;
		return temp;

	}

	/** throws an exception if the user hasn't primed the MiniScanner */
	private void checkPrimed() {
		if (!primed) {
			throw new IllegalArgumentException(
					"Error, Scanner not primed. Maybe you called next an extra time?");
		}
	}

}