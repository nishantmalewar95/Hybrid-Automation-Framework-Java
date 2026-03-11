package api.tests;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.Test; // Sahi wala import

public class JsonPlaceholderSchemaTest {

    @Test
    public void validatePostSchema() {

        useRelaxedHTTPSValidation();
		
        
        baseURI = "https://jsonplaceholder.typicode.com";

        given()
            .get("/posts/1")
        .then()
            .assertThat()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("schema.json"));
            
        System.out.println("Nishant, Schema Validation Passed!");
    }
}