package api.tests;

// Static imports for RestAssured and Hamcrest Matchers
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;
import api.models.Post;

// Log4j2 imports for logging framework
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PostWithPojoTest {
	
    // Initialize Logger for this specific class
    private static final Logger logger = LogManager.getLogger(PostWithPojoTest.class);
	
    @Test
    public void createPostUsingPojo() {
        // Bypass SSL certificate validation for secure connections
        useRelaxedHTTPSValidation();
        
        logger.info("--- STARTING TEST: createPostUsingPojo ---");
        
        // Initialize POJO and set data for the request body
        Post postData = new Post("Architect Task", "Learning POJO Serialization with Logs", 1);
        logger.info("Payload prepared for task: {}", postData.getTitle());
        
        // Perform the API POST request
        logger.info("Sending POST request to https://jsonplaceholder.typicode.com/posts");
        
        var response = given()
            .baseUri("https://jsonplaceholder.typicode.com")
            .header("Content-Type", "application/json")
            .body(postData)
        .when()
            .post("/posts");

        // Capture and log the API response status code
        logger.info("Response Received. Status Code: {}", response.getStatusCode());
        
        // Perform Assertions using RestAssured then() block
        response.then()
            .statusCode(201)
            .body("title", equalTo("Architect Task"));
            
        logger.info("Assertion Passed: Post successfully created with expected title.");
        logger.info("--- TEST COMPLETED SUCCESSFULLY ---");
    }
}