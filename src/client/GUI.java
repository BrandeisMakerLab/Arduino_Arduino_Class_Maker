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

	public GUI(String title, String generalPrompt, String[] fields, String[] examples, String buttonName) {
		ready = false;
		// loadFile();
		setLocation(400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel jp = new JPanel(new GridLayout(20, 2));// was 4 1
		// header label
		JLabel lb0 = new JLabel(title);
		jp.add(lb0);
		jp.add(new JLabel(generalPrompt));
		// body labels
		textBoxes = new JTextArea[fields.length + 1];
		for (int i = 0; i < fields.length; i++) {
			createField(fields, examples, i, jp);
		}

		// output text field
		JButton btn = new JButton(buttonName);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				updateClass(jp);
			}
		});
		;
		jp.add(btn);
		ready = true;
		getContentPane().add(jp);
		pack();

	}

	/* Creates a label and text box for a given field */
	private void createField(String[] fields, String[] examples, int index, JPanel jp) {
		JLabel lbl = new JLabel("Please enter " + fields[index] + " here");
		final JTextArea tf = new JTextArea(examples[index]);
		textBoxes[index] = tf;
		tf.setText(examples[index]);
		jp.add(lbl);
		jp.add(tf);
		if (ready) {
			System.exit(1);
			getContentPane().removeAll();
		}
	}
	
	public void resetPanel(JPanel jp,String toDisplay) {
		jp.removeAll();
		final JTextArea tf3 = new JTextArea(20,20);
	    tf3.setEditable(false);
	    jp.add(tf3);
	    tf3.setText(toDisplay);
	}
	
	public abstract void updateClass(JPanel jp);
}
