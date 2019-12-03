/*Written by Jacob Smith for Brandeis Robotics club
Runs a website on the local wifi network which displays a title and buttons 
with user provided names. The class also reutrns whihc button was pressed to the arduino
An object orineted version of  https://randomnerdtutorials.com/esp8266-web-server/
May 5 2019*/
#ifndef ESPServer_h
#define ESPServer_h

//only compile this class if the board is correct
#if defined(ARDUINO_ESP8266_WEMOS_D1R1) 
//include all of the necessary library to make this one work
#include <Arduino.h>
#include <ESP8266WiFi.h>
#include <ESPServer.h>

class ESPServer{
  
  private:
    //the network name and password for the wifi network
    const char* ssid;
    const char* password;
    //the title of the website
    String title;
    //a list of the possible actions the website should display, no spaces
    String* possibleCodes;
    //the number of codes in the website
    int numCodes;
    // the server object that coonnects to wifi
    WiFiServer server{80};
    
    // Variable to store the HTTP request
    String header;
    //the wifi clinet to connect to internet
    WiFiClient client;
    
    //displays the website with html commmands
    int displayWebsite () ;
    //displays the body of the website
    int displayBody() ;
    //keeps track of a specified interval, so a procedure can be performed every second for example
    void displayButtons();  
    //gets the command that was most recently called
    int getIndex();
    //gets the command that was most recently called
    boolean hasLink(String url);
    //Enters necessary html information to st up the website
    void setupHeader();
    //styles the buttons with css code to look better
    void styleButton() ;
  

  public:
    //connects to the internet if the user wants to do that at a specified time
    void wifiConnect();
    //creates a new ESPServer object with title, list of options, and number options
    ESPServer(String title,String possibleOptions [],int numCodes);
    //runs the wbsite and returns which action was accomplished
    int runServer();
        
};
#elif defined (DONT_NEED_ESPSERVER)
  #warning : May lead to "error: 'ESPServer' does not name a type" ; Program to Board Incompatibility ; One of the libraries you are using will not work with your board ; For more information, go to cse230.artifice.cc/lecture/splitting-code.html
  
#else
  #error : Program to Board Incompatibility ; One of the libraries you are using will not work with your board ; Quick fix: add <#define DONT_NEED_ESPSERVER> to top ; For more information, go to cse230.artifice.cc/lecture/splitting-code.html
#endif
#endif