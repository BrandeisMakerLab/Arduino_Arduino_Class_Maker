/**Name: Jacob Smith
  *Email:jsmith2021@brandeis.edu 
  *Date: May 31, 2019
  *Assignment:	Personal Study, integrates the Arduino Class Generator project into the Arduino IDE
  *Bugs: This hasn't been tested on other computers, mine is windows 10
  *Sources:
  *Notes 
  *https://www.java-forums.org/new-java/51756-actionlistener-calling-methods-different-class.html
		//save code
		try {
			sketchController.save();
		}catch(IOException e) {
			System.out.println("error with saving");
		}
		SketchFile current = getCurrentTab().getSketchFile();
		System.out.println("MARK" + current.getFile().getPath());
		String sketchString = getCurrentTab().getText();
		this.addTab(current, sketchString);
		sketchController.nameCode(fileNames[i]); creates new tab with given file name, initialize method of EditorStatus.java line 240
		editor.status.edit(tr("Name for new file:"), "");creates new tab with dialogue for file name, HandleNewCode method of SketchController.java line 72
		edit(String message, String dflt)
		
		 item = newJMenuItem(tr("Generate Arduino Class"),' ');
   		item.addActionListener((ActionListener) event -> ClassGeneratorInterface.generateLibrary(sketchController,getCurrentTab(),getCurrentTab().getSketchFile(),tabs));
		
 */
package IDEInterface;
import processing.app.SketchController;
import processing.app.EditorTab;
import processing.app.SketchFile;

import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClassGeneratorInterface {
	
	 /**
	  * Reads the file name and contents of the current sketch, and creates
	  * a body file, header file, keywords, and example file
	 */
	public static void generateLibrary(SketchController controller,EditorTab tab,SketchFile sketchFile,ArrayList<EditorTab> tabs) {
		if(failedToCompile(controller)) {return;}
		//get the file name and contents of the sketch
		String []sketchInfo=getNameContentsPath(tab,sketchFile);
		SketchParser parser=new SketchParser(sketchInfo[1]);
		ArduinoClassContainer cont=parser.getContainer(sketchInfo[2],false);
		//create the files with strings
		setLibraryTabs(sketchInfo[0],cont.getBody(),cont.getKeywords(),cont.getHeader(),cont.getExample(),tabs,controller);
	}

	/**
	 * compiles the sketch before arduino class is generated
	 * to force the user to submit a correct class
	 * @return true if class failed to compile
	 */
	private static boolean failedToCompile(SketchController controller) {
		System.out.println("Compiling sketch to check for errors...");
		String output="failed";
		try {
			output=controller.build(true, false);
		}catch(Exception e) {}
		if(output.equals("failed")) {
			System.out.println("Error, the sketch failed to compile, exiting sketch generator");
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Reads a sketch for Arduino Class Generation
	 * @return the name, contents, and file path of the sketch
	 */
	private  static String[] getNameContentsPath(EditorTab currentTab,SketchFile sketchFile) {
		//create array to return sketch information in
		String[]sketchInfo=new String[3];
		//load sketch file name
		String fileName=sketchFile.getFile().getName();
		//remove extension from fileName
		sketchInfo[0]=fileName.replaceAll(".ino", "");	
		//load sketch contents
		sketchInfo[1] = currentTab.getText();
		//load sketch path
		sketchInfo[2] =sketchFile.getFile().getPath();
		return sketchInfo;
	}

	/**
	 * Creates and saves tabs of an rduino sketch with the arudino libary files
	 * for Arduino library generation
	 * @param className the name of the class
	 * @param body the body file of class
	 * @param example the example file of class
	 * @param header the header file of the class
	 * @param keywords the keywords file of the class
	 */
	private static void setLibraryTabs(String className,String body,String example,String header,String keywords,ArrayList<EditorTab> tabs,SketchController controller) {

		//create and set text of all tabs
		//the name of every tab starts with className, because tabs are alphabetized
		//create body tab
		controller.nameCode(className+".cpp");
		//get the tab from the tabs field because nameCode doesn't return the tab
		//these indexes get complicated because of alphabetization
		tabs.get(1).setText(body);
		//create example tab
		controller.nameCode(className+"Example.ino");
		tabs.get(2).setText(example);
		//create header tab
		controller.nameCode(className+".h");
		//I don't know why this isn't a 3...
		tabs.get(2).setText(header);
		//create keywords tab
		controller.nameCode(className+"keywords.txt");
		tabs.get(4).setText(keywords);
	}	
}