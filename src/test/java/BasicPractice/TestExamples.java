package BasicPractice;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class TestExamples {

    @Test
    public void test_1() {
        // ReqRes ki jagah JSONPlaceholder ka URL use kiya
        Response response = get("https://jsonplaceholder.typicode.com/posts/1");

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Time: " + response.getTime());
        System.out.println("Body: " + response.getBody().asString());
        System.out.println("Status Line: " + response.getStatusLine());
        System.out.println("Header: " + response.getHeader("content-type"));
        
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }
    
    @Test
    public void test_2() {
        // Base URI set ki
        baseURI = "https://jsonplaceholder.typicode.com";
        
        given()
            .header("Content-Type", "application/json")
        .when()
            .get("/posts") // /posts list mangwayi
        .then()
            .statusCode(200)
            // JSONPlaceholder mein data array nahi hota, seedha list hoti hai
            // Isliye "data[1].id" ki jagah "[1].id" use kiya hai
            .body("[1].id", equalTo(2)) 
            .log().all();
    }
}