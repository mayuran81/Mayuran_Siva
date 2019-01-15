# Parental Control Service

Parent Control Service takes movie id and the rating as input. 
It determines whether the given movie is permitted to watch.

Service will return true/false as an outcome of the operation. If any exception due to the
service call, It will be logged and thrown back to the calling user. logging is for any investigation purpose.

## Getting Started

1. download the code from the given url, dowload it as a zip file
2. unzip mayuran_Siva.zip
3. cd Mayuran_Siva\ParentControl

### Environment Requirement

Java 1.8, Maven

### Running

Run the following maven command from the command prompt. command prompt should be in the same folder
level as where pom file of the downloaded project is.

As an example if the downloaded zip is in c:\dev\Mayuran_Siva, cd to ParentControl

c:\dev\Mayuran_Siva\ParentControl

Run the following command

mvn clean install

### Run the tests

mvn test

### Notes

check the test coverage of the project by navigating to

Mayuran_Siva\ParentControl\target\site\jacoco

open index file in the browser to view the code coverage



