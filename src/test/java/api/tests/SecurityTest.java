package api.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.useRelaxedHTTPSValidation;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;import net.bytebuddy.implementation.bytecode.assign.Assigner.EqualTypesOnly;

public class SecurityTest {
	
	@Test
	public void testBearerTokenWithJsonPlaceholder() {
		
		useRelaxedHTTPSValidation();

		
		//Base URI for JSONPlaceholder
		RestAssured.baseURI ="https://jsonplaceholder.typicode.com";
		
		//Step 1:Mock Toekn
		String myToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9_nishant_123";
		
		//step 2 : Post Request with Authorization Header
		given()
		     .header("Authorization","Bearer" + myToken)
		     .contentType(ContentType.JSON)
		     .body("{" +
	                    "\"title\": \"SDET Task 3\"," +
	                    "\"body\": \"Testing Bearer Token Logic\"," +
	                    "\"userId\": 1" +
	                  "}")
		  .when()
		       .post("/posts")
		  .then()
		       .log().all()
		       .statusCode(201)
		       .body("title", equalTo("SDET Task 3"));
	}

}
