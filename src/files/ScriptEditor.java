package files;
/* Name: Jacob Smith
Date: May 12 2019
Assignment: Personal study, allows the user to load and write a file, for Arduno editing
Email: jsmith2021@brandeis.edu
Building Java programs 4th Ed page 424 for reading files, 412 for writing to them
https://stackoverflow.com/questions/7709041/javac-is-not-recognized-as-an-internal-or-external-command-operable-program-or
cd C:\Users\RTI\Documents\Arduino\libraries\ZumoAutomation\extras
https://stackoverflow.com/questions/15512190/how-to-insert-a-new-line-character-in-a-string-to-printstream-then-use-a-scanner
javac ScriptEditor.java
java ScriptEditor
*/
import java.io.PrintStream;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class ScriptEditor{
	
	private String fileName;
	private String contents;
	private Scanner editor;
	

	/**
	* Creates a new Script editor object
	*/
	public ScriptEditor(String fileName){
		this.fileName=fileName;
		File f=new File(fileName);
		try{
			this.editor=new Scanner(f);
		}catch(Exception e){
			System.out.println("Error, file not found");
		}
		this.contents="";
		loadContents();
		
	}
	
	/**
	* Displays simple example to load contents of a file, print it, and rasave the file
	*/
	public static void main (String []args)throws FileNotFoundException{
		System.out.println("Example of ScriptEditor Class");
		System.out.println("This program will load a file from memory, add a line, and save the file");
		
		System.out.println("Reading to file");
		ScriptEditor helper=new ScriptEditor("test.txt");
		
		System.out.println("Getting Contents");
		String contents=helper.getContents();
		System.out.print(contents);
		
		System.out.println("Now writing to file");
		helper.writeFile(contents+"This was added to the file\r\n");
	}
	
	/**
	* Loads a file's contents into a string
	*/
	private void loadContents(){
		contents="";
		while (editor.hasNextLine()){
			contents+=editor.nextLine()+"\r\n";
		}
		
	}
	
	/**
	*  Replaces a file with given String
	*/
	public void writeFile(String message){
		try{
			PrintStream output=new PrintStream(new File(fileName));
			output.print(message);
			output.close();
		}catch(Exception e){
			System.out.println("Error, file not found");
		}
	
		
	}
	
	/*
	*  gets the File's contents
	*/
	public String getContents(){
		//I can return this safely because Stringa re immutable in Java
		return contents;
		
	}

}