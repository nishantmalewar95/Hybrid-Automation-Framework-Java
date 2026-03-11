package BasicPractice;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class GetAndPostExamples {
	
	@Test
	public void testGet() {
		// 1. BaseURI change ki
		baseURI="https://jsonplaceholder.typicode.com";
		
		given()
			.get("/users") // JSONPlaceholder mein /users list milti hai
		    .then()
			.statusCode(200)
			// 2. JSONPlaceholder ka data structure alag hai (isliye "data[4]" ki jagah "[4]" use kiya)
			.body("[4].name", equalTo("Chelsey Dietrich")) 
			.body("name", hasItems("Leanne Graham", "Ervin Howell"));
	}
	
	@Test
	public void testPost() {
		// 3. JSONObject ka use karke body banana
		JSONObject request = new JSONObject();
		
		request.put("title", "SDET Learning");
		request.put("body", "Rest Assured is easy");
		request.put("userId", 1);
		
		System.out.println("Payload: " + request.toJSONString());
		
		baseURI = "https://jsonplaceholder.typicode.com";
		
		given()
			.header("Content-Type", "application/json")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(request.toJSONString())
		    .when()
			.post("/posts") // Endpoint change kiya
		    .then()
			.statusCode(201)
			.log().all();
	}
}