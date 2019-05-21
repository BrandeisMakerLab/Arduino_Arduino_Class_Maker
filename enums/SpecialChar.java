/** Name: Jacob Smith
 *  Email:jsmith2021@brandeis.edu
 *  Assignment: Personalt Study, an enum to keep track of special characters
 *  	and to automatically allow them to be be printed explicitely
 *  	So this can convert an invsibe newline to the word newline
 *  	Useful in user input validation 
 *  Date: May 16, 2019
 *  Sources: 
 *  Bugs:
 */
package enums;

public enum SpecialChar {
	
	NEWLINE("\n","\\n","new line"),TAB("\t","\\t","tab"),BLANK("","","blank"),BAR("|","|","bar");
	
	private String sequence;
	private String visible;
	private String seqName;
	
	/**
	 * creates a new Character Enum
	 */
	private SpecialChar(String sequence,String visible,String seqName){
		this.sequence=sequence;
		this.visible=visible;
		this.seqName=seqName;

	}
	
	/**
	 * returns the sequence of this enum
	 */
	public String getSequence(){
		return sequence;
	}
	
	/**
	 * returns the visible sequence of this enum, such as
	 * with an escape sequence
	 */
	public String getVisible(){
		return visible;
	}
	
	/**
	 * returns the name of the sequence for this enum
	 * which is more convienent than converting the enum to a 
	 * lowercase string
	 */
	public String getseqName(){
		return seqName;
	}
	
	/**
	 * displays the properties of this enum
	 */
	public static void main(String[]args){
		for(SpecialChar c:SpecialChar.values()){
			System.out.println(c.seqName+": "+c.visible);
		}
	}
} 
