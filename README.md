# ArduinoClassMaker

## Quick Description

A set of java programs to automatically generate arduino library header, body, keywords, and example sketch from a working Arduino .ino sketch.

## Instructions For Use

Click on Runme.jar, which will allow the user to enter the name of the class to generate and the file path it is in.

![RunMe dialogue](C:\Users\jsmit\Documents\ArduinoClassMaker\documentation\RunMeDialogue.PNG | width=100)

When you click on the body, these editable files are displayed:

​	![Generated Classes](C:\Users\jsmit\Documents\ArduinoClassMaker\documentation\GeneratedClasses.PNG | width=100)

## Rationale

**BACKGROUND:** I have been researching this question, and I haven't found any tool that generates Arduino Libraries. [Sloeber](http://eclipse.baeyens.it/installAdvice.shtml)  is a great IDE, providing autocomplete, automatic library includes, and other professional features.

**INTRODUCTION:** That is why I created a ArduinoClassGenerator, a tool which converts Arduino Sketches into [Arduino libraries](https://www.arduino.cc/en/Hacking/LibraryTutorial). While working on my project, I have found that the best workflow for me to create arduino libraries is to write a .ino sketch with the behavior I want, and then sort out that code into the .h, .cpp, example file, and keywords file manually or with a template.

**IMPLEMENTATION:** However, I realized that much of this work can be done for me, so I created a java class which reads the Morse.ino or other sketch, and parses it (MorseParsed) into a format containing the essential information, like variable type, name, value, and comments.

Then, my Java program generates a cpp (Morse.cpp), .h (Morse.h), keywords (MorseKeywords.txt), and example file (MorseExampleFile.ino). It can do this because all of the information is contained in the parsed file. This means that instead of copy pasting variable names from a header to body file, the computer does the busywork. In addition, the program can identify which methods should be public by whether they are called in the loop and setup methods.

**PROPOSAL:** I want to know if the Arduino developers are interested in my tool getting in to the ARDUINO IDE, perhaps as a menu option under sketchàcreate library from sketch.

**ADVANTAGES:** Such a tool could save advanced programmers the time of typing out a class from a sketch, and could be used to encourage good programming practices like commenting and including a keywords file. In addition, this tool could help programmers who might not format their code for easy sharing to do so.

**DISADVANTAGE:** One drawback to including this tool in the Arduino IDE would be that it could confuse [new users](https://www.arduino.cc/en/Main/AboutUs). As it stands, the Arduino IDE is a reasonable tool for new users to write and upload a sketch, and more advanced authors could go to other IDES anyway. My response would be that the Arduino IDE already [allows library files to be viewed](.%20https:/github.com/arduino/Arduino/issues/3736), so creating them is not much of a leap. In addition, this tool could be a beginner’s first step into what a library looks like. 

**FUTURE WORK:** If continuing with the project, I would create new files in the correct file structure and format to be shared on [the Arduino class manager](https://github.com/arduino/Arduino/wiki/Library-Manager-FAQ). This would allow the user to share their code even more easily.



