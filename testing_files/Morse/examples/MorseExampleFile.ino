/*TODO: EXPLAIN WHAT PROGRAM IS FOR AND GIVE YOUR NAME AND WHO CAN USE THIS PROGRAM 2019/05/15*/

//includes the libraries of code necessary to make this one work
#include <Morse.h>

//The object used to interfact with the class
Morse morse();

//TODO: EXPLAIN WHAT METHOD DOES
void setup() {
    //sets up the class
    morse.begin();
    pinMode(pin, OUTPUT);

}

//TODO: EXPLAIN WHAT METHOD DOES
void loop() {
    morse.dot(); morse.dot(); morse.dot();
    morse.dash(); morse.dash(); morse.dash();
    morse.dot(); morse.dot(); morse.dot();
    delay(3000);

}

