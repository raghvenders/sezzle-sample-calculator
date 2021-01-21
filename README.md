# Sezzle-Sample-Calculator
A Sample online Calculator which shares all user's computation results live.


# Using this repository

You can run this standalone basic arithmetic calculator as a java web application, or you can run it as container runtime in docker environment, as it gives
the cloud native enablement to host it as a live public site.


## Prerequisites

To use this repository, you need the following installed locally:

<B>Note :</B> Below mentioned binaries are used for local development (MacOS Catalina).

- [Node](https://nodejs.org/) - Node - v10.15.1
- [Java](https://www.oracle.com/java/) - Java version "1.8.0_171"
- [Docker](https://www.docker.com/) - Container Runtime Docker  - Docker desktop community

## Clone , Build and Run

Clone the repository and navigate to the directory

```
git clone https://github.com/raghvenders/sezzle-sample-calculator.git
cd sezzle-sample-calculator
```

## Build

To build the project as a standalone java web application

```
./gradlew bootRun
```

This will install the node dependencies needed for UI module, compiling java, and building classes and resources.
Now spring boot will be available to run as a web application.

<B>Note :</B> This runs without gradle installed as it runs with gradle wrapper, which is already part of the project.
First time run will take time as gradle wrapper downloads the external distribution and build the dependencies.


To build the application in a container, run the following to build the container image and run it:

```
./gradlew bootWar
docker build -t <username>/sezzle-sample-calculator .
docker run -p 8080:8080 <username>/sezzle-sample-calculator
```

<B>Note :</B> Kindly make sure the installed docker is running.


## Development

This project can be imported as a gradle project in intellij and we can flexibly use various other gradle tasks and run dependencies.
For Javascript, react plugins can be used for intellij or use vscode/chrome browser to modify or validate the changes.

-Password Generator util will help you to encrypt the password to be used in LDAP conf - test-server.ldif


## Use the Application
1. Launch the application locally - http://127.0.0.1:8080 or http://localhost:8080

2. Login as one of the below users in the login page
```
     tom/apple
     harry/orange
```
3.Do the work in your calculator and "=" will bring you the result and add it to the live result board.

4.The result board keeps track of latest 10 results of both the users.

5.The user session time out is 15 minutes.

6.Anytime you can log in back, and see the latest results.

## Known Information and trouble shooting
1. The application is currently hosted on http://ec2-54-226-155-58.compute-1.amazonaws.com:8080/ and it is not https yet.

2. If any update is not reflecting in the result board due to Websocket connection lost or as load results are not synchronized possibly out of sync, 
   browser refresh does help to get the latest result or we could synchronize the operation at a cost.

3. If we tend to build and run project multiple times or run in both approach we might encounter port binding error 8080.
Either modify the port in application.properties and docker run command or kill the existing process.


4. Make sure 8389 port is not occupied, as it will be used by the LDAP server for user authentication.

6. React Jest dom and spring boot Junits are yet to be updated.






