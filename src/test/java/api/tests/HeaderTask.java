package api.tests;

import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager; // Log4j Import
import org.apache.logging.log4j.Logger;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.useRelaxedHTTPSValidation;


public class HeaderTask {
	
	//initialize logger
	private static final Logger logger = LogManager.getLogger(HeaderTask.class);
	
	@Test
	public void testHeaderAndAuth() {
		
		useRelaxedHTTPSValidation();

		//step 1: Base URI set
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		
		//logger
		logger.info("----Starting APU request for Header Task ---");
		
	
		//step 2: Request send with header
		Response response = given().header("Content-Type","application/json")// Custom Header
				               .header("my-custom-id","Nishant_123")  // You can also send your header
				            // If there was a Bearer token: .header("Authorization", "Bearer your_token_here")
				            .when()
				               .get("/posts/1")
				            .then()
				               .statusCode(200) //validation
				               .extract().response();
		
		logger.info("Api request successfully with status code: "+response.getStatusCode());
		
		// Step 3: Extract all headers and print them
		Headers allHeaders = response.getHeaders();
		
		logger.info("Extracting response Headers...");
		
		for(Header h:allHeaders) {
			System.out.println("Header Name: "+ h.getName() + "| Value: "+ h.getValue());
			
		}
		
		//print one specific header
		String serverHeader = response.getHeader("Server");
        logger.warn("Server detected as: "+ serverHeader);
	}

}
