BookManagement Application
API Testing- RestAssured with TestNg Framework
Report-Allure Report

****Steps and Implementation*****

1.Make sure Java and Apache maven is installed and configured properly
2.Create maven project
3.In Pom.xml - Add all dependency related to RestAssured, TestNG, JSON and Allure Report
4. Project Structure
----src/main/java
----src/test/java
----src/test/resources

5.src/main/java contains
--Package-configFiles -- This classes contains tokengeneration , configure the properties
--constants -- This classes contains constants static string and integers
--RequestBody -- This classes contain pojo class to generate the request body for Book management, Login, SignUp and update

6.src/test/java
---package - tests
--tests.BookManagement -- This classes contains test methods to do CRUD operation for BookManagement(create,get,update and delete)
27 TestCases --
TestNG Framework which is having @annotation(test,description,priority and dataprovider)
i) Use Hard and soft assertion to compare the actual and expected response
ii)Implement the Fileinput and output stream to save the file and compare against the actual response
iii)Use @test method to run the test , @description to descripe postive and negative testcase, priority to priorities the testcases
dataprovider is pass the input to testmethods

--tests.HealthCheck -- To check the application health
1 TestCase
i) Use Hard and soft assertion to compare the actual and expected response
--tests.UserAuthentication  -- This classes contain Login and Signup
8 Testcases ---TestNG Framework which is having @annotation(test,description,priority)
i) Use Hard and soft assertion to compare the actual and expected response


7.src/test/resources
--config.properties - this file contains path and keys
--testNg.xml -- this xml file contains classes to run all test methods

8. Execute and Report Generation
Allure Report - Detailed Report to specify the Pass , Failures

***Run a test***
mvn clean test

***Generate the Allure Report***

i) allure generate allure-results --clean -o allure-report
ii) allure open allure-report
