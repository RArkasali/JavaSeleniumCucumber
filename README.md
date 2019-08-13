Pre-Condition:
--------------

Java jdk 1.8 or greater should be Installed.

Latest version of Chrome and Mozilla browser should be Installed.

Latest version of Git should be Installed.

Latest version of Maven should be Installed.

User Guide to Execute UI and Api Test:
1.Download the workspace and Keep in local directories.

2.Open the workspace in intellij.

3.To start execution

                 - In left panel open the qa-challenge project 
                 
                 - Go to src->main->resources->config.properties , 
	                    - mention the base url for UI automation.
                        - mention the browser to be executed.
	                    - mention the service url for rest api automation.	
	                    - mention the api key for rest api automation.
	                     		
                 -  In qa-challenge open the directory called Testsuite.
                 
                 -  In Testsuite open the Testng.xml file.
                 
                 -  In opened Testng file ,right click in the inside panel of file.
                 
                 -  And click Run(This will execute both Api and UI test).
                 
                 -  After execution go to reports - AutomationReport.html to see the results.
                 
To Execute the Api test separately
----------------------------------

                 -  In qa-challenge project , Go to src->test->runner->ApiRunTest.java file.
                                  
                 -  In opened ApiRunTest.java file ,right click in the inside panel of file.
                                  
                 -  And click Run(This will execute Api test).
                 
                 -  After execution go to reports - AutomationReport.html to see the result.
                 
To Execute the UI test separately
---------------------------------

                -  In qa-challenge project , Go to src->test->runner->UiRunTest.java file.
                                  
                 -  In opened UiRunTest.java file ,right click in the inside panel of file.
                                  
                 -  And click Run(This will execute UI test).
                 
                 -  After execution go to reports - AutomationReport.html to see the result.
                 
Jenkins configuration
---------------------

Precondition:

    In Jenkins - > Manage Jenkins - > configuration , mention git hub servers details.
    
    Configure Email notification.
      
    Maven should be installed and the path of the maven should be mentioned in Jenkins.
    

Install latest version of Jenkins 

Login to Jenkins and click on new Job - http://localhost:8080/newJob 

Enter an item name "qa-challenge" 

Click Free style project and click Ok 

Enter the general description "qa challenge project" 

In the source code management , select Git

Give the repository url qa-challenge.git

Add the credentials

In the Build Triggers section select "GitHub hook trigger for GITScm polling"

In the Build environment check "Delete workspace before build starts"

In build section select "Execute Windows batch command" and enter the command - 
    cd qa-challenge
    mvn test 

In Post build section select "Email notification" and enter the recipients to receive the email.

Click Save.

After saving the project , view the project http://localhost:8080/job/qa-challenge/
 
Click Build Now.

Go to the recent build.

Click Console output to view the running job logs. 

After the execution the recipients will get an email notification.