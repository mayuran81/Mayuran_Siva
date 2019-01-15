#Parental Control Service

Parent Control Service takes required movie and the rating as input. 
It then determines whether the given movie is permitted to watch.

Service will return true/false as an outcome of the operation. If there is any exception due to the
service call, It will be logged and thrown back to the calling user. logging is purely for any investigation purpose.

## Getting Started

1. download the code from the given url, dowload it as a zip file
2. unzip mayuran_Siva.zip
3. cd Mayuran_Siva

### Environment Requirement

Java 1.8, Maven

### Running

Run the following maven command from the command prompt. command prompt should be in the same folder
level as where parentControl level. This is the same level as where pom file is

As an example

c:\dev\ParentControl

Run the following command

mvn clean install

### Run the tests

mvn test

### Notes

check the test coverage of the project by navigating to

Mayuran_Siva\target\site\jacoco

open index file in the browser to view the code coverage



