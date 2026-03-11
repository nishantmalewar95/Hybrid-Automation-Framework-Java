package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import api.base.BaseApiTest;
import api.utils.ApiUtils;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

/**
 * This class inherits from BaseApiTest to utilize common configurations
 * like BaseURI and Request Specifications.
 */
public class RestAssuredBasicsTest extends BaseApiTest {

    @Test
    public void testGetSinglePost() {
        // Using getRequestSpec() instead of given() to avoid rewriting headers
            getRequestSpec()
            .when()
            .get("/posts/1")
            .then()
            .statusCode(200)
                .body("id", equalTo(1))
                .body("title", notNullValue());
    }

    @Test
    public void testGetPostWithLogging() {
        // Specifically using log().all() to see the full response in console
             getRequestSpec()
            .when()
            .get("/posts/1")
            .then()
            .log().all()
            .statusCode(200);
    }

    @Test
    public void testCreatePost() {
        // Using Text Blocks (Java 15+) for a clean JSON payload
          String requestBody = """
                {
                "title": "SDET practice",
                "body" : "learning Rest Assured",
                "userId": 1
                }
                """;

            getRequestSpec()
           .body(requestBody) // Attaching the payload to the request
           .when()
           .post("/posts") // Sending POST request to create a resource
            .then()
                .log().all()
                .statusCode(201) // 201 indicates successful creation
                .body("id", notNullValue())
                .body("title", equalTo("SDET practice"));
    }
    
    @Test
    public void testGetNonExistentPost() {
    	// We are intentionally passing an ID that doesn't exist
    	getRequestSpec()
    	.when()
    	.get("/posts/999999")
    	.then()
    	.log().all() // Observe the 404 error in console
    	.statusCode(404); // Assert that server returns 'Not Found'
    	
    }
    
    @Test
    public void testInvalidRoute() {
    	//Trying to access a wrong endpoint URL
    	getRequestSpec()
    	.when()
    	.get("/invalid-route-name")
    	.then()
    	.statusCode(404);
    }
    
    @Test
    public void testUpdatePost() {
        // Note: Title matches the assertion exactly (Case Sensitive)
        String requestBody = """
                {
                "id": 1,
                "title": "Updated SDET title",
                "body": "updated body content",
                "userId": 1
                }
                """;
        
        Response response = getRequestSpec()
                .body(requestBody)
                .when()
                .put("/posts/1")
                .then()
                .statusCode(200)
                .extract().response();
        
        String updatedTitle = response.jsonPath().getString("title");
        
        // Assertion fixed to match the payload casing
        Assert.assertEquals(updatedTitle, "Updated SDET title", "Title was not updated correctly!");
        System.out.println("PUT Success: Title updated to -> " + updatedTitle);
    }

    @Test
    public void testDeletePost() {
        Response response = getRequestSpec()
                .when()
                .delete("/posts/1")
                .then()
                .statusCode(200)
                .extract().response();
        
        Assert.assertEquals(response.getStatusCode(), 200, "Delete request failed!");
        System.out.println("Delete success: Post 1 deleted successfully.");
    }
    
    @Test
    public void testGetWithUtils() {
    	// Calling static method from ApiUtils class
    	Response response = ApiUtils.getRequestSpec()
    			            .when()
    			            .get("/posts/1");
    	
    	//using our utility method for validation
    	ApiUtils.verifyStatus(response, 200);
    	
    	System.out.println("Verification successful using ApiUtils!");
    }
    
    @Test
    public void testBasicAuth() {
    	// Basic Auth method: Rest Assured automatically handles encoding
    	Response response = io.restassured.RestAssured.given()
    			.auth()
    			.basic("admin", "password123") // Credentials provided here
    			.when()
    			.get("https://httpbin.org/basic-auth/admin/password123")
    			.then()
    			.statusCode(200)
    			.extract().response();
    	
    	// Verification: Checking if 'authenticated' field in JSON is true
    	boolean isAuthenticated = response.jsonPath().getBoolean("authenticated");
    	Assert.assertTrue(isAuthenticated,"Basic Auth failed!");
    	System.out.println("Basic Auth Success: User is authenticated.");
    }
    
    @Test
    public void testBearerToken() {
    	// Dummy token for demonstration
    	String myToken = "your_token_here";
    	
    	io.restassured.RestAssured.given()
    	.header("Authorization", "Bearer " + myToken) // Header format is important
    	.when()
    	.get("https://api.realworld.io/api/articles")
    	.then()
    	// Note: If token is invalid, you might get 401. 
        // For learning, we check the flow.
        .log().headers();
    	
    }
    
    
    
   
    
    
}