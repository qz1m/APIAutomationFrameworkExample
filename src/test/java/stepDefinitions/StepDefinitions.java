package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinitions extends Utils {
	
	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	Response response;
	static String placeId;
	TestDataBuild data = new TestDataBuild();
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		
		requestSpec = given().spec(requestSpecification())
		.body(data.addPlacePayload(name, language, address));
	}
	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		
	 	APIResources resourceAPI = APIResources.valueOf(resource);
	  	System.out.println(resourceAPI.getResource());
		
		responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST"))
		response = requestSpec.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))
		response = requestSpec.when().get(resourceAPI.getResource());
	}
	@Then("the API call is success with response code {int}")
	public void the_api_call_is_success_with_response_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
	   assertEquals(getJsonPath(response, keyValue), expectedValue);
	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		placeId = getJsonPath(response, "place_id");
		requestSpec = given().spec(requestSpecification()).queryParam("place_id", placeId);
		user_calls_with_http_request(resource, "GET");
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName, expectedName);
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	 	requestSpec = given().spec(requestSpecification()).body(data.deletePlacePayload(placeId));
		
	}

}
