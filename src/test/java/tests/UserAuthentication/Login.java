package tests.UserAuthentication;

import configFiles.configReader;
import configFiles.tokenGeneration;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import RequestBody.requestBodyCreationLogin.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import static configFiles.configBasepath.configBaseLoginPath;
import static io.restassured.RestAssured.given;

public class Login {

    public RequestSpecification createBaseRequestSpec() {
        String APIKeys = tokenGeneration.JWTToken();
        RestAssured.baseURI = configReader.getProperty("baseUri");

        return given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIKeys);
    }

    @Feature("User Authentication")
    @Story("Login API")
    @Description("Verify the login functionality with valid credentials")
    @Severity(io.qameta.allure.SeverityLevel.CRITICAL)
    @Test(description ="Verify the login status code",priority = 1)
    public void verifyLogin() throws FileNotFoundException {

        String requestBody = RequestBody.requestBodyCreationLogin.createLoginRequest("nprhcrrz").toPrettyString();

        Response response = createBaseRequestSpec().given().body(requestBody)
                .when()
                .post(configBaseLoginPath())
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(),200);


    }

    @Test(description ="Verify the Login token type",priority = 2)
    public void verifyLoginTokenType() throws IOException {

        String requestBody = RequestBody.requestBodyCreationLogin.createLoginRequest("nprhcrrz").toPrettyString();

        Response response = createBaseRequestSpec().given().body(requestBody)
                .when()
                .post(configBaseLoginPath())
                .then()
                .extract().response();
        FileWriter storedRes = new FileWriter("src/test/resources/token.txt");
        storedRes.write(response.jsonPath().getString("access_token"));
        storedRes.close();

        Assert.assertEquals(response.jsonPath().getString("token_type"),"bearer");
        Assert.assertNotEquals(response.jsonPath().getString("access_token"),null);


    }
    @Test(description ="Verify the Login with InvalidCredentials",priority = 3)
    public void verifyLoginInvalidCredentials() throws FileNotFoundException {

        String requestBody = RequestBody.requestBodyCreationLogin.createLoginRequest("np").toPrettyString();

        Response response = createBaseRequestSpec().given().body(requestBody)
                .when()
                .post(configBaseLoginPath())
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(),400);
        Assert.assertEquals(response.jsonPath().
                getString("detail"),"Incorrect email or password");


    }
    @Test(description ="Verify the Login with EmptyBody",priority = 4)
    public void verifyLoginEmptyBody() throws FileNotFoundException {

        String requestBody = "{}";

        Response response = createBaseRequestSpec().given().body(requestBody)
                .when()
                .post("/login")
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(),400);


    }
    @Test(description ="Verify the Login with WithoutBody",priority = 5)
    public void verifyLoginWithoutBody() throws FileNotFoundException {

        String requestBody = "";

        Response response = createBaseRequestSpec().given().body(requestBody)
                .when()
                .post("/login")
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(),422);


    }
    @Test(description ="Verify the Login with MissingFields",priority = 6)
    public void verifyLoginMissingFields() throws FileNotFoundException {

        String requestBody = RequestBody.requestBodyCreationLogin.createLoginRequest("np").remove("password").toPrettyString();

        Response response = createBaseRequestSpec().given().body(requestBody)
                .when()
                .post("/login")
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(),422);


    }
    @Test(description ="Verify the Login with Incorrect Path",priority = 7)
    public void LoginIncorrectPath() throws FileNotFoundException {

        String requestBody = RequestBody.requestBodyCreationLogin.createLoginRequest("np").toPrettyString();

        Response response = createBaseRequestSpec().given().body(requestBody)
                .when()
                .post("/log")
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(),404);


    }


}
