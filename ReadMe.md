
### Application Name - Book Management API, Health Check API, User Authentication API

### Framework: Java RestAssured with TestNg Framework

### Report Generation: Allure Report

# ****Steps and Implementation*****

#### 1.Make sure Java and Apache maven is installed and configured properly

#### 2.Created maven project with Pom.xml - 

The following dependencies are added in the Pom.xml file
* RestAssured
* TestNg
* JsonPath
* Jackson Databind
* Allure TestNg
* Allure RestAssured

#### Project Structure

* src
  * main
    * java
      * configFiles
      * constants
      * pojo
    * resources
  * test
    * java
      * tests

### Below are steps to implement the framework

#### 3.src/main/java contains

##### --Package-configFiles:
1. configReader -- This classes contains method to read the config.properties file
2. tokenGeneration --  This classes contains tokengeneration , configure the properties.
3. configBasepath -- This classes contains basepath for all the API

##### --Package-constants:
* constants -- This classes contains constants static string and integers

##### --Package-pojo:
* requestBodycreation -- This classes contains all the request body for BookManagement and UserAuthentication
* requestBodycreationLogin -- This classes contains all the request body for Login
* requestBodycreationSignUp -- This classes contains all the request body for Signup
* requestBodyUpdate -- This classes contains all the request body for Update BookManagement

#### 4.src/main/resources

* config.properties - this file contains path and keys
* testNg.xml -- this xml file contains classes to run all test methods
* expectedResponse.json -- this file contains expected response to compare against the actual response
* token.txt -- this file contains token generated after login

#### 5.src/test/java

---package - tests
1. BookManagementTest -- This class contains all the test methods for Book Management API
2. HealthCheckTest -- This class contains all the test methods for Health Check API
3. UserAuthenticationTest -- This class contains all the test methods for User Authentication API

#### i)BookManagementTest

--Test Methods
createBookDetails -- POST Method to create a new book
* verifyCreateBookDetails -- POST Method to verify the creation of a new book
* verifyCreateBookDetailsWithIncorrectPath -- POST Method to create a new book with incorrect path
* verifyCreateBookDetailsWithIncorrectRequestBody  -- POST Method to create a new book with incorrect request body
* verifyTheCreatedBookResponseWithExistingData -- POST Method to create a new book with existing data
* verifyCreateBookDetailsWithMissingPath Parameters -- POST Method to create a new book with missing path parameters

##### deleteBookDetails -- DELETE Method to delete the book details by ID

* verifyDeleteNonExistingBookDetails -- DELETE Method to delete the non existing book details by ID
* verifyDeleteWithExistingBookDetails -- DELETE Method to delete the existing book details by ID
* verifyDeleteWithIncorrectPath -- DELETE Method to delete the book details with incorrect path

##### getBookDetails -- GET Method to get the book details by ID

* verifyBooksStatus -- GET Method to verify the status of the book details by ID
* VerifyBookCount -- GET Method to verify the count of the book details by ID
* verifyAllBooksDetails -- GET Method to get all the book details
* verifyGetSpecificBookDetails -- GET Method to get a specific book details by ID
* verifyAuthorNameOfBook -- GET Method to verify the author name of the book by ID
* verifyPublishedYearOfBook -- GET Method to verify the published year of the book by ID
* verifyBookSummaryOfBook -- GET Method to verify the book summary of the book by ID
* verifyIncorrectAuthorNameOfTheBook -- GET Method to verify the incorrect author name of the book by ID
* verifyGetBookDetailsWithInvalidID -- GET Method to get the book details with invalid ID
* verifyGetBookDetailsWithIncorrectName -- GET Method to get the book details with incorrect namespace
* verifyGetBookDetailsWithIncorrectSummary -- GET Method to get the book details with incorrect summary

##### updateBookDetails -- PUT Method to update the book details by ID

* verifyUpdateBookDetails -- PUT Method to update the book details by ID
* verifyUpdateBookDetailsResponseWithNewData -- PUT Method to update the book details with new data by ID
* verifyUpdateBookDetailsWithInvalidID -- PUT Method to update the book details with invalid ID
* verifyUpdateBookDetailsIncorrectPath -- PUT Method to update the book details with incorrect path
* verifyUpdateBookDetailsIncorrectId -- PUT Method to update the book details with incorrect ID

#### ii)HealthCheck

--Test Methods
* verifyHealthCheck -- GET Method to check the health of the API

#### iii)UserAuthentication

--Test Methods

##### Login

* verifyLogin -- POST Method to login with valid credentials
* verifyLoginInvalidCredentials -- POST Method to login with invalid credentials
* verifyLoginEmptyBody -- POST Method to login with empty body
* verifyLoginMissingFields -- POST Method to login with missing fields
* verifyLoginWithoutBody -- POST Method to login without body
* verifyLoginWithIncorrectPath -- POST Method to login with incorrect path
* verifyLoginTokenType -- POST Method to verify the token type in the login response

##### SighUp

* verifySignUp -- POST Method to signup with valid details
* verifySignUpExistingUser -- POST Method to signup with existing user details
* verifySignUpEmptyBody -- POST Method to signup with empty body
* verifySignUpMissingFields -- POST Method to signup with missing fields
* verifySignUpExtraFields -- POST Method to signup with extra fields
* verifySignUpWithoutBody -- POST Method to signup without body

#### 6.Testing Framework - TestNG
* Annotations - @Test, @DataProvider,priority, description
* Assertions - AssertEquals, AssertTrue, AssertFalse, AssertNotNull
* Test Suites - testNg.xml to run all the test methods

#### 7.Reporting - Allure Report

* Dependency - Added Allure dependency in the Pom.xml file
* Annotations - Added Allure annotations in the few test methods
@Feature, @Story, @Description, @Severity


#### Execute and Report Generation

*****Run a test*****
Step 1: Navigate to the project directory in the command prompt
Step 2: Run the following command to execute the test cases and generate the allure-results folder

`###### mvn clean test`

Step 3: After the test execution, you will find the allure-results folder in the project directory
Step 4: To generate the Allure report, run the following commands in the command prompt
*****Generate the Allure Report*****

`###### allure generate allure-results --clean -o allure-report`

`###### allure open allure-report`
