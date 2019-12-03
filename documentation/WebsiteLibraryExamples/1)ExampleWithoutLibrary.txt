/*********
  Rui Santos
  Complete project details at https://randomnerdtutorials.com
  https://randomnerdtutorials.com/esp8266-web-server/
  Moified by Dies Robotics Club to be simpler
*********/

// Load Wi-Fi library
#include <ESP8266WiFi.h>
//#include <DriveShield.h>
//DriveShield robot;
// Replace with your network credentials
const char* ssid     = "brandeis_open";
const char* password = "";
String title = "Deis Robotics Test Web Server";
String possibleCodes [] = {"driveF", "driveB", "stopF","stopB"};
String nextCodes     [] = {"stopF",  "stopB",  "driveB","driveF"};
int numCodes=sizeof(possibleCodes) / sizeof(possibleCodes[0]);


// Set web server port number to 80
WiFiServer server(80);

// Variable to store the HTTP request
String header;

void setup() {
  Serial.begin(115200);
  // Initialize the output variables as outputs
  wifiConnect();
}


void loop() {
  runServer();
}

void runServer() {
  WiFiClient client = server.available();   // Listen for incoming clients

  if (client) {                             // If a new client connects,
    String currentLine = "";                // make a String to hold incoming data from the client
    while (client.connected()) {            // loop while the client's connected
      if (client.available()) {             // if there's bytes to read from the client,
        char c = client.read();             // read a byte, then
        header += c;
        if (c == '\n') {                    // if the byte is a newline character
          // if the current line is blank, you got two newline characters in a row.
          // that's the end of the client HTTP request, so send a response:
          if (currentLine.length() == 0) {
            setupHeader(client);
            styleButton(client);

            // Web Page Heading
            client.println("<body><h1>" + title + "</h1>");
            boolean found=false;//to see if code was correctly identified
            //iterate through all the possible errorcodes
            for (int i = 0; i < numCodes-1; i++) {
              // If the output5State is off, it displays the ON button
              if (header.indexOf("GET /"+possibleCodes[i]) >= 0) {
                 client.println("<p><a href=\"/" + possibleCodes[i] + "\"><button class=\"button button2\">" +  nextCodes[i] + "</button></a></p>");
                 Serial.println("I should do "+ nextCodes[i]);
                found=true;
              } 
            }
            //use the last string as a default setting
            if(!found){
               client.println("<p><a href=\"/" + possibleCodes[numCodes-1] + "\"><button class=\"button button2\">" +  nextCodes[numCodes-1] + "</button></a></p>");
               Serial.println("default");
            }    
            client.println("</body></html>");

            // The HTTP response ends with another blank line
            client.println();
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
}

void setupHeader(WiFiClient client) {
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

void styleButton(WiFiClient client) {

  // CSS to style the on/off buttons
  // Feel free to change the background-color and font-size attributes to fit your preferences
  client.println("<style>html { font-family: Helvetica; display: inline-block; margin: 0px auto; text-align: center;}");
  client.println(".button { background-color: #195B6A; border: none; color: white; padding: 16px 40px;");
  client.println("text-decoration: none; font-size: 30px; margin: 2px; cursor: pointer;}");
  client.println("</style></head>");

}
void wifiConnect() {
  // Connect to Wi-Fi network with SSID and password
  Serial.print("Connecting to ");
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