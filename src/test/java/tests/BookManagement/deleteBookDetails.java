package tests.BookManagement;

import configFiles.configReader;
import configFiles.tokenGeneration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import Constants.constants.*;
import java.io.FileNotFoundException;
import java.util.List;
import static Constants.constants.BookId;
import static io.restassured.RestAssured.given;
import static configFiles.configBasepath.configBaseBookPath;

public class deleteBookDetails {
    public RequestSpecification createBaseRequestSpec() {
        String APIKeys = tokenGeneration.JWTToken();
        RestAssured.baseURI = configReader.getProperty("baseUri");

        return given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + APIKeys);
    }

    @Test(description ="Verify the status code",priority = 1)
    public void DeleteBooksStatus() throws FileNotFoundException {

        getBooks books = new getBooks();

        List<Integer> list = books.GetBooksStatus().get("id");

        for(int i=3;i<4;i++) {
            BookId = list.get(i);
            if(BookId != 0) {
                Response response = createBaseRequestSpec().given()
                        .when()
                        .delete(configBaseBookPath() + BookId)
                        .then()
                        .extract().response();


                Assert.assertEquals(response.getStatusCode(), 200);
            }
            else{
                System.out.println("No bookId is available for delete");
            }
        }

    }
    @Test(description ="Verify the status message",priority = 2)
    public void DeleteBooksStatusMessage() throws FileNotFoundException {

        getBooks books = new getBooks();

        List<Integer> list = books.GetBooksStatus().get("id");

        for(int i=2;i<3;i++) {
            BookId = list.get(i);
            if(BookId != 0) {
                System.out.println(BookId);
                Response response = createBaseRequestSpec().given()
                        .when()
                        .delete(configBaseBookPath() + BookId)
                        .then()
                        .extract().response();


                Assert.assertEquals(response.jsonPath().getString("message"), "Book deleted successfully");
            }
            else{
                System.out.println("No bookId is available for delete");
            }
        }

    }

    @Test(description ="Verify the DeleteBooksStatus With IncorrectId",priority = 3)
    public void DeleteBooksStatusWithIncorrectId() throws FileNotFoundException {

        BookId = 77;
                Response response = createBaseRequestSpec().given()
                        .when()
                        .delete(configBaseBookPath()+ BookId)
                        .then()
                        .extract().response();


                Assert.assertEquals(response.getStatusCode(), 404);
        Assert.assertEquals(response.jsonPath().getString("detail"), "Book not found");
            }

    @Test(description ="Verify the DeleteBooksStatus With IncorrectPath",priority = 4)
    public void DeleteBooksStatusWithIncorrectPath() throws FileNotFoundException {

        BookId = 77;
        Response response = createBaseRequestSpec().given()
                .when()
                .delete("/boo" + BookId)
                .then()
                .extract().response();


        Assert.assertEquals(response.getStatusCode(), 404);

    }




}
