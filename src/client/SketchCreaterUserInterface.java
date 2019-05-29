/**Name: Jacob Smith
  *Email:jsmith2021@brandeis.edu 
  *Date: May 29, 2019
  *Assignment: Personal Study, allows the user to convert an Arduino sketch into a lirbary,
  *the core of the ArduinoClass Generator project	
  *Bugs:
  *Sources:
  */
package client;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

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
	public SketchCreaterUserInterface(String title, String generalPrompt, String[] fields, String[] examples,
			String buttonName) {
		super(title, generalPrompt, fields, examples, buttonName);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		// set up GUI
		String title = "Welcome to the Arduino Sketch to Library Converter";
		String generalPrompt = "please enter fields and press button";
		String[] fields = { "className [Template]", "Sketch path" };
		String[] examples = { "Timer", "testing_files\\Morse\\Morse.ino" };
		String buttonName="Press to get body, keywords, header, example sketch";
		new SketchCreaterUserInterface(title, generalPrompt, fields, examples,buttonName).setVisible(true);

	}
	
	public void updateClass(JPanel jp){
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
		resetPanel2(jp, "apple");
		//close window automatically after button press
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setVisible(false);

	}
	public void resetPanel2(JPanel jp,String toDisplay) {
		/*jp.repaint();
		jp.removeAll();
		System.out.println("mark");
		jp.removeAll();
		final JTextArea tf3 = new JTextArea(20,20);
	    tf3.setEditable(false);
	    jp.add(tf3);
	    tf3.setText("appleby");*/
		JPanel jp2 = new JPanel(new GridLayout(20, 2));// was 4 1
		final JTextArea tf3 = new JTextArea(20,20);
	    tf3.setEditable(false);
	    jp2.add(tf3);
	    tf3.setText("appleby");
	    getContentPane().add(jp2);
	}

}
