/**Name: Jacob Smith
  *Email:jsmith2021@brandeis.edu 
  *Date: May 29, 2019
  *Assignment:	
  *Bugs:
  *Sources:
  */
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Random;

public class test {
public static void main(String[]args) throws AWTException, InterruptedException {
	Robot bot = new Robot();
	bot.mouseMove(2000,0);
	bot.mousePress(InputEvent.BUTTON1_MASK);
	//add time between press and release or the input event system may 
	//not think it is a click
	try{Thread.sleep(250);}catch(InterruptedException e){}
	bot.mouseRelease(InputEvent.BUTTON1_MASK);
}
}
