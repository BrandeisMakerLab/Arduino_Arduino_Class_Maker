/*Written by Jacob Smith for Brandeis Robotics club
Runs a website on the local wifi network which displays a title and buttons 
with user provided names. The class also reutrns whihc button was pressed to the arduino
An object orineted version of  https://randomnerdtutorials.com/esp8266-web-server/
May 5 2019*/

//only compile this class if the board is correct
#ifdef ARDUINO_ESP8266_WEMOS_D1R1
//include all of the classes necessary to make this one work
#include <Arduino.h>
#include <ESPServer.h>

//ESPServer constructor
ESPServer::ESPServer(String title,String* possibleCodes,int numCodes){
  this->title=title;
  this->numCodes=numCodes;
  this->possibleCodes =possibleCodes;
  this->ssid     = "brandeis_open";
  this->password = "";
  this->title = title;
  
}

//connect to the wifi and display ip and mac adress
void ESPServer::wifiConnect() {
  Serial.print("MAC aress of this device:");
  Serial.println(WiFi.macAddress());
  // Connect to Wi-Fi network with SSID and password
  Serial.print("Connecting to :");
  Serial.println(ssid);
  WiFi.begin(ssid, password);  
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  // Print local IP address and start web server
  Serial.println("");
  Serial.println("WiFi connected.");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
  server.begin();
  
}

//displays the website with html commmands
int ESPServer::displayWebsite () {
  setupHeader();
  styleButton();
  // Web Page Heading
  client.println("<body><h1>" + title + "</h1>");
  int indexUsed = displayBody();
  client.println("</body></html>");

  // The HTTP response ends with another blank line
  client.println();
  return indexUsed;
}



//displays the boy of the website
int ESPServer::displayBody() {
  int index=getIndex();
  displayButtons();
  return index;
}

//keeps track of a specified interval, so a procedure can be performed every second for example
void ESPServer::displayButtons(){
  for (int index=0;index<numCodes;index++){
    client.println("<p><a href=\"/" + *(possibleCodes+index) + "\"><button class=\"button button2\">" +  *(possibleCodes+index) + "</button></a></p>");     
  }  
}

//gets the command that was most recently called
int ESPServer::getIndex(){
  boolean found = false; //to see if code was correctly identified
  //iterate through all the possible errorcodes
  int index = 0;
  while (!found && index < numCodes - 1) {
    found = hasLink(possibleCodes[index]);
    if (!found) {
      index = index + 1;
    }
  }
  //use last elemn in array as default
  if (!found) {
    index = numCodes - 1;
  }  
  return index;
}

//returns whether the usr has entered a link
boolean ESPServer::hasLink(String url){ 
  return (header.indexOf("GET /" + url) >= 0);
}

//the public method to run the server and return the user responce as the index of choices array
int ESPServer::runServer() {
  
  //connects to wifi is user didn't already
if(WiFi.status() != WL_CONNECTED){
  wifiConnect();
}else
  client= server.available(); 
  //WiFiClient client = server.available();   // Listen for incoming clients
  int indexUsed = -1;
  if (client) { 
    // If a new client connects,
   
  String currentLine = "";                // make a String to hold incoming data from the client
    while (client.connected()) {            // loop while the client's connected
      
    if (client.available()) {             // if there's bytes to read from the client,
         
    char c = client.read();             // read a byte, then
        header += c;
        if (c == '\n') {                    // if the byte is a newline character
          // if the current line is blank, you got two newline characters in a row.
          // that's the end of the client HTTP request, so send a response:
          if (currentLine.length() == 0) {
            indexUsed = displayWebsite();
            // Break out of the while loop
            break;
          } else { // if you got a newline, then clear currentLine
            currentLine = "";
          }
        } else if (c != '\r') {  // if you got anything else but a carriage return character,
          currentLine += c;      // add it to the end of the currentLine
        }
      }
    }
    // Clear the header variable
    header = "";
    // Close the connection
    client.stop();
  }
  return indexUsed;
  
}

//Enters necessary html information to st up the website
void ESPServer::setupHeader() {
  // HTTP headers always start with a response code (e.g. HTTP/1.1 200 OK)
  // and a content-type so the client knows what's coming, then a blank line:
  client.println("HTTP/1.1 200 OK");
  client.println("Content-type:text/html");
  client.println("Connection: close");
  client.println();

  // Display the HTML web page
  client.println("<!DOCTYPE html><html>");
  client.println("<head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
  client.println("<link rel=\"icon\" href=\"data:,\">");

}

//styles the buttons with css code to look better
void ESPServer:: styleButton() {

  // CSS to style the on/off buttons
  // Feel free to change the background-color and font-size attributes to fit your preferences
  client.println("<style>html { font-family: Helvetica; display: inline-block; margin: 0px auto; text-align: center;}");
  client.println(".button { background-color: #195B6A; border: none; color: white; padding: 16px 40px;");
  client.println("text-decoration: none; font-size: 30px; margin: 2px; cursor: pointer;}");
  client.println("</style></head>");
}

#endif