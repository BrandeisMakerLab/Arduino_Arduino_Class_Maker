#Jacob Smith script to automatically create Dozuki Guide 
#Sources:
	#https://stackoverflow.com/questions/4302027/how-to-open-a-url-in-python
	#import python  lirbaries here C:\Users\RTI\AppData\Local\Programs\Python\Python37-32\Lib\site-packages
	#https://stackoverflow.com/questions/4302027/how-to-open-a-url-in-python
	#Must be logged into https://brandeismakerlab.dozuki.com/
	#Also my first attempt at reverse engineering a website
	#view documetns from command line:https://askubuntu.com/questions/58416/how-can-i-preview-html-documents-from-the-command-line
	#sleenium blog post https://towardsdatascience.com/controlling-the-web-with-python-6fceb22c5f08
	#selenium:https://github.com/SeleniumHQ/selenium/tree/master/py#installing
	#ChromeDriver:https://sites.google.com/a/chromium.org/chromedriver/downloads
	#use selenium to fill in text box 
	#direct input https://stackoverflow.com/questions/13166395/fill-input-of-type-text-and-press-submit-using-python/13167289
	#direct input https://stackoverflow.com/questions/11892729/how-to-log-in-to-a-website-using-pythons-requests-module/17633072#17633072
	#click button on webpage https://www.reddit.com/r/learnpython/comments/6bt8g2/how_can_i_essentially_click_a_button_on_a_webpage/
#Bugs:
	#Saving Page results in authorization error
	#extra imports because I don't know how to make them global
#Extra Code
    #Extra program to log in on low level
    #Provided Code
    #Fill in your details here to be posted to the login form.
    #payload = {
    #  'inUserName': 'jsmith2021@brandeis.edu',
    #  'inUserPass': '5 Incorrect Zebras!'
    #}
    #url="https://help.dozuki.com/Login"
    #with requests.Session() as s:
    #  p = s.post(url, data=payload)
    # print the html returned or something more intelligent to see if it's a successful login page.
    #  displayBrowser(url)
    # An authorised request.
    #r = s.get('A protected web page url')
    #print (r.text)
        # etc...
    #My code not being used
    #Create guide with requests
    #r=requests.get(newGuide)
    #display broweser with webBrowser
    #displayBrowser(newGuide)
    #save guide with requests
    #saveGuide=(url+"intro/save")
    #r=requests.post(saveGuide)
    #display text displayText(r)
    #save the guide and dispay text in command prompt, won't work
    #display("Save the Guide:",waitTime)
    #driver.get(url+"intro/save")
   
    #displays text of a website using htmltext
    #def displayText(website):
	  #import html2text
	  #h = html2text.HTML2Text()
	  #h.ignore_links = True
	  #print (h.handle(website.text))

   #display a website in a browser
    #def displayBrowser(url):
	 #import webbrowser
	 #webbrowser.open(url)

#main method of script
def main():
	#create script with parameters
	LogOnNewGuide("Arduino","Shows how to automatically create a Dozuki Guide","Look I can fill in the text here too")
  

#Creates a new Dozuki Guide Given the existing category, title,
# and contents 
def LogOnNewGuide(topic,title,body):
  #import required packages
  import time
  from selenium import webdriver
  import requests
  #define constants
  waitTime=1
  url="https://brandeismakerlab.dozuki.com/"

  display("Logging in to Dozuki takes 15 seconds:",waitTime)
  driver=logIn("email","password","loginBtn","jsmith2021@brandeis.edu","5 Incorrect Zebras!",url+"Login")

  #Create new Guide
  makeNewGuide(url,driver,waitTime,topic)
  
  #Populating Guide
  display("Populating Guide Description:",waitTime)
  fillIn(driver,"introTitle",delete()+title)
  fillIn(driver,"introSummary",delete()+body)

  #save it, need to load guide url 
  display("Save Guide with Selenium:",waitTime)
  button = driver.find_element_by_id("save")
  button.click()

  #make guide public
  display("Make Guide Public:",waitTime)
  button = driver.find_element_by_id("togglePublic")
  button.click()
 
  #edit step title
  time.sleep(5)
  display("Edit Step",waitTime)
  fillIn(driver,"editStepTitleInput",delete()+"This is a title")
  time.sleep(5)


  display("Logging out of Dozuki:",waitTime)
  import requests
  requests.get(url+"\Logout")

  #close browser
  driver.quit()

#backspace character to remove autofill input
def delete():
	return '\b\b\b\b\b\b\b\b\b\b\b\b\b\b'
	   

#Create a guide using requests method
def makeNewGuide(url,driver,waitTime,topic):
  display("Creating New Guide:",waitTime)
  #create new guide and display it in a browser, importing it
  import webbrowser
  import requests
  newGuide=url+"Guide/new/Topic/"+topic
  driver.get(newGuide)

#logs in to a website
#given name of user box, passwrod box, button box, username, password, and url
#returns the driver used to log in for later use
def logIn(userName,passwordName,buttonName,username,password,url):
  #provided code to set up automatic browser
  from selenium import webdriver
  import time
  options = webdriver.ChromeOptions()
  options.add_argument('--ignore-certificate-errors')
  options.add_argument("--test-type")
  driver = webdriver.Chrome(chrome_options=options)
  driver.get(url)
  #fill in forms 
  fillIn(driver,userName,username)
  fillIn(driver,passwordName,password)
  button = driver.find_element_by_id(buttonName)
  button.click()
  return driver

#displays a message in dialogue and waits
def display(message,displayTime):
  import time
  print(message)
  time.sleep(displayTime)

#Uses Selenium to fill a message into a text box of a given key
def fillIn(driver,key,message):
 import selenium
 text_area = driver.find_element_by_id(key)#password
 text_area.send_keys(message)

#run the main method
main()