package tests.BookManagement;

import configFiles.configReader;
import configFiles.tokenGeneration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static configFiles.configBasepath.configBaseBookPath;
import static io.restassured.RestAssured.given;

public class updateBookDetails {

    public RequestSpecification createBaseRequestSpec() {
        String APIKeys = tokenGeneration.JWTToken();
        RestAssured.baseURI = configReader.getProperty("baseUri");

        return given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIKeys);
    }

    @Test(description ="Verify the UpdateBookDetails and check the status",priority = 1)
    public void UpdateBookDetails() throws FileNotFoundException {

        String requestBody = RequestBody.requestBodyUpdate.updateRequest("Steve","jakson",1995,
                "A novel set in the Jazz Age on Long Island");
        Response response = createBaseRequestSpec().given().contentType(ContentType.JSON).body(requestBody)
                .when()
                .put(configBaseBookPath()+2)
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(),200);

    }
    @Test(description ="Verify the UpdateBookDetails and check the response",priority = 2)
    public void VerifyUpdateBookDetailsResponse() throws FileNotFoundException {

        String requestBody = RequestBody.requestBodyUpdate.updateRequest("Steve","jakson",1995,
                "A novel set in the Jazz Age on Long Island");
        Response response = createBaseRequestSpec().given().contentType(ContentType.JSON).body(requestBody)
                .when()
                .put(configBaseBookPath()+2)
                .then()
                .extract().response();
        Assert.assertEquals(response.jsonPath().getString("author"),"Steve");
        Assert.assertEquals(response.jsonPath().getString("name"),"jakson");
        Assert.assertEquals(response.jsonPath().getString("published_year"),"1995");
        Assert.assertEquals(response.jsonPath().getString("book_summary"),"A novel set in the Jazz Age on Long Island");
        Assert.assertTrue(true, "Getting Expected response");
    }
    @Test(description ="Verify the UpdateBookDetails without passing ID",priority = 3)
    public void UpdateBookDetailsWithoutPassingId() throws FileNotFoundException {

        String requestBody = RequestBody.requestBodyUpdate.updateRequest("Steve","jakson",1995,
                "A novel set in the Jazz Age on Long Island");
        Response response = createBaseRequestSpec().given().contentType(ContentType.JSON).body(requestBody)
                .when()
                .put(configBaseBookPath())
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(),405);
        Assert.assertTrue(true, "Method is not allowed without passing the ID");

    }
    @Test(description ="Verify the UpdateBookDetails with IncorrectPath",priority = 4)
    public void UpdateBookDetailsIncorrectPath() throws FileNotFoundException {

        String requestBody = RequestBody.requestBodyUpdate.updateRequest("Steve","jakson",1995,
                "A novel set in the Jazz Age on Long Island");
        Response response = createBaseRequestSpec().given().contentType(ContentType.JSON).body(requestBody)
                .when()
                .put("/boo")
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(),404);
        Assert.assertTrue(true, "Path is incorrect");

    }
    @Test(description ="Verify the UpdateBookDetails with IncorrectId",priority = 5)
    public void UpdateBookDetailsIncorrectId() throws FileNotFoundException {

        String requestBody = RequestBody.requestBodyUpdate.updateRequest("Steve","jakson",1995,
                "A novel set in the Jazz Age on Long Island");
        Response response = createBaseRequestSpec().given().contentType(ContentType.JSON).body(requestBody)
                .when()
                .put(configBaseBookPath()+555)
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(),404);
        Assert.assertEquals(response.jsonPath().get("detail"),"Book not found");

    }
}
