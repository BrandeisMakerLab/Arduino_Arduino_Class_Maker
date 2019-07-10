/*TODO: EXPLAIN WHAT PROGRAM IS FOR AND GIVE YOUR NAME AND WHO CAN USE THIS PROGRAM 2019/05/15*/

//sets up the Blank Header file
#ifndef Blank_h
#define Blank_h

//this should work on all boards, so there is no preprocessor directive here

//includes the libraries of code necessary to make this one work
#include <Blank.h>

class Blank{
  private:
  public:
    //Creates a new Blank object
    Blank();
    //Initializes the class, can't always be done at same time as constructor
    void begin();
};
#endif