package api.tests;

import static io.restassured.RestAssured.*;

public class AuthDemo {
	
	@org.testng.annotations.Test
	public void testBasicAuth() {
		//for bypass the SSL
		useRelaxedHTTPSValidation();
		
		given()
		  .auth().basic("postman","password")
		.when()
		  .get("https://postman-echo.com/basic-auth")
		 .then()
		   .statusCode(200)
		   .log().body();
		
		
	}

}
