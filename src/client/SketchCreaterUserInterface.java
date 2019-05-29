/**Name: Jacob Smith
  *Email:jsmith2021@brandeis.edu 
  *Date: May 29, 2019
  *Assignment: Personal Study, allows the user to convert an Arduino sketch into a lirbary,
  *the core of the ArduinoClass Generator project	
  *Bugs:
  *Sources:https://stackoverflow.com/questions/11109450/how-do-i-remove-a-component-from-a-jpanel-and-then-redisplay-the-frame
  *https://www.reddit.com/r/java/comments/2ok338/switching_jpanel_when_clicking_on_a_jbutton_swing/
  */
package client;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import files.ScriptEditor;
import files.SketchParser;


public class SketchCreaterUserInterface extends GUI {

	/**
	 * @param title
	 * @param generalPrompt
	 * @param fields
	 * @param examples
	 * @param buttonName
	 */
	public SketchCreaterUserInterface(JFrame frame,String title, String generalPrompt, String[] fields, String[] examples,
			String buttonName, int x, int y, int windowWidth, int windowLength,Font font) {
		super(title, generalPrompt, fields, examples, buttonName,x,y,font);
	
	}

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		// set up GUI
		String title = "Welcome to the Arduino Sketch to Library Converter";
		String generalPrompt = "please enter fields and press button";
		String[] fields = { "className [Template]", "Sketch path" };
		String[] examples = { "Morse", "testing_files\\MorseCommented.ino" };
		String buttonName="Press to get body, keywords, header, example sketch";
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		int width=screenSize.width;
		int height=screenSize.height;
		Font font=new Font("ComicSans",Font.LAYOUT_LEFT_TO_RIGHT,20);
		new SketchCreaterUserInterface(null,title, generalPrompt, fields, examples,buttonName,width/3,height/4,width/4,height,font).setVisible(true);

	}
	
	public void updateClass(JPanel jp,Font font){

		//load neecessary fields from gui
		String className = textBoxes[0].getText();
		String fileName = textBoxes[1].getText();
		
		//load sketch from file	
		ScriptEditor helper = new ScriptEditor(fileName);
		System.out.println("Getting Contents");
		String contents = helper.toString();
		System.out.println(contents);
		//create sketch parser from loaded file
		SketchParser parser = new SketchParser(contents);
		System.out.println(parser);
		//create ArduinoClassContainer from sketch parser
		ArduinoClassContainer cont = parser.getContainer(className, false);
		System.out.println(cont);
		 Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		int width=screenSize.width;
		int height=screenSize.height;
		
		this.newPanel("Class .cpp file",cont.getBody(),width/4*0,0,width/4,height/4,font);
		this.newPanel("Class Example File",cont.getExample(),width/4*1,0,width/4,height/4,font);
		this.newPanel("Class Header File",cont.getHeader(),width/4*2,0,width/4,height/4,font);
		this.newPanel("Class keywords file",cont.getKeywords(),width/4*3,0,width/4,height/4,font);
		this.newPanel("Original Sketch", contents, 0, 0, width/4, 0, font);
	}
}
