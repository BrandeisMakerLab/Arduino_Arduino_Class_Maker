package enums;

// @formatter:off
/** Name: Jacob Smith
 *  Email:jsmith2021@brandeis.edu
 *  Assignment: Personal Study, an enum to contain correct string representations
 *  of arduino classes for unit testing. The hardcoding of arduino classes allow for 
 *  special characters to be explicit and for them to be tested easily with string compare assertions
 *  Date: May 16, 2019
 *  Sources: 
 */


public enum ArduinoClassHardCoded {
	
    CPP_FILE( 
    		"/* Written by Jacob Smith for Brandeis Robotics Club 2019/05/15\n"+
    		"A timer class to allow the user to create loops and maintain program control\n"+
    		"Boards supported: ARDUINO_AVR_UNO ESP8266_WEMOSD1R1*/\n\n"+

    		"//only compile this class if the board is correct\n"+
    		"#if defined(ARDUINO_AVR_UNO) | defined (ESP8266_WEMOSD1R1)\n\n"+

    		"//includes the libraries of code necessary to make this one work\n"+
    		"#include <Timer.h>\n\n"+

    		"//Creates a new Timer object\n"+
    		"Timer::Timer() {\n"    +
    		"    initTime=millis();\n\n"+

    		"}\n\n"+

    		"//resets the Initial Time\n"+
    		"long Timer::resetTime() {\n" +   
    		"    initTime=millis();\n"+
    		"    return getTime();\n\n"+

    		"}\n\n"+

    		"//returns the current time\n"+
    		"long Timer::getTime() {\n" +
    		"    return millis()-initTime;\n\n"+

    		"}\n\n"+

    		"//returns the current time and the initial time\n"+
    		"long Timer::getAndResetTime() {\n"  +
    		"    long curTime=getTime();\n"+
    		"    resetTime();\n"+
    		"    return curTime;\n\n"+

    		"}\n\n"+

    		"#endif"),
   	CPP_FILE_All_BOARDS( 
       		"/* Written by Jacob Smith for Brandeis Robotics Club 2019/05/15\n"+
       		"A timer class to allow the user to create loops and maintain program control\n"+
       		"Boards supported: ALL*/\n\n"+

			"//this should work on all boards, so there is no preprocessor directive here\n\n"+
       		
    	    "//includes the libraries of code necessary to make this one work\n"+
    	    "#include <Timer.h>\n\n"+

    	    "//Creates a new Timer object\n"+
    	    "Timer::Timer() {\n"    +
    	    "    initTime=millis();\n\n"+

    	    "}\n\n"+

    	    "//resets the Initial Time\n"+
    	    "long Timer::resetTime() {\n" +   
    	    "    initTime=millis();\n"+
    	    "    return getTime();\n\n"+

    	    "}\n\n"+

    	    "//returns the current time\n"+
    	    "long Timer::getTime() {\n" +
    	    "    return millis()-initTime;\n\n"+

    	    "}\n\n"+

    	    "//returns the current time and the initial time\n"+
    	    "long Timer::getAndResetTime() {\n"  +
    	    "    long curTime=getTime();\n"+
    	    "    resetTime();\n"+
    	    "    return curTime;\n\n"+

    	    "}\n\n"+

    	    "#endif"),
    H_FILE(
    		"/* Written by Jacob Smith for Brandeis Robotics Club 2019/05/15\n"+
    		"A timer class to allow the user to create loops and maintain program control\n"+
    		"Boards supported: ARDUINO_AVR_UNO ESP8266_WEMOSD1R1*/\n\n"+

    		"//sets up the Timer Header file\n"+
    		"#ifndef Timer_h\n"+
    		"#define Timer_h\n\n"+

			"//only compile this class if the board is correct\n"+
			"#if defined(ARDUINO_AVR_UNO) | defined (ESP8266_WEMOSD1R1)\n\n"+
		
    		"//includes the libraries of code necessary to make this one work\n"+
    		"#include <Timer.h>\n"+
    		"#include <Apple.h>\n\n"+

    		"class Timer{\n"+
    		"  private:\n"+
    		"    //the beginning time of the interval\n"+
    		"    long initTime;\n"+
    		"    //a test variable for the parser\n"+
    		"    Apple test;\n"+
    		"  public:\n"+
    		"    //Creates a new Timer object\n"+
    		"    Timer();\n"+
    		"    //resets the Initial Time\n"+
    		"    long resetTime();\n"+
    		"    //returns the current time\n"+
    		"    long getTime();\n"+
    		"    //returns the current time and the initial time\n"+
    		"    long getAndResetTime();\n"+
    		"};\n"+
    		"#elif defined (DONT_NEED_TIMER)\n"+
    		"  #warning : May lead to error: 'Timer' does not name a type ; Program to Board Incompatibility ; One of the libraries you are using will not work with your board ; For more information, go to cse230.artifice.cc/lecture/splitting-code.html\n"+
    		"#else\n"+
    		"  #error : Program to Board Incompatibility ; One of the libraries you are using will not work with your board ; Quick fix: add <#define DONT_NEED_TIMER> to top ; For more information, go to cse230.artifice.cc/lecture/splitting-code.html\n"+
    		"#endif\n"+
    		"#endif"),
 	H_FILE_ALL_BOARDS(
     		"/* Written by Jacob Smith for Brandeis Robotics Club 2019/05/15\n"+
     		"A timer class to allow the user to create loops and maintain program control\n"+
     		"Boards supported: ALL*/\n\n"+

     		"//sets up the Timer Header file\n"+
     		"#ifndef Timer_h\n"+
     		"#define Timer_h\n\n"+

			"//this should work on all boards, so there is no preprocessor directive here\n\n"+


     		"//includes the libraries of code necessary to make this one work\n"+
     		"#include <Timer.h>\n"+
     		"#include <Apple.h>\n\n"+

     		"class Timer{\n"+
     		"  private:\n"+
     		"    //the beginning time of the interval\n"+
    		"    long initTime;\n"+
     		"    //a test variable for the parser\n"+
     		"    Apple test;\n"+
     		"  public:\n"+
     		"    //Creates a new Timer object\n"+
     		"    Timer();\n"+
     		"    //resets the Initial Time\n"+
     		"    long resetTime();\n"+
     		"    //returns the current time\n"+
     		"    long getTime();\n"+
     		"    //returns the current time and the initial time\n"+
     		"    long getAndResetTime();\n"+
     		"};\n"+
     		"#endif"),
    	KEYWORDS_FILE(
    		"//Generates ARDUINO KEYWORDS for Timer class\n"+
    		"Timer	KEYWORD1\n"+
    		"resetTime	KEYWORD2\n"+
    		"getTime	KEYWORD2\n"+
    		"getAndResetTime	KEYWORD2\n"),
    	HEADER_FILE_FEWERKEYWORDS(
    			"/*A timer class to allow the user to create loops and maintain program control*/\n\n"
    			
    			+"//sets up the Timer Header file\n"
    			+"#ifndef Timer_h\n"
    			+"#define Timer_h\n\n"

				+"//this should work on all boards, so there is no preprocessor directive here\n\n"

				+"//includes the libraries of code necessary to make this one work\n"
				+"#include <Timer.h>\n"
				+"#include <Apple.h>\n\n"

				+"class Timer{\n"
				+"  private:\n"
				+"    //the beginning time of the interval\n"
				+"    long initTime;\n"
				+"    //a test variable for the parser\n"
				+"    Apple test;\n"
				+"  public:\n"
				+"    //Creates a new Timer object\n"
				+"    Timer();\n"
				+"    //resets the Initial Time\n"
				+"    long resetTime();\n"
				+"    //returns the current time\n"
				+"    long getTime();\n"
				+"    //returns the current time and the initial time\n"
				+"    long getAndResetTime();\n"
				+"};\n"
				+"#endif");
    
    //a string to store the representation of the class
    private String hardCode;
    
    /** A private constructor to create an enum with class hardCode
     * */
    ArduinoClassHardCoded(String hardCode) {
        this.hardCode=hardCode;
    }
   

    /**Displays capabilities of the enum
     * */
    public static void main(String[] args) {
        System.out.println("Prints out the different enum names and contents");
        System.out.println("________________________________________________");
        for (ArduinoClassHardCoded a : ArduinoClassHardCoded.values()){
        	System.out.println(a.name()+":\n\n//////////////");
        	System.out.println(a.toString());
        }
          
    }
    
    /**returns a string representation of the enum, with name, prompt, and format
     * */
    public String toString(){
    	return hardCode;
    }
}