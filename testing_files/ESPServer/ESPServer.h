/*Rui Santos
Complete project details at https://randomnerdtutorials.com
https://randomnerdtutorials.com/esp8266-web-server/
Moified by Dies Robotics Club to be simpler 2019/05/15*/

//sets up the ESPServer Header file
#ifndef ESPServer_h
#define ESPServer_h

//this should work on all boards, so there is no preprocessor directive here

//includes the libraries of code necessary to make this one work
#include <ESPServer.h>
#include <WiFiServer.h>

class ESPServer{
  private:
    //Replace with your network credentials, ssid is for wifi network
    const char* ssid;
    //the password of the wifi network
    const char* password;
    //The title of the website
    String title;
    //The possibilities on the button, no spaces
    String [] possibleCodes;
    //the number of possibilities
    int numCodes;
    //Set web server port number to 80
    WiFiServer server;
    //Variable to store the HTTP request
    String header;
    //displays the website with website
    int displayWebsite(WiFiClient client);
    //displays the buttons of the website
    int displayBody(WiFiClient client);
    //display all the possible buttons
    void displayButtons(WiFiClient client);
    //gets the command that was most recently called
    int getIndex();
    //returns whether the website is being accessed with a link
    boolean hasLink(String url);
    //sets up the header of the website
    void setupHeader(WiFiClient client);
    //uses CSS to style the button 
    void styleButton(WiFiClient client);
  public:
    //Creates a new ESPServer object
    ESPServer();
    //Initializes the class, can't always be done at same time as constructor
    void begin();
    //runs all the logic for the server
    int runServer();
    //connects to the internet and displays releve=ant information
    void wifiConnect();
};
#endif