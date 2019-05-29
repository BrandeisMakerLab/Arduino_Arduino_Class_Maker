package client;
/*https://coderanch.com/t/340772/java/convert-Console-App-GUI
https://examples.javacodegeeks.com/desktop-java/swing/jtextfield/create-read-only-non-editable-jtextfield/
https://stackoverflow.com/questions/25513711/how-to-make-my-textfield-bigger-for-gui-in-java
*/

import javax.swing.*;

import classGenerators.ArduinoClassCpp;
import classGenerators.ArduinoClassH;

class TemplatetoLibrary extends GUI
{
	private static final long serialVersionUID = 1L;

	/**
	 * @param title
	 * @param generalPrompt
	 * @param fields
	 * @param examples
	 * @param buttonName
	 */
	public TemplatetoLibrary(String title, String generalPrompt, String[] fields, String[] examples,
			String buttonName) {
		super(title, generalPrompt, fields, examples, buttonName);
		// TODO Auto-generated constructor stub
	}
	
  public static void main(String[] args){
	  //set up GUI
	  String title="Welcome to the Arduino Library Template Maker";
	  String generalPrompt="please enter fields and press button";
	  String []fields={"className [Template]","author [John Doe]","organization [Brandeis Univeristy]","headerComments [This class...]","supportedBoards [ARDUINO_AVR_UNO]","variables [long time]","publicMethods [int|resetTime|resets the time]time=0;"};
	  String[]examples= {"Timer","Jacob Smith","Brandeis Robotics Club","A timer class to allow the user to create loops and maintain program control","ARDUINO_AVR_UNO ESP8266_WEMOSD1R1","long time\nApple test","initTime=millis();\n\nlong|resetTime|resets the Initial Time|\ninitTime=millis();\nreturn getTime();\n\n"};
	  String buttonName="Please enter fields to edit Arduino class from template";
	  new TemplatetoLibrary(title,generalPrompt,fields,examples,buttonName).setVisible(true);
	 
}

public void updateClass(JPanel jp){
	ArduinoClassCpp body=new ArduinoClassCpp(textBoxes[0].getText(),textBoxes[1].getText(),textBoxes[2].getText(),textBoxes[3].getText(),textBoxes[4].getText(),textBoxes[5].getText(),null,textBoxes[6].getText());
	ArduinoClassH header=new ArduinoClassH(textBoxes[0].getText(),textBoxes[1].getText(),textBoxes[2].getText(),textBoxes[3].getText(),textBoxes[4].getText(),textBoxes[5].getText(),null,textBoxes[6].getText());
	//jp.removeAll();
	//final JTextArea tf3 = new JTextArea(20,20);
    //tf3.setEditable(false);
    //jp.add(tf3);
    //tf3.setText(body.toString());
	System.out.println(body);
	System.out.println(header);
	System.out.println(header.getKeywords());
	//close winow automatically after button press
	try {
		Thread.sleep(10000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	this.setVisible(false);

}
}
