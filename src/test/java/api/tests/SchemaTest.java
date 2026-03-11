package api.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import com.aventstack.extentreports.gherkin.model.Then;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class SchemaTest {

	@Test
	public void validateContract() {
		
		useRelaxedHTTPSValidation();
		
		RestAssured.baseURI ="https://jsonplaceholder.typicode.com";
		 
		given()
		.get("/posts/1")
        .then()
        .assertThat()
        .statusCode(200)
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"))
        .log().all();
        
        System.out.println("Nishant, Schema Validation Passed! Data types are correct.");
    }
	}


