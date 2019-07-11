package files;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import cc.arduinoclassmaker.ArduinoClassContainer;
import cc.arduinoclassmaker.Files;
import cc.arduinoclassmaker.SketchParser;
import cc.arduinoclassmaker.libraryOptionalFields;
import cc.arduinoclassmaker.otherClassFileMaker;

/**
 * Name: Jacob Smith Email:jsmith2021@brandeis.edu Date: Jul 10, 2019
 * Assignment:Personal Study, verifies behavior of testOtherClassFileMaker class
 * Bugs: Sources: Rights: Copyright (C) 2019 Jacob Smith License is GPL-3.0,
 * included in License.txt of this github project
 */

public class testOtherClassFileMaker {

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	/**
	 * the directory to create files in
	 */
	String path;

	@Before
	/**
	 * runs before other methods to give them a path to work in
	 */
	public void setupPath() {
		path = tempFolder.getRoot().toString();
	}

	@Test
	/**
	 * asserts that Files class creates a file with correct location, name, and
	 * contents
	 * 
	 * @throws IOException
	 */
	public void testCreateFiles() throws IOException {
		//the file path of the correct testing files
		String correctPath = "testing_files\\otherClassFiles";
		//create optional class files
		String className = "test";
		otherClassFileMaker.createClassFiles(className, path, "test file", "test\tKEYWORD1\nbegin\tKEYWORD2");
		//create array of file endings
		String []fileEndings = { "\\.development", "\\.gtignore", "\\library.properties",
				"\\examples\\" + className + "Example\\" + className + "Example.ino", "\\keywords.txt" };
		//iterate through fileEndings array to compare files
		for(int i=0;i<fileEndings.length;i++) {
			compareFiles(correctPath + fileEndings[i], path + fileEndings[i]);
		}
		//make sure that there aren't any extra files at top level
		assertEquals(5, new File(path).listFiles().length);
		//make sure that there aren't any extra files at examples folder level
		assertEquals(1, new File(path+"\\examples").listFiles().length);
		//make sure that there aren't any extra files at examples file level
		assertEquals(1, new File(path+"\\examples\\"+className+"Example").listFiles().length);

	}

	/**
	 * Helper test method to use a file to check sketch parsing
	 * 
	 * @param exampleSketch
	 *            the file containing the sketch to be parsed
	 * @param parsedExampleSketch
	 *            the file containing the correctly parsed fields
	 */
	private static void compareFiles(String correctFile, String generatedFile) {
		// load the Example Sketch from file
		ScriptEditor loader = new ScriptEditor(generatedFile);
		String generatedContents = loader.toString().replaceAll("\r", "");
		// load correct fields from memory
		loader = new ScriptEditor(correctFile);
		String correctContents = loader.toString().replaceAll("\r", "");
		// replace special characters necessary for file comparisons
		// assert the the correct fields equal the generated fields
		assertEquals(correctContents, generatedContents);
	}

	/*
	 * private void compareGeneratedClass(String className) { //the file structure
	 * String folder="testing_files\\"+className+"\\";
	 * 
	 * //load the Example Sketch from file //create SketchParser object to get
	 * fields from sketch String []filePaths= {"apple","orange","pear"}; String
	 * []fileContents=new String [filePaths.length]; ScriptEditor helper=null;
	 * for(int i=0;i<filePaths.length;i++) { helper=new ScriptEditor(filePaths[i]);
	 * fileContents[i]=helper.toString().replaceAll("\r", ""); } return
	 * fileContents; }
	 * 
	 * // @return an array ofString representing all files in folder
	 * 
	 * private String[] loadOtherFiles(String path) { //the file structure String
	 * folder="testing_files\\Morse"; File file=new File(folder); File []
	 * pathNames=file.listFiles(); for(int i=0;i<pathNames.length;i++) {
	 * System.out.println(pathNames[i]); } return null; }
	 *
	 */

}
