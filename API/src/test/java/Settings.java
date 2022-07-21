import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class Settings {
	
	public String baseURI = "https://trello.com";

	public RequestSpecification setupBaseURI() {
		
		RestAssured.baseURI = baseURI;
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("Content-Type: application/json","Accept: application/json");
		
		return httpRequest;
	}
	
	public String getValueJson(String response,String id) {

		JsonPath jsonPath = new JsonPath(response);
		String extracted = jsonPath.get(id);
		return extracted;
		
	}
	
	
	public void checkStatusCodeSuccessfull(Response response) {
		
		 Assert.assertEquals(response.getStatusCode(),200);
		
		
	}
	
}
