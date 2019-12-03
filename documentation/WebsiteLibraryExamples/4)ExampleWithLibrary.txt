/* Wifi Server Jacob Smith Brandeis Robotics Club*/
//inclue the wifi server library
#include <ESPServer.h>
//the title of the webite
String title="Test Title";
//the possible options of the website
String possibleCodes[]={"this","that","default"};
//create a new ESPServer
ESPServer server(title,possibleCodes,3);
void setup() {
  //establish a USB connection to the computer
  Serial.begin(115200);
  //wait for a bit so connection can be established
  delay(2000);
  //display example message
  Serial.println("Brandeis University Robotics Example Web Server");
  //connect to the internet
  server.wifiConnect();
}

void loop() {
  //run the server and display the action prformed
  int indexUsed=server.runServer();
  //only print when user has experienced action
  if(indexUsed!=-1){
    //print the message of what has happenned
     Serial.println("I have been told to do: "+possibleCodes[indexUsed]);
   }  
}