/**Name: Jacob Smith
  *Email:jsmith2021@brandeis.edu 
  *Date: May 20, 2019
  *Assignment: Personal Study, provided the line by line proppts and user responces for ArduinoClassPrompts class	
  *Bugs:
  *Sources:
  */
package enums;

public enum ExampleLineByLine implements PromptResponces{
		//every example responce with \n will be split on that
	    CLASSNAME 		("Please enter the class name:",null,"Timer"),
	    AUTHOR   		("Please enter your name:",null,"Jacob Smith"),
	    ORGANIZATION   	("Please enter your organization:",null,"Brandeis Robotics Club"),
	    HEADERCOMMENTS 	("Please enter a description of the class\n\t\t\t",null,"A timer class to allow the user to create loops and maintain program control\ndone\n"),
	    SUPPORTEDBOARDS	("Please enter supported boards\n\t\t",null,"ARDUINO_AVR_UNO\nESP8266_WEMOSD1R1\ndone\n"),
	    VARIABLES  		("type:-name:-description:","Does your class have a variable to enter? Y/N:",
	    		"y\nlong\ninitTime\nThe beginning of the time interval\n"+
	    		"y\nApple\ntest\na test variable for the parser\nn\n"),
	    PRIVATEMETHODS  ("return type-name-parameters/null-description-body","do you have a private method to enter? Y/N","n\n"),
	    PUBLICMETHODS 	("return type-name-parameters/null-description-body","do you have a public method to enter? Y/N",
	    		"initTime=millis();\ndone\n"+
	    		"y\nlong\nresetTime\nnull\nresets the Initial Time\ninitTime=millis();\nreturn getTime();\ndone\n"
	    		+"y\nlong\ngetTime\nnull\nreturns the current time\nreturn millis()-initTime;\ndone\n"
	    		+"y\nlong\ngetAndResetTime\nnull\nreturns the current time and the initial time\nlong curTime=getTime();\nresetTime();\nreturn curTime;\ndone\nn\n");
	
		private String prompt;
		private String alternatePrompt;
		private String exampleResponce;
		/**
		 * 
		 * @param prompt the string printed to prompt the user
		 * @param exampleaResponce a valid responce the user might enter
		 */
		private ExampleLineByLine(String prompt, String alternatePrompt,String exampleResponce) {
			this.prompt=prompt;
			this.alternatePrompt=alternatePrompt;
			this.exampleResponce=exampleResponce;
		}
		
		public String getPrompt() {
			return prompt;
		}
		
		public String getAlternatePrompt() {
			return alternatePrompt;
		}
		
		public String getExampleResponce() {
			return exampleResponce;
		}

		/* 
		 * build a sample string of all the user needs to make a valid arduino class
		 */
		@Override
		public String getResponces() {
			//create and array of all the enums
			ExampleLineByLine[]examples=ExampleLineByLine.values();
			ExampleLineByLine example;
			String responces="";
			//iterate through the array,adding the example fields to a responces string
			for(int i=0;i<examples.length;i++) {
				example=examples[i];
				responces+=example.exampleResponce+"\n";
			}
			//return the responces string
			return responces;
		}
}
