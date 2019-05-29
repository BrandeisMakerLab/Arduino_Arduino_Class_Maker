# ArduinoClassMaker

My Journal Notes on the Project

Unknown Date:

had to reset project
tested promt method in prmpt enum, going to display DONE prompt
more cleanly and also I have to break up internally parsed prompts better
I am using recursion to handle the complex formatting
void|driveLeft|tells robot to Drive Left
robot.driveLeft()

void|driveRight|tells robot to Drive Right
robot.driveRight()

is on the innermost level tokens of |, then tokens of newLines, then tokens of double newLines
while this format is complicated, it is both computer and human readable

Working on a field by field prompter, handling initial conditions before loop does simplify code

Sunday:
	I am only allowing allowed characters, to hide which characters I am using for parsing
	And also exclude strange characters I haven't thought of
	
	replacing all instanes of scanner readnext line with my validating looping method
it seems like using an enum for pormpting is too complicated, I will just use methods

I am handling special cases manually, simplifies my methods and makes code more readable

using interface to save code 
enums can implement interfacecs

 i don't know how to do static methods with instance methods, covnerting static metehods to instance methods

 I'm tired of retyping my responces when testing prompts,c reateing enums and interfaces similar to how I can test my ArduinoClassClient Scanner example

 putting prompts and user responces into an enum for the ArduinoClassPrompter

 Ok, with the print streams and enums, I can now just run a test instead of manually typing in test input fro ArduinoClassPrmopts
	and now debugging is so easy!
	I am now at the point where I can prompt field by field and try to generate the arduino class, but I found methodss are missing | character
	set breakpoints as you work with debugger, maybe directly from runTime exception
	 founf a parsing error in variables, explains why only header file had it, Ive got an extra newline in variables
	 I fixed that error which was missing |, but methods had extra newline, I removed it wiht substrings
	 Now test runs correctly, but public methods aren't showing up	
		To Do: not do substring by flipping with fencepost solution, fix public methods, unit test error corrections
		public methods was newline issue, now adding constructor line
		now that I added constructor, class basically has correct structure!
		I swithced order of private and public methods in body file, which puts constructor at top and puts most vidible methods at top too
	line by line unit test passed!
	
	cutting down unused code, saving in text file
	
	Created arduino class maker github respotiry so I can actually delete unused code and know it is backed up

May 23 I am working on the program that converts an existing arduino sketch until a library, which required a lot of background code work.
I modified the MiniScanner class to display an error with the word that was looked for, and the Arduino Class generators to allow method parameters of null if blank.
The methodParser class handles the work of converting a method into the format the code generator uses, and I want the program to automatically generate an example sketch
The sketch parser should also be able to tell which methods are public vs private by lloking for which methods are in the setup and loop methods
The parsedMethod class has fields even though I could have the whole thing be one big to string	
The Sketchparser class currently can read a sketch into the code representing the header, variables, public and private methods, loop and setup, but they aren't formatted
into the format the Arduino class gneerator needs

Now the SketchParser method can convert my sketch into a farily well formatted list of header comment, variables and methods, next step is separating private and public methods
and parsing variables. The variables parsing will become fairly interesting/complicated, because they should all go to a constructor. This means that the sketch to lirbary program
is forcing me to add some features like adding constructor and adding method parameters.
	The method correctly decided that wifiConnect and runserver where public methods
	I am using replaceAll String method a lot, very useful in reformatting sketch
	I am writing ParseVariable method with test cases to covnert code of variable delcaration to parsable format. This is complicated bcause data types and values can be more than one word, 
		and arrays are in form type name [], and variables don't have to be initialized
	5/24 10:51 PM: Working on arduino parser and unit tests, I got test to pass when variable is declared not inititized, still failing Linked List switch nodes (I wrtoe linked list to iterate over varibales which can have multiple words like const char)
	and eliminating extra vertical bars.
		pased extra vertical bars test using tring.replace instead of tring replace all?
	5/25 10:48: I want to integrate this project into the wider Arduino Enviornment by reforamtting and compiling files before they are parsed. This would allow me to standardize text before
	I have to parse it, and return an error in advance if program doesn't compile. Also, I want to eventually make a pull request to have the automatic class generator be part of t he arduino ide. Relevant links:
	[1] Arduino Command Line Interface https://github.com/arduino/Arduino/blob/master/build/shared/manpage.adoc
	[2] Jar files already part of arduino https://github.com/arduino/Arduino/tree/master/arduino-core/lib
	 [3] How to create a jar filw in eclipse http://www.skylit.com/javamethods/faqs/createjar.html
	 
	 May 27 2019 10:34 AM: I looked up who else is working on an Arduino Class Generator on the Arduino developers group, noone there is. I'm working on passing more of the VariableParser unit tests, so I wrote a MiniScanner getResr
	 method to help. Also, else statements are useful in avoiding something right before exiting a loop. In that scheme, use booleann loop control variable as condition. Set variable in loop, and use else statment to do whtaver should be done while loopingis chuggin
	 but not when it is about to exit. This is a more subtle version of a break statment.
		12:58 PM: Owkring on LinkedList switch method for variavle parser, I wrote a state method that handles null popitner exceptions, and made setter methods privte to force it use
		1:13 PM: [Android App for Physip] Arduino Auto format inserts spaces but doesn't remove them, I'll have todo that on my own, which should be using the MiniScanner class internally
		
		3:07 PM May 28 2019: I got the MiniScanner to allow it to ignore multiple tokesn in input, but now a lot of other unit tests fail
		6:58 PM: I spent a lot of time today trying to get rid of scanner errors, put the project on github so I wouldn't have to redo my work again
		11:00 PM I got all unit tests to pass, and having project on github with commits on passing tests makes it so much easier to keep project moving forward with unit tests passing, and creating branches is useful. In the SketchParser class, I am working on an autogenerated constructor based on variables. Before that, I am preparing the program to handle the https://www.arduino.cc/en/Hacking/LibraryTutorial morse code sketch as is so I I can post it to the Arudino developers forum. The challenges so far with that have been to reformat the newline brackets style of coding into the same line and to create to do comments when comments are missing, which is what I'm doing now.