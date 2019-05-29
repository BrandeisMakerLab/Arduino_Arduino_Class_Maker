/* Written by Jacob Smith for Brandeis Robotics Club 2019/05/15
A timer class to allow the user to create loops and maintain program control
Boards supported: ARDUINO_AVR_UNO ESP8266_WEMOSD1R1*/

//includes the libraries of code necessary to make this one work
#include <Timer.h>

//The object used to interfact with the class
Timer timer();

//runs once
void setup() {
    timer.resetTime()
    delay(3000);
    Serial.println(timer.getTime())

}

//runs many times
void loop() {
    Serial.println(timer.getAndResetTime());
    delay(3000);

}

