/**Name: Jacob Smith
  *Email:jsmith2021@brandeis.edu 
  *Date: May 24, 2019
  *Assignment: Personal Study, Parses a global variable with a comment
  *into a format usbale by arduino code generator	
  *Bugs:
  *Sources:
  */
package cc.ArduinoClassGenerator;

public class VariableParser {
	/*
	 * fields relevant to variable 
	 */
	private String type;
	private String comment;
	private String name;
	private String value;
	
	/**
	 * whether the variable has parens, needed in loadVal
	 */
	private boolean referenceVar;
	
	/**
	 * 
	 * @param comment the comment describing the variable
	 * @param temp the string containing the other variable information for parsing
	 */
	public VariableParser(String comment,String temp) {
		loadComment(comment);
		temp=clean(temp);
		//set up scanners to iterate through temp string
		MiniScanner varReader=new MiniScanner();
		varReader.prime(temp," ");
		//load fields into memory, note how type doesn't need scanner
		//and value doesn't need linked list, shows how I'm saving work
		LinkedList l=loadName(varReader,temp);
		loadType(l);
		loadVal(varReader);
	}
	
	/**
	 * Loads the comment and formats it in memory
	 * @param comment the unformatted comment
	 */
	private void loadComment(String comment) {
		comment=ArduinoParser.removeWhiteSpace(comment);
		comment=comment.replaceAll("/", "");
		//remove vertical bars to get rid of parsing errors, they are parsing token
		this.comment=comment.replace("|","");
	}
	/**
	 * Removes edge cases from formatting like a missing space for parsing
	 * @param temp the String holding variable information
	 * @return the string with entangling characters remove
	 */
	private String clean(String temp) {
		temp=temp.replace("|","");
		//insert spaces around special characters
		temp=temp.replace(";", " ;");
		temp=temp.replace("=", " = ");	
		temp=temp.replace("(", " ( ");
		temp=temp.replace(")"," )");
		temp=temp.replace("[]"," [] ");
		return temp;
	}
	
	/**
	 * Loads the name into memory and prepares a list to get the data type
	 * @param temp the string holding va`riable information
	 * @return a LinkedList containing the data type of the string
	 */
	private LinkedList loadName(MiniScanner varReader,String temp) {
		//create linked list to populate with data type and name
		String token="";
		LinkedList l=new LinkedList();
		//iterate through the temp string until you see equal, semicolon and paren are also possible stop markers
		while(varReader.hasNext() && !token.equals("=") && !token.equals(";")&&!(token.equals("("))) {
			if(!token.equals("")) {
				l.add(token);
			}
			token=varReader.next();
		}
		//store the tail in the linked list
		LinkedListNode tail=l.getTail();
		//if variable is array, swap bracket and name 
		if(tail.getVal().equals("[]")) {
			LinkedListNode name=tail.getPrev();
			l.switchNodes(name,tail);
		}
		//check for variable that is declared but not initialized
		//which means it would contain a semicolon as int num;
		if(token.contains(";")) {
			
		}
		//check for paren for an initialized reference variable
		if(token.contains("(")) {
			referenceVar=true;
		}
		//the name will be the last element of list
		this.name=l.getTail().getVal();
		//return the linked list to load the type
		return l;
	}
	
	/**
	 * Loads the type into memory by iterating over a linked list
	 * This is a linked list because a data type can be more than one word,
	 * like const char*
	 * @param l the linked list containing the type
	 */
	private void loadType(LinkedList l) {
		LinkedListNode n=l.getHead();
		type=n.getVal();
		//iterate along the linked list until you get to the name
		//to build the type
		while(!n.getNext().getVal().equals(this.name)) {
			n=n.getNext();
			type+=" "+n.getVal();
		}
		//trim the type of white space
		this.type=type.trim();
	}
	
	/**
	 * loads the variable value into memory
	 * @param varReader the scanner to iterate over the string containing the variable information
	 */
	private void loadVal(MiniScanner varReader) {
		//the candidate for variable value
		String temp="";
		//if this is a refernece variable, use getUntil method
		//to get parameters
		if(referenceVar) {
			temp=varReader.getUntil(")");
		//otherwise, the value is all of th rest of the tokens
		}else {
			//will ignore semicolon, if there isn't one, just returns rest of string
			temp=varReader.getUntil(";");
		}
		//if the variable has no value, make string blank instead of the word null
		if(temp==null) {
			temp="";
		}
		
		this.value=temp;
	}
	/**
	 * @Return a Formatted version of the variables suitable for 
	 */
	public String toString() {
		return type+"|"+name+"|"+comment+"|"+value+"|\n";
	}
	
	/**
	 * Shows the capabilities of the VariableParaser class by
	 * reformatting code into a more useful format
	 * @param args not used
	 */
	public static void main (String[]args) {
		//display explanation
		System.out.println("Shows the ability of this class to convert a variable");
		System.out.println("to a more parsable format");
		System.out.println("return type|name|comment|value|\n");
		//set up strings and parser
		String c1="//the password of the wifi network";
		String v1="const char* password = \"\"";
		VariableParser p1=new VariableParser(c1,v1);
		//print strings and parsed variable
		System.out.println("Comment:"+c1);
		System.out.println("Variable:"+v1);
		System.out.println("Parsed Output:"+p1);
		
	}
}
