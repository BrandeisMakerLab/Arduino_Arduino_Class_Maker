/**Name: Jacob Smith
  *Email:jsmith2021@brandeis.edu 
  *Date: May 20, 2019
  *Assignment:	Personal Study, allows implemetning classes to ensure they have a prompt method
  *for unit testing involving keyboard input
  *Bugs:
  *Sources:
  */
package client;

public interface Prompter {

    //Prompts the user to enter information relevant to the arduino class
	//parameter scanner to read keyboard input
    ArduinoClassContainer prompt();

}