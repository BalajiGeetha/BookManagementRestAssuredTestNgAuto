package tests.UserAuthentication;

import RequestBody.requestBodyCreationSignUp;
import com.fasterxml.jackson.databind.JsonNode;
import configFiles.configReader;
import configFiles.tokenGeneration;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
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
    @Feature("User Authentication")
    @Story("SignUp API")
    @Description("Verify the SignUp functionality with valid credentials")
    @Severity(io.qameta.allure.SeverityLevel.CRITICAL)
    @Test(description ="Verify the SignUp and check the status",priority = 1)
    public void verifySignUp() throws FileNotFoundException {
        String requestBody = requestBodyCreationSignUp.createSignUpRequest().toPrettyString();
        Response response = createBaseRequestSpec().given().contentType(ContentType.JSON).body(requestBody)
                .when()
                .post(configBaseSignUpPath())
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.jsonPath().getString("message"),"User created successfully");

    }
    @Test(description ="Verify the SignUp ExtraFields",priority = 2)
    public void verifySignUpExtraFields() throws FileNotFoundException {
        String requestBody = requestBodyCreationSignUp.createSignUpRequest().put("name","steve").toPrettyString();
        Response response = createBaseRequestSpec()
                .given()
                .contentType(ContentType.JSON).
                body(requestBody)
                .when()
                .post(configBaseSignUpPath())
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(),200);

    }
    @Test(description ="Verify the SignUp with ExistingUser",priority = 3)
    public void verifySignUpExistingUser() throws FileNotFoundException {
        JsonNode requestBody = requestBodyCreationSignUp.createSignUpRequest().
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
        JsonNode requestBody = requestBodyCreationSignUp.createSignUpRequest().
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
    @Test(description ="Verify the SignUp with EmptyBody",priority = 5)
    public void verifySignUpEmptyBody() throws FileNotFoundException {

        String req = "{}";
        Response response = createBaseRequestSpec().
                given().
                contentType(ContentType.JSON).
                body(req)
                .when()
                .post("/signup")
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(),500);


    }
    @Test(description ="Verify the SignUp WithoutBody",priority = 6)
    public void verifySignUpWithoutBody() throws FileNotFoundException {

        String req = "";
        Response response = createBaseRequestSpec().
                given().
                contentType(ContentType.JSON).
                body(req)
                .when()
                .post("/signup")
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(),422);


    }
}
