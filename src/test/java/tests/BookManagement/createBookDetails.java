package tests.BookManagement;

import configFiles.configReader;
import configFiles.tokenGeneration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import static configFiles.configBasepath.configBaseBookPath;
import static io.restassured.RestAssured.given;

public class createBookDetails {

    public RequestSpecification createBaseRequestSpec() {
        String APIKeys = tokenGeneration.JWTToken();
        RestAssured.baseURI = configReader.getProperty("baseUri");

        return given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIKeys);
    }

    @Test(description ="Verify the CreateAndUploadBookDetails and check the status",priority = 1)
    public void CreateAndUploadBookDetails() throws FileNotFoundException {
        String requestBody = RequestBody.requestBodyCreation.createRequest();
        Response response = createBaseRequestSpec().given().contentType(ContentType.JSON).body(requestBody)
                .when()
                .post(configBaseBookPath())
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(),200);

    }
    @Test(description ="Verify the response for the createBookDetails",priority = 2)
    public void VerifyTheCreatedResponse() throws FileNotFoundException {
        String requestBody = RequestBody.requestBodyCreation.createRequest();
        Response response = createBaseRequestSpec().given().contentType(ContentType.JSON).body(requestBody)
                .when()
                .post(configBaseBookPath())
                .then()
                .extract().response();

        JsonPath ExpectedJson = JsonPath.given(requestBody);
        Assert.assertEquals(response.jsonPath().getString("name"),ExpectedJson.getString("name"));

    }
    @Test(description ="Verify the createBookDetails from IncorrectPath",priority = 3)
    public void VerifyIncorrectPath() throws FileNotFoundException {
        String requestBody = RequestBody.requestBodyCreation.createRequest();
        Response response = createBaseRequestSpec().given().contentType(ContentType.JSON).body(requestBody)
                .when()
                .post("/books")
                .then().log().all()
                .extract().response();


        Assert.assertEquals(response.getStatusCode(),307);

    }
    @Test(description ="Verify the createBookDetails from IncorrectRequestBody",priority = 4)
    public void VerifyIncorrectRequestBody() throws FileNotFoundException {
        String requestBody = RequestBody.requestBodyCreation.createRequest();
        Response response = createBaseRequestSpec().given().contentType(ContentType.JSON).body(requestBody+"{}")
                .when()
                .post(configBaseBookPath())
                .then()
                .extract().response();


        Assert.assertEquals(response.getStatusCode(),422,"Getting Different status code");

        Assert.assertTrue(true, "Getting Expected error code - Incorrect Request body or Json");

    }
    @Test(description ="Verify the createBookDetails from MissingResourcePath",priority = 5)
    public void VerifyMissingPath() throws FileNotFoundException {
        String requestBody = RequestBody.requestBodyCreation.createRequest();
        Response response = createBaseRequestSpec().given().contentType(ContentType.JSON).body(requestBody)
                .when()
                .post("")
                .then()
                .extract().response();


        Assert.assertEquals(response.getStatusCode(),404,"Getting Different status code");

        Assert.assertTrue(true, "Getting Expected error code - Requested resource path is missing");
    }
}
