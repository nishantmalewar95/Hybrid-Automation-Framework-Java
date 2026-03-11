package api.tests;

import api.base.BaseApiTest; 
import api.utils.ApiUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LosApiTest extends BaseApiTest{
	
	// 1. DataProvider provides multiple sets of data (Username, Password, Expected Status)
	@DataProvider(name="loginData")
	public Object[][] getLoginData(){
		return new Object[][] {
			{"standard_user", "secret_sauce", 201}, // Valid scenario
            {"locked_out_user", "wrong_pass", 201}  // Testing another scenario
		};
	}
	
	// 2. Pass parameters to the method to receive data from DataProvider
	@Test(dataProvider = "loginData")
	public void testLosLogin(String username, String password, int expectedStatus) {
	
		// 3. Construct dynamic JSON body using the parameters
		String loginPayload = "{" +
                "\"title\": \"" + username + "\"," +
                "\"body\": \"" + password + "\"," +
                "\"userId\": 1" +
                "}";
		
		//step 2:Hit using request spec
		Response response = ApiUtils.getRequestSpec()
				.log().uri()
				.body(loginPayload)
				.when()
				.post("/posts") //Base URL will come from base class
			    .then()
			    .extract().response();
		
		// Step 3: Verifications using the dynamic expected status
        Assert.assertEquals(response.getStatusCode(), expectedStatus, "Status code mismatch!");    
        
        Assert.assertNotNull(response.jsonPath().get("id"), "ID field is missing!");
        
        System.out.println("Tested with user: " + username + " | Result: " + response.getStatusCode());
    }
}