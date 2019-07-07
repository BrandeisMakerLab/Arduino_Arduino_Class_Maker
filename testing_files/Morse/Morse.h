/*TODO: EXPLAIN WHAT PROGRAM IS FOR AND GIVE YOUR NAME AND WHO CAN USE THIS PROGRAM 2019/05/15*/

//sets up the Morse Header file
#ifndef Morse_h
#define Morse_h

//this should work on all boards, so there is no preprocessor directive here

//includes the libraries of code necessary to make this one work
#include <Morse.h>

class Morse{
  private:
    //TODO: EXPLAIN WHAT VARIABLE IS FOR
    int pin;
  public:
    //Creates a new Morse object
    Morse();
    //Initializes the class, can't always be done at same time as constructor
    void begin();
    //TODO: EXPLAIN WHAT METHOD DOES
    void dot();
    //TODO: EXPLAIN WHAT METHOD DOES
    void dash();
};
#endif