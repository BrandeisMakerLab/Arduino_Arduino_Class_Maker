/*Written by Jacob Smith for Brandeis University.
 Allows arduino to cmmunicate with morse code*/
//the pin to flash on
int pin = 13;

//runs once, sets up pins
void setup()
{
  pinMode(pin, OUTPUT);
}

//runs many times, flashes a message
void loop()
{
  dot(); dot(); dot();
  dash(); dash(); dash();
  dot(); dot(); dot();
  //wait 3 seconds...
  delay(3000);
}

//plays a dot on the pin
void dot()
{
  digitalWrite(pin, HIGH);
  //wait quarter second...
  delay(250);
  digitalWrite(pin, LOW);
  delay(250);
}

//plays a dash on the pin
void dash()
{
  digitalWrite(pin, HIGH);
  //wait one second...
  delay(1000);
  digitalWrite(pin, LOW);
  delay(250);
}