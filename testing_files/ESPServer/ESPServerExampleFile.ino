/*Rui Santos
Complete project details at https://randomnerdtutorials.com
https://randomnerdtutorials.com/esp8266-web-server/
Moified by Dies Robotics Club to be simpler 2019/05/15*/

//includes the libraries of code necessary to make this one work
#include <ESPServer.h>

//The object used to interfact with the class
ESPServer espserver();

//runs once
void setup() {
    Serial.begin(115200);
    // Initialize the output variables as outputs
    espserver.wifiConnect();

}

//runs many times
void loop() {
    //this can't be a method becuase it would always print something
    int indexUsed = espserver.runServer();
    //if the website has been told to do something, return the string command
    if (indexUsed != -1) {
      Serial.println("I have been told to " + possibleCodes[indexUsed]);
    }

}

