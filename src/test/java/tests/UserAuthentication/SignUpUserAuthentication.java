package tests.UserAuthentication;

import RequestBody.requestBodyCreationSignUp;
import com.fasterxml.jackson.databind.JsonNode;
import configFiles.configReader;
import configFiles.tokenGeneration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.FileNotFoundException;

import static configFiles.configBasepath.configBaseSignUpPath;
import static io.restassured.RestAssured.given;

public class SignUpUserAuthentication {

    public RequestSpecification createBaseRequestSpec() {
        String APIKeys = tokenGeneration.JWTToken();
        RestAssured.baseURI = configReader.getProperty("baseUri");

        return given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIKeys);
    }

    @Test(description ="Verify the SignUp and check the status",priority = 1)
    public void SignUp() throws FileNotFoundException {
        String requestBody = requestBodyCreationSignUp.createLoginRequest().toPrettyString();
        Response response = createBaseRequestSpec().given().contentType(ContentType.JSON).body(requestBody)
                .when()
                .post(configBaseSignUpPath())
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.jsonPath().getString("message"),"User created successfully");

    }
    @Test(description ="Verify the SignUp IncorrectInput",priority = 2)
    public void SignUpWithIncorrectInput() throws FileNotFoundException {
        String requestBody = requestBodyCreationSignUp.createLoginRequest().toPrettyString();
        Response response = createBaseRequestSpec().
                given()
                .contentType(ContentType.JSON).
                body(requestBody+"{}")
                .when()
                .post(configBaseSignUpPath())
                .then().log().all()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(),422);

    }
    @Test(description ="Verify the SignUp with Registered emailId",priority = 3)
    public void SignUpWithRegisteredEmailId() throws FileNotFoundException {
        JsonNode requestBody = requestBodyCreationSignUp.createLoginRequest().
                put("email","nprhcrrz@gmail.com");
        String req = requestBody.toPrettyString();
        Response response = createBaseRequestSpec().
                given().
                contentType(ContentType.JSON).
                body(req)
                .when()
                .post(configBaseSignUpPath())
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(),400);
        Assert.assertEquals(response.jsonPath().getString("detail"),"Email already registered");

    }
    @Test(description ="Verify the SignUp with IncorrectPath",priority = 4)
    public void SignUpWithIncorrectPath() throws FileNotFoundException {
        JsonNode requestBody = requestBodyCreationSignUp.createLoginRequest().
                put("email","nprhcrrz@gmail.com");
        String req = requestBody.toPrettyString();
        Response response = createBaseRequestSpec().
                given().
                contentType(ContentType.JSON).
                body(req)
                .when()
                .post("/signu")
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(),404);
        Assert.assertEquals(response.jsonPath().getString("detail"),"Not Found");

    }
}
