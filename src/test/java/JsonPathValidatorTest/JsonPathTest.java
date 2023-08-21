package JsonPathValidatorTest;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JsonPathTest {

	
	@Test

	public void getCircuitDataAPIWith_YearTest() {
		
	RestAssured.baseURI = "http://ergast.com";
	
	Response response =	given().log().all()
					.when().log().all()
						.get("/api/f1/2017/circuits.json");
					
	String jsonResponse = response.asString();
	System.out.println(jsonResponse);
	
	int totalCircuits = JsonPath.read(jsonResponse, "$.MRData.CircuitTable.Circuits.length()");
	System.out.println(totalCircuits);
	
	List<String> countryList = JsonPath.read(jsonResponse, "$..Circuits..country");
	System.out.println(countryList.size());
	System.out.println(countryList);
}
	@Test
	public void getProductTest() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response response = given()
			.when()
				.get("/products");
		
		String jsonResponse = response.asString();
		System.out.println(jsonResponse);
		
		List<Float> rateLessThanThree = JsonPath.read(jsonResponse, "$[?(@.rating.rate < 3)].rating.rate");
		System.out.println(rateLessThanThree);
		
		//With two Attributes
		List<Map<String, Object>> jewelleryList= JsonPath.read(jsonResponse, "$[?(@.category == 'jewelery')].[\"title\",\"price\"]");
		
		System.out.println(jewelleryList);
		
		
		for(Map<String, Object> product: jewelleryList) {
			String title = (String)product.get("title");
			Object price = (Object)product.get("price");
			
			System.out.println("Title : " + title);
			System.out.println("price :" + price);
			System.out.println("----------------------");
		}
		
		//With three Attributes
		List<Map<String, Object>> jewelleryList2= JsonPath.read(jsonResponse, "$[?(@.category == 'jewelery')].[\"title\",\"price\",\"id\"]");
		
		System.out.println(jewelleryList2);
		
		for(Map<String, Object> product: jewelleryList2) {
			String title = (String)product.get("title");
			Object price = (Object)product.get("price");
			Integer idList = (Integer)product.get("id");
			
			System.out.println("Title : " + title);
			System.out.println("price :" + price);
			System.out.println("id :" + idList);
			System.out.println("----------------------");
		}

		
	}

}