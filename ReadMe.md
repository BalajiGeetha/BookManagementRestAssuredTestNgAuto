
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

**----src/main/java
----src/test/java
----src/test/resources
---pom.xml**

### Below are steps to implement the framework

#### 3.src/main/java contains

##### --Package-configFiles:
i)configReader -- This classes contains method to read the config.properties file
ii)tokenGeneration --  This classes contains tokengeneration , configure the properties.
iii)configBasepath -- This classes contains basepath for all the API

##### --Package-constants:
i)constants -- This classes contains constants static string and integers

##### --Package-pojo:
i)requestBodycreation -- This classes contains all the request body for BookManagement and UserAuthentication
ii)requestBodycreationLogin -- This classes contains all the request body for Login
iii)requestBodycreationSignUp -- This classes contains all the request body for Signup
iv)requestBodyUpdate -- This classes contains all the request body for Update BookManagement

#### 4.src/main/resources

i)config.properties - this file contains path and keys
ii)testNg.xml -- this xml file contains classes to run all test methods
iii)expectedResponse.json -- this file contains expected response to compare against the actual response
iv)token.txt -- this file contains token generated after login

#### 5.src/test/java

---package - tests
i)BookManagementTest -- This class contains all the test methods for Book Management API
ii)HealthCheckTest -- This class contains all the test methods for Health Check API
iii)UserAuthenticationTest -- This class contains all the test methods for User Authentication API

#### i)BookManagementTest

--Test Methods
createBookDetails -- POST Method to create a new book
a)verifyCreateBookDetails -- POST Method to verify the creation of a new book
b)verifyCreateBookDetailsWithIncorrectPath -- POST Method to create a new book with incorrect path
c)VerifyCreateBookDetailsWithIncorrectRequestBody  -- POST Method to create a new book with incorrect request body
d)VerifyTheCreatedBookResponseWithExistingData -- POST Method to create a new book with existing data
e)VerifyCreateBookDetailsWithMissingPath Parameters -- POST Method to create a new book with missing path parameters

##### deleteBookDetails -- DELETE Method to delete the book details by ID

f)verifyDeleteNonExistingBookDetails -- DELETE Method to delete the non existing book details by ID
g)verifyDeleteWithExistingBookDetails -- DELETE Method to delete the existing book details by ID
h)verifyDeleteWithIncorrectPath -- DELETE Method to delete the book details with incorrect path

##### getBookDetails -- GET Method to get the book details by ID

i)verifyBooksStatus -- GET Method to verify the status of the book details by ID
j)VerifyBookCount -- GET Method to verify the count of the book details by ID
k)verifyAllBooksDetails -- GET Method to get all the book details
l)verifyGetSpecificBookDetails -- GET Method to get a specific book details by ID
m)verifyAuthorNameOfBook -- GET Method to verify the author name of the book by ID
n)verifyPublishedYearOfBook -- GET Method to verify the published year of the book by ID
o)verifyBookSummaryOfBook -- GET Method to verify the book summary of the book by ID
p)verifyIncorrectAuthorNameOfTheBook -- GET Method to verify the incorrect author name of the book by ID
q)verifyGetBookDetailsWithInvalidID -- GET Method to get the book details with invalid ID
r)VerifyGetBookDetailsWithIncorrectName -- GET Method to get the book details with incorrect namespace
s)VerifyGetBookDetailsWithIncorrectSummary -- GET Method to get the book details with incorrect summary

##### updateBookDetails -- PUT Method to update the book details by ID

t)verifyUpdateBookDetails -- PUT Method to update the book details by ID
u)verifyUpdateBookDetailsResponseWithNewData -- PUT Method to update the book details with new data by ID
v)verifyUpdateBookDetailsWithInvalidID -- PUT Method to update the book details with invalid ID
w)verifyUpdateBookDetailsIncorrectPath -- PUT Method to update the book details with incorrect path
x)verifyUpdateBookDetailsIncorrectId -- PUT Method to update the book details with incorrect ID

#### ii)HealthCheck

--Test Methods
a)verifyHealthCheck -- GET Method to check the health of the API

#### iii)UserAuthentication

--Test Methods

##### Login

a)verifyLogin -- POST Method to login with valid credentials
b)verifyLoginInvalidCredentials -- POST Method to login with invalid credentials
c)verifyLoginEmptyBody -- POST Method to login with empty body
d)verifyLoginMissingFields -- POST Method to login with missing fields
e)verifyLoginWithoutBody -- POST Method to login without body
f)verifyLoginWithIncorrectPath -- POST Method to login with incorrect path
g)verifyLoginTokenType -- POST Method to verify the token type in the login response

##### SighUp

i)verifySignUp -- POST Method to signup with valid details
j)verifySignUpExistingUser -- POST Method to signup with existing user details
k)verifySignUpEmptyBody -- POST Method to signup with empty body
l)verifySignUpMissingFields -- POST Method to signup with missing fields
m)verifySignUpExtraFields -- POST Method to signup with extra fields
n)verifySignUpWithoutBody -- POST Method to signup without body

#### 6.Testing Framework - TestNG
i)Annotations - @Test, @DataProvider,priority, description
ii)Assertions - AssertEquals, AssertTrue, AssertFalse, AssertNotNull
iii)Test Suites - testNg.xml to run all the test methods

#### 7.Reporting - Allure Report

* i)Dependency - Added Allure dependency in the Pom.xml file
* ii)Annotations - Added Allure annotations in the few test methods
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
