import java.util.regex.Pattern;

/**Name: Jacob Smith
  *Email:jsmith2021@brandeis.edu 
  *Date: May 28, 2019
  *Assignment:	
  *Bugs:
  *Sources:
  */

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String base="void setup()\n{\n}";
		System.out.println(base);
		base=replaceAllSimple(base,")\n{","){");
		System.out.println(base);
		

	}
	
	/**
	 * Helper parsing method, replaces literal sequences instead of regular expressions
	 * @param base the string to itrate over
	 * @param toReplace the unwanted sequence
	 * @param replaceWith the wanted sequence
	 * @return the base string with the unwanted pattern replaced with the wanted pattern
	 */
	private static String replaceAllSimple(String base,String toReplace,String replaceWith) {
		String temp;
		for(int i=0;i<base.length()-toReplace.length();i++) {
			temp=base.substring(i, i+toReplace.length());
			if(temp.equals(toReplace)) {
				base=base.substring(0,i)+replaceWith+base.substring(i+toReplace.length(),base.length());
			}
		}
		return base;
	}

}
