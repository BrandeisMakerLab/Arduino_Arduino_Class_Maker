/*TODO: EXPLAIN WHAT PROGRAM IS FOR AND GIVE YOUR NAME AND WHO CAN USE THIS PROGRAM 2019/05/15*/

//this should work on all boards, so there is no preprocessor directive here

//includes the libraries of code necessary to make this one work
#include <Morse.h>

//Creates a new Morse object
Morse::Morse() {
}

//Initializes the class, can't always be done at same time as constructor
void Morse::begin() {
    //TODO: EXPLAIN WHAT VARIABLE IS FOR
    pin = 13;

}

//TODO: EXPLAIN WHAT METHOD DOES
void Morse::dot() {
    digitalWrite(pin, HIGH);
    delay(250);
    digitalWrite(pin, LOW);
    delay(250);

}

//TODO: EXPLAIN WHAT METHOD DOES
void Morse::dash() {
    digitalWrite(pin, HIGH);
    delay(1000);
    digitalWrite(pin, LOW);
    delay(250);

}

#endif