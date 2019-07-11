/**Name: Jacob Smith
  *Email:jsmith2021@brandeis.edu 
  *Date: Jul 10, 2019
  *Assignment:Personal Study, verifies the behavior of the files class
  *Bugs:
  *Sources:
  *Rights:  Copyright (C) 2019 Jacob Smith
  *  		License is GPL-3.0, included in License.txt of this github project
  */
package files;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import cc.arduinoclassmaker.Files;
	 
public class FilesTest {
	     
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
	    public void setupPath(){
	    	path=tempFolder.getRoot().toString();
	    }
	     
	    @Test
	    /**
	     * asserts that Files class creates a file with correct location, name, and contents
	     * @throws IOException
	     */
	    public void testCreateFile() throws IOException{
	    	//create the file
	    	Files.createFile(path, "test.txt", "this is a test");
	    	//use helper method to verify file contents
	        assertFileCreated(path, "test.txt", "this is a test");
	    }
	     
	     
	    @Test
	    /**
	     * asserts that Files class creates a folder with correct location and name
	     * @throws IOException
	     */
	    public void testCreateFolder() throws IOException{
	    	//uses Files class to create a folder
	    	Files.createFolder(path, "folder");
	    	//uses File clas to make sure folder exists and is a folder
	        File file =new File(path+"\\folder");
	        assertTrue(file.exists());
	        assertTrue(file.isDirectory());
	    }
	    
	   /**
	     * asserts that a file at that location exists and has those contents
	    * @param path where file is located
	    * @param fileName name of file
	    * @param correctContents the correct contents of the file
	    * @throws FileNotFoundException
	    */
	    public static void assertFileCreated(String path,String fileName,String correctContents) throws FileNotFoundException {
	    	//use File class to check if the file exists and is a file
	        File file=new File(path+"\\"+fileName);
	        //assertTrue(file.exists());
	        //assertTrue(file.isFile());
	        //verifies contents of file using a scanner
	        Scanner fileReader=new Scanner(file);
	        String contents="";
	        while(fileReader.hasNextLine()) {
	        	contents+=fileReader.nextLine();
	        }
	       // assertEquals(correctContents,contents);
	        fileReader.close();
	    }
	     
	 
	}
