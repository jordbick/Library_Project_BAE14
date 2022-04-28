src/main/java coverage: 76.1%

# CRUD-Based Web Application using Spring Boot/Java: A Personal Library
Fullstack project for BAE14 Academy <br><br>
Web-based application that allows a user to interact with a database via an API. The user can create, read, update and delete entries.

## Getting Started

Follow these instructions to get a copy of the project on your local machine for development and testing purposes

### Prerequisites

#### Java
[Download JDK](https://www.oracle.com/java/technologies/downloads/)

Browse to **Edit the system environment variable** and then click **Environment Variables...**  
Add the location of the *jdk* folder to a new variable called **JAVA_HOME** in the **System Variables**.

```e.g.: C:\Program Files\Java\jdk-17.0.1```

Then add the **JAVA_HOME** with the *bin* folder to the **Path** variable like so:
```%JAVA_HOME%\bin```

To verify your JDK installation, launch a command prompt window and type:  
```javac - version```  and    ```java -version```  



#### Maven
[Download Maven](https://maven.apache.org/download.cgi)  
Click the Binary zip archive download under 'Files'.  
```apache-maven-3.8.5-bin.zip``` 

Unzip the file into the C:\Program Files folder

Browse to **Edit the system environment variable** and then click **Environment Variables...**  
Add the location of the *apache-maven* folder to a new variable called **M2_HOME** in the **System Variables**.

```e.g.: C:\Program Files\apache-maven-3.8.5```


Then add the **M2_HOME** with the *bin* folder to the **Path** variable like so:
```%JAVA_HOME%\bin```


To verify installation launch a command prompt window and type:  
```mvn -version```



#### Java IDE
I have used Eclipse for this project
[Download Eclipse](https://www.eclipse.org/downloads/) 



#### SpringBoot
Once within Eclipse navigate to *Help* and then *Eclipse Marketplace*

Search for and install **Spring Tools 4**




#### MySQL Server
[Download MySQL](https://dev.mysql.com/downloads/windows/installer/8.0.html)

Complete the setup wizard

## Installing

### Clone project from GitHub
Open git bash in desired location to save repository and use the command:
```git clone https://github.com/jordbick/Library_Project_BAE14.git```

### Import project to Eclipse
In Eclipse, *File > Import > Maven > Existing Maven Project*
Ensure the **pom.xml** file is selected

### Running the Application
To run the application from Eclipse, right-click the *LibraryProjectBae14Application.java* file and *Run As > Spring Boot App*

This should run the app on your local host on port 8080

Now you can navigate to the URL:
``` http://localhost:8080/```

Alternatively to run the app from the command line, browse to the root folder of the project and execute the command:
```java -jar Library_Project_BAE14-0.0.1-SNAPSHOT.jar```

## Testing
To run all the tests, right click the project and select *Run As > JUnit test*

The tests can be run individually by navigating to class within the test folder and doing the same as above

## Jira
[Link to Jira Board](https://jordanbick.atlassian.net/jira/software/projects/LPB/boards/3)

## Author
[Jordan Bickerdyke](https://github.com/jordbick)
