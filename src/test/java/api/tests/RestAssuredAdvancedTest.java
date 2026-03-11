package api.tests;

import org.testng.Assert;
import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;
import api.base.BaseApiTest;
import io.restassured.response.Response;
import java.util.List;
import static org.hamcrest.Matchers.*;

/**
 * This class handles complex validations like JSON parsing, 
 * List extractions, and index-based verification.
 */
public class RestAssuredAdvancedTest extends BaseApiTest {

    @Test
    public void testValidateListResponse() {
        // Step 1: Fetch multiple posts using the common request specification
        Response response = getRequestSpec()
                            .when()
                                .get("/posts")
                            .then()
                                .statusCode(200)
                                .extract().response();

        // Step 2: Extract all 'id' fields from the JSON array into a Java List
        List<Integer> allIds = response.jsonPath().getList("id");

        // Step 3: Validate the list size and content
        System.out.println("Total posts fetched: " + allIds.size());
        
        // Assertions to ensure data integrity
        Assert.assertTrue(allIds.size() > 0, "Validation Failed: List should not be empty");
        Assert.assertEquals(allIds.get(0), 1, "Validation Failed: First ID mismatch");
        
        // Step 4: Verify specific fields at a particular index using JSONPath
        // [9].title refers to the title of the 10th element in the array
        String tenthTitle = response.jsonPath().getString("[9].title");
        System.out.println("10th Post Title: " + tenthTitle);
        Assert.assertNotNull(tenthTitle, "Validation Failed: Title at index 9 is null");
    }

    @Test
    public void testVerifyResponseHeaders() {
        // Using getRequestSpec to verify server-side headers
        getRequestSpec()
            .when()
                .get("/posts/1")
            .then()
                .header("Content-Type", containsString("application/json"))
                .header("Cache-Control", notNullValue());
    }
    
    @Test
    public void validateApiResponse() {
    	useRelaxedHTTPSValidation();
    	
    	given()
    	.baseUri("https://jsonplaceholder.typicode.com")
        .basePath("/posts/1")
    	.when()
    	   .get()
    	.then()
    	   .statusCode(200)
    	   .time(lessThan(2000L))
    	   .body("id", equalTo(1))
           .body("userId", equalTo(1))
           .body("title", notNullValue())
           .log().all();
    	
    	System.out.println("JSONPlaceholder: Status, Time, and Contract validated!");
    
    }
    
    @Test
    public void testDatabaseValidationWithPlaceholder() {
    	//fetch api title 
    	String apiTitle = given()
    			 .baseUri("https://jsonplaceholder.typicode.com")
    			 .get("/posts/1")
    			 .jsonPath().getString("title");
    	
    	String dbTitle ="sunt aut facere repellat provident occaecati excepturi optio reprehenderit ";
    	
    	if(apiTitle.equals(dbTitle)) {
    		System.out.println("DB Match Success: API Title matches Database!");
    	}else {
			System.out.println("DB Match Failed!");
		}
    	
    	}
    			
    
    
}