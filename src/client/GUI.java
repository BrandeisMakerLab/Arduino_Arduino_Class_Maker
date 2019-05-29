package client;
/*https://coderanch.com/t/340772/java/convert-Console-App-GUI
https://examples.javacodegeeks.com/desktop-java/swing/jtextfield/create-read-only-non-editable-jtextfield/
https://stackoverflow.com/questions/25513711/how-to-make-my-textfield-bigger-for-gui-in-java
*/

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

abstract class GUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean ready;
	protected JTextArea[] textBoxes;
	
	public GUI(String title, String generalPrompt, String[] fields, String[] examples, String buttonName, int x, int y,Font font) {
		ready = false;
		// loadFile();
		setLocation(x, y);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel jp = new JPanel(new GridLayout(20,2));// was 4 1
		// header label
		JLabel lb0 = new JLabel(title);
		lb0.setFont(font);
		jp.add(lb0);
		
		JLabel genPrompt=new JLabel(generalPrompt);
		genPrompt.setFont(font);
		jp.add(genPrompt);
		// body labels
		textBoxes = new JTextArea[fields.length + 1];
		for (int i = 0; i < fields.length; i++) {
			createField(fields, examples, i, jp,font);
		}

		// output text field
		JButton btn = new JButton(buttonName);
		btn.setFont(font);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				updateClass(jp,font);
			}
		});
		;
		jp.add(btn);
		ready = true;
		getContentPane().add(jp);
		pack();

	}

	/* Creates a label and text box for a given field */
	private void createField(String[] fields, String[] examples, int index, JPanel jp,Font font) {
		JLabel lbl = new JLabel("Please enter " + fields[index] + " here");
		final JTextArea tf = new JTextArea(examples[index]);
		textBoxes[index] = tf;
		tf.setText(examples[index]);
		tf.setFont(font);
		lbl.setFont(font);
		jp.add(lbl);
		jp.add(tf);
		if (ready) {
			System.exit(1);
			getContentPane().removeAll();
		}
	}
	
	public void newPanel(String windowName,String toDisplay,int x,int y,int windowWidth, int windowLength,Font font) {
		this.setVisible(false);
		
		JFrame newFrame = new JFrame(windowName);
	    newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    newFrame.setLocation(x, y);
	    
	    JPanel jp=new JPanel();
	    jp.setOpaque(true); //content panes must be opaque
	    final JTextArea tf3 = new JTextArea(windowLength/10,windowWidth/11);
		tf3.setText(toDisplay);
		tf3.setFont(font);
		
	    jp.add(tf3);
        newFrame.setContentPane(jp);
        
        //Display the window.
        newFrame.pack();
        newFrame.setVisible(true);	  
	}
	
	public abstract void updateClass(JPanel jp,Font font);
}
