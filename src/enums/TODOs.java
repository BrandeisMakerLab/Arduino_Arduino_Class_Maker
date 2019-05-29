/**Name: Jacob Smith
  *Email:jsmith2021@brandeis.edu 
  *Date: May 29, 2019
  *Assignment:	Personal Study, convenient place to put 
  *TODO messages generated when user doesn't enter a rewuired field
  *Bugs:
  *Sources:
  */
package enums;

public enum TODOs {
	/**
	 * hardcoded message fields
	 */
	HeaderComment("TODO: explain what program is for and give your name and who can use this program"),
	Method("TODO: explain what method does"),Variable("TODO: explain what variable is for");
	/**
	 * the message to be displayed
	 */
	private String message;
	/**
	 * creates a new TODO object
	 * @param message the message to be displayed
	 */
	private TODOs (String message) {
		this.message=message;
	}
	
	/**
	 * demonstrates the enums available
	 * @param args not used
	 */
	public static void main (String[]args) {
		TODOs[] messages=TODOs.values();
		for(int i=0;i<messages.length;i++) {
			System.out.println(messages[i]);
		}
	}
	
	/**
	 * @return the message
	 */
	public String toString(){
		return message;
	}
}
