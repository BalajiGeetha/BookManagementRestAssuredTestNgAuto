package tests.BookManagement;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import configFiles.*;
import org.testng.asserts.SoftAssert;


import static configFiles.configBasepath.configBaseBookPath;
import static io.restassured.RestAssured.given;


public class getBookDetails {

    @DataProvider(name = "bookID")
    public Object[]dataProvide() {
        return new Object[] {"2"};
    }
    public RequestSpecification createBaseRequestSpec() {
        String APIKeys = tokenGeneration.JWTToken();
        RestAssured.baseURI = configReader.getProperty("baseUri");

        return given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIKeys);
    }

    @Test(description ="Verify the status code",priority = 1)
public JsonPath verifyBooksStatus() throws FileNotFoundException{

       Response response = createBaseRequestSpec().given()
                .when()
                .get(configBaseBookPath())
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(),200);

        return response.jsonPath();

}
    @Test(description ="Verify the book id count from the list",priority = 2)
    public void VerifyBookCount() throws IOException {

        Response response = createBaseRequestSpec().given()
                .when()
                .get(configBaseBookPath())
                .then()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String expectedBody = response.asPrettyString();
        FileWriter storedRes = new FileWriter("src/test/resources/expected-body.json");
        storedRes.write(expectedBody);
        storedRes.close();
        List<Integer> list = jsonPath.getList("id");

        Assert.assertNotEquals(list.size(),0,"Getting an expected count:"+list.size());

    }
    @Test(description ="Verify the all book details from the list",priority = 3)
    public void verifyGetAllBooksDetails() throws IOException {

        Response response = createBaseRequestSpec().given()
                .when()
                .get(configBaseBookPath())
                .then()
                .extract().response();

        Path file = Paths.get("src/test/resources/expected-body.json");
        String content = Files.readString(file, StandardCharsets.UTF_8);
       String Expected = response.asPrettyString();
      Assert.assertEquals(content,Expected,"Stored bookdetails same as actual bookdetails");

    }

    @Test(description ="Verify the specific book from the list",dataProvider = "bookID",priority = 4)
    //@Parameters({"bookID"})
    public void verifyGetSpecificBookDetails(String bookID) throws FileNotFoundException{

        Response response = createBaseRequestSpec().given()
                .when()
                .get(configBaseBookPath()+bookID)
                .then()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
       String specBookDetails = jsonPath.get("name");
        Assert.assertEquals(specBookDetails,"jakson","Getting expected name from the book");

    }

    @Test(description ="Verify the AuthorName from the list",dataProvider = "bookID",priority=5)
    public void verifyAuthorNameOfBook(String bookID) throws FileNotFoundException{

        Response response = createBaseRequestSpec().given()
                .when()
                .get(configBaseBookPath()+bookID)
                .then()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String specBookDetails = jsonPath.get("author");
        Assert.assertEquals(specBookDetails,"Steve","getting expected author name from the book");

    }
    @Test(description ="Verify the PublishedYear from the list",dataProvider = "bookID",priority = 6)
    public void verifyPublishedYearOfBook(String bookID) throws FileNotFoundException{

        Response response = createBaseRequestSpec().given()
                .when()
                .get(configBaseBookPath()+bookID)
                .then()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int specBookDetails = jsonPath.get("published_year");

        Assert.assertEquals(specBookDetails,1995,"Getting expected published year from the book");


    }
    @Test(description ="Verify the BookSummary from the list",dataProvider = "bookID",priority = 7)
    public void verifyBookSummaryOfBook(String bookID) throws FileNotFoundException{

        Response response = createBaseRequestSpec().given()
                .when()
                .get(configBaseBookPath()+bookID)
                .then()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String specBookDetails = jsonPath.get("book_summary");
        Assert.assertEquals(specBookDetails,"A novel set in the Jazz Age on Long Island","Summary details are not same");


    }
    @Test(description ="Verify the IncorrectAuthorName from the list",dataProvider = "bookID",priority = 8)
    public void verifyIncorrectAuthorNameOfTheBook(String bookID) throws FileNotFoundException{

        Response response = createBaseRequestSpec().given()
                .when()
                .get(configBaseBookPath()+bookID)
                .then()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String specBookDetails = jsonPath.get("author");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(specBookDetails,"Fitzgerald","Expected Author found in the list");
        softAssert.assertAll();

    }


    @Test(description ="Verify the book details with InvalidID",priority = 9)
    public void verifyGetBookDetailsWithInvalidID() throws FileNotFoundException{
        int bookID = 123;
        Response response = createBaseRequestSpec().given()
                .when()
                .get(configBaseBookPath()+bookID)
                .then().statusCode(404)
                .extract().response();

        Assert.assertEquals(response.getBody().asString(),"{\"detail\":\"Book not found\"}","Getting 404 error for the Invalid BookId");

    }
    @Test(description ="Verify the IncorrectName from the list",dataProvider = "bookID",priority = 10)
    public void VerifyGetBookDetailsWithIncorrectName(String bookID) throws FileNotFoundException{

        Response response = createBaseRequestSpec().given()
                .when()
                .get(configBaseBookPath()+bookID)
                .then()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String specBookDetails = jsonPath.get("name");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(specBookDetails,"TheGatsby","Expected Author found in the list");
        softAssert.assertAll();

    }
    @Test(description ="Verify the IncorrectSummary from the list",dataProvider = "bookID",priority = 11)
    public void VerifyGetBookDetailsWithIncorrectSummary(String bookID) throws FileNotFoundException{

        Response response = createBaseRequestSpec().given()
                .when()
                .get(configBaseBookPath()+bookID)
                .then()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String specBookDetails = jsonPath.get("book_summary");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(specBookDetails,"A humorous science fiction story following the misadventures of Arthur Dent after the Earth is demolished to make way for a hyperspace bypass.","Expected summary details are matching");
        softAssert.assertAll();

    }

    @Test(description ="Verify the IncorrectCharacterInBookId from the list",dataProvider = "bookID",priority = 12)
    public void VerifyGetBookDetailsWithIncorrectCharacterInBookId(String bookID) throws FileNotFoundException{

        Response response = createBaseRequestSpec().given()
                .when()
                .get(configBaseBookPath()+bookID+"@@")
                .then().statusCode(422)
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String msg = jsonPath.getString("detail.msg").replaceAll("\\[|\\]","");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(msg,"Input should be a valid integer, unable to parse string as an integer","Input accept only Integer");
        softAssert.assertAll();

    }
    @Test(description ="Verify the IncorrectUrl from the list",dataProvider = "bookID",priority = 13)
    public void VerifyGetBookDetailsWithIncorrectPath(String bookID) throws FileNotFoundException{

        Response response = createBaseRequestSpec().given()
                .when()
                .get("/boo"+bookID+"@@")
                .then()
                .extract().response();

     Assert.assertEquals(response.getStatusCode(),404,"Resource path is incorrect");
    }

}
