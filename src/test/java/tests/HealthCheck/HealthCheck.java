package tests.HealthCheck;

import configFiles.configReader;
import configFiles.tokenGeneration;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static io.restassured.RestAssured.given;

public class HealthCheck {
    public RequestSpecification createBaseRequestSpec() {
        String APIKeys = tokenGeneration.JWTToken();
        RestAssured.baseURI = configReader.getProperty("baseUri");

        return given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIKeys);
    }
    @Feature("Health Check")
    @Story("Health Status API")
    @Description("Verify the Health check status")
    @Severity(io.qameta.allure.SeverityLevel.CRITICAL)
    @Test(description ="Verify the Health check status")
    public void HealthStatus() throws FileNotFoundException {

        Response response = createBaseRequestSpec().given()
                .when()
                .get("/health")
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(),200,"Application is not up");
        Assert.assertEquals(response.getBody().jsonPath().get("status").toString(),"up");

    }
}
