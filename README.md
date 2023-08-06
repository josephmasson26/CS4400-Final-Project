# CS4400-Final-Project
MySQL and JavaFX Travel Journal Application

CS 4400 - Group 6 - Phase 4 Submission
Anthony Candelmo, Joseph Masson, Lorenzo Gastaldi, Isaac Moran, Suraj Shekar

Submitted Files:
CREATE_STATEMENTS.sql
INSERT_STATEMENTS.sql
CS400-Travel-Journal--.zip
ReadMe.txt

Instructions to run the travel journal.

STEP 1
In MySQL Workbench:
Create a new connection named 'traveljournal'
Create a new schema named 'traveljournal' and set it as the default
Open and run CREATE_STATEMENTS.sql and INSERT_STATEMENTS.sql

STEP 2
In Explorer:
Extract all files from CS400-Travel-Journal--.zip

STEP 3
In Intellij IDEA:
Close your current project with File -> Close Project
Select 'New Project'
Select 'JavaFX' from the sidebar
Ensure the language is set to 'Java' and the Build system is 'Maven'
It is also best if the location is easily accessible and near where the .zip was extracted to.
Select 'Next'
Select 'Create'
Right click on the top-most folder, select Open In -> Explorer
Open the folder, select all files, and delete them
Past all extracted files into the now-empty folder and give IntelliJ some time to index them

STEP 4
Open CS400-Travel-Journal-- -> Travel Journal -> src -> main -> java -> com.example.traveljournal
Open the file named Connector
Change the field String dbPass on line 11 to your MySQL root password

In the com.example.traveljournal folder, open the file named Start
This will be the file you run to execute the program

STEP 5
In the top menu, open File -> Project Structure -> Libraries
On the top, you will see a '+' icon
Press and select 'From Maven...'
In the text field, type 'controlsfx' and press enter
The bar will glow red, wait a few seconds and press the drop-down arrow to the right
Select 'com.github.martinkoster:actionfx-controlsfx:1.6.0'
Press 'OK'
Press 'OK'

STEP 6
Remain in the 'Libraries' menu
Press '+' and select Java
Open the folder the files and Intellij project are open in and open the CS400-Travel-Journal-- folder
Select the file named 'charm-glisten-6.1.0.jar'
Press 'OK'
Press 'OK'

STEP 7
At the bottom of the Libraries menu, press 'Apply', then 'OK'
Run Start.java

Congratulations! The app should be successfully installed and runnable from Start.java!
