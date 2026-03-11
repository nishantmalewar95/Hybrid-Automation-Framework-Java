package api.utils;

import static io.restassured.RestAssured.given;
import org.testng.Assert;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiUtils {
    
    // Method 1: Request Specification
    public static RequestSpecification getRequestSpec() {
        return given()
                .header("Content-Type","application/json")
                .log().all();
    }

    // Method 2: Post with Token (Isme Base URI update kar lena)
    public static Response postWithToken(String endpoint, Object body, String token) {
        return given()
                .baseUri("https://your-api-url.com") 
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(body)
                .log().all()
                .post(endpoint);
    }
    
    // Method 3: Status Verification
    public static void verifyStatus(Response response, int expectedStatus) {
        Assert.assertEquals(response.statusCode(), expectedStatus, "Status code mismatch!");
    }
}