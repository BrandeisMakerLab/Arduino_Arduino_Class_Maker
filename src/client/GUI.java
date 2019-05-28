package client;
/*https://coderanch.com/t/340772/java/convert-Console-App-GUI
https://examples.javacodegeeks.com/desktop-java/swing/jtextfield/create-read-only-non-editable-jtextfield/
https://stackoverflow.com/questions/25513711/how-to-make-my-textfield-bigger-for-gui-in-java
*/

import javax.swing.*;

import classGenerators.ArduinoClassCpp;
import classGenerators.ArduinoClassH;

import java.awt.event.*;
import java.awt.*;
class GUI extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean ready;
	private JTextArea []textBoxes;
  public GUI()
  {
	 ready=false;
	//loadFile();
    setLocation(400,300);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    JPanel jp = new JPanel(new GridLayout(20,2));//was 4 1
	//header label
	JLabel lb0 = new JLabel("Welcome to the Arduino Library Template Maker");   
	jp.add(lb0);
	jp.add(new JLabel("please enter fields and press button"));
	//body labels
	String []fields={"className [Template]","author [John Doe]","organization [Brandeis Univeristy]","headerComments [This class...]","supportedBoards [ARDUINO_AVR_UNO]","variables [long time]","publicMethods [int|resetTime|resets the time]time=0;"};
	String[]examples= {"Timer","Jacob Smith","Brandeis Robotics Club","A timer class to allow the user to create loops and maintain program control","ARDUINO_AVR_UNO ESP8266_WEMOSD1R1","long time\nApple test","initTime=millis();\n\nlong|resetTime|resets the Initial Time|\ninitTime=millis();\nreturn getTime();\n\n"};
	
	

	textBoxes= new JTextArea[fields.length+1];
	for (int i=0;i<fields.length;i++){
		createField(fields,examples,i,jp);
	}
	

	//output text field
	JButton btn = new JButton("Please enter fields to edit Arduino class from template");
    btn.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        updateClass(jp);}}); ;
	jp.add(btn);  
	ready=true;
    getContentPane().add(jp);
    pack();
	
	
  }
  public static void main(String[] args){
	  //set up GUI
	  
	  new GUI().setVisible(true);
	 
}

/*Creates a label and text box for a given field*/
private void createField(String [] fields,String []examples,int index,JPanel jp) {
	JLabel lbl = new JLabel("Please enter "+fields[index]+" here");
	final JTextArea tf = new JTextArea(examples[index]);
	textBoxes[index]=tf;
	tf.setText(examples[index]);
	jp.add(lbl);
	jp.add(tf);
	if(ready) {
		System.exit(1);
		getContentPane().removeAll();
	}
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
