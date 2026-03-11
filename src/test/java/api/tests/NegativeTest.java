package api.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class NegativeTest {

	private static final Logger logger = LogManager.getLogger(NegativeTest.class);
	
	@Test
	public void validate04NotFoundForInvalidId() {
		
		useRelaxedHTTPSValidation();
		
		logger.info("--- STARTING NEGATIVE TEST: validate404NotFound ---");
		
		//setting base URI for the invalid resource
		logger.info("Requesting post with non-existent ID: 999");
		
		given()
		    .baseUri("https://jsonplaceholder.typicode.com")
		.when()
		    .get("/posts/999")
		.then()
		     .statusCode(404)  //validate the status code is 404
		     .body("isEmpty()", is(true))
		     .log().all();
		
		logger.info("Negative Test Passed: Received 404 and empty body as expected.");
        logger.info("--- NEGATIVE TEST COMPLETED ---");
		     
		 
	
	
	}

}
