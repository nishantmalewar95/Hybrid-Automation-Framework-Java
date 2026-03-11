package api.base;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class BaseApiTest {
	
	@BeforeClass
	public void setup() {
		//Global setting for Base URL
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		RestAssured.useRelaxedHTTPSValidation();
	}
	
	//Common request setup ( headers,Auth etc.)
	protected RequestSpecification getRequestSpec() {
		return given()
				.header("Content-type","application/json")
				.log().all();// Optional: added for better visibility
	}

}
