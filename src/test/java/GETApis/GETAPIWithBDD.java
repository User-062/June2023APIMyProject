package GETApis;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class GETAPIWithBDD {

	@Test
	
	public void getProductsTest() {
		
		given().log().all()
			.when().log().all()
				.get("https://fakestoreapi.com/products")
					.then().log().all()
						.assertThat()
							.statusCode(200)
								.and()
									.contentType(ContentType.JSON)
										.and()
											.header("Connection","keep-alive")
												.and()
													.body("$.size()", equalTo(20))
														.and()
															.body("id", is(notNullValue()))
																.and()
																	.body("title", hasItem("Mens Cotton Jacket"));
																	
		
	}
	
	
	
		@Test
	
		public void getUserAPITest() {
		RestAssured.baseURI = "https://gorest.co.in";
	
		given().log().all()
			.header("Authorization", "Bearer 9a8ff32407603ab822a750a16997a948c4803a41abed9856302151b934b6a965")
				.when().log().all()
					.get("/public/v2/users/")
						.then().log().all()
							.assertThat()
								.statusCode(200)
										.and()
											.contentType(ContentType.JSON)
														.and()
															.body("$.size()", equalTo(10));
					
	}	
	
		@Test

		public void getProductDataAPIWithQueryParamTest() {
		RestAssured.baseURI = "https://fakestoreapi.com/";
		
			given().log().all()
				.queryParam("limit", 5)
				.queryParam("name", "sanjay")
						.when().log().all()
							.get("/products")
								.then().log().all()
									.assertThat()
										.statusCode(200)
											.and()
												.contentType(ContentType.JSON);
		}
		@Test

		public void getProductDataAPI_With_Extract_Body() {
		RestAssured.baseURI = "https://fakestoreapi.com/";
		
			Response response = given().log().all()
									.queryParam("limit", 5)
										.queryParam("name", "sanjay")
													.when().log().all()
														.get("/products");
							
			JsonPath js	= response.jsonPath();			
			
			//Get the id of first Product
			int firstProductId = js.getInt("[0].id");
			System.out.println("firstProductId = " + firstProductId);
			
			String 	firstProductTitle = js.getString("[0].title");
			System.out.println("firstProductTitle = " + firstProductTitle);
			
			float price = js.getFloat("[0].price");
			System.out.println("price = " + price);
			
			int count = js.getInt("[0].rating.count");
			System.out.println("count = " + count);
			
		}							
		
		@Test

		public void getProductDataAPI_With_Extract_Body_WithJSONArray() {
		RestAssured.baseURI = "https://fakestoreapi.com/";
		
			Response response = given().log().all()
									.queryParam("limit", 10)
													.when().log().all()
														.get("/products");
							
			JsonPath js	= response.jsonPath();		
			
			
			List<Integer> idList = js.getList("id");
			List<String> titleList = js.getList("title");
			//List<Object> rateList = js.getList("rating.rate");
			
			List<Float> rateList = js.getList("rating.rate",Float.class);
			
			List<Integer> countList = js.getList("rating.count");
			
			for(int i =0; i<idList.size(); i++) {
				int id = idList.get(i);
				String title = titleList.get(i);
				Float rate = rateList.get(i);
				Integer count = countList.get(i);
			
			
			System.out.println("ID: = " + id + " " + "Tiltle: = " + title + " " + "Rate:  = " + rate + " " + "Count: = " + count);
			
			}							
		}
		
		@Test

		public void getUserAPI_With_Extract_Body_WithJSON() {
			RestAssured.baseURI = "https://gorest.co.in";
			
			Response response = given().log().all()
				.header("Authorization", "Bearer 9a8ff32407603ab822a750a16997a948c4803a41abed9856302151b934b6a965")
					.when().log().all()
						.get("/public/v2/users/3614597");
			
			JsonPath js = response.jsonPath();
			
			System.out.println(js.getInt("id"));
			System.out.println(js.getString("email"));
			
		
		}
		
		
		@Test

		public void getUserAPI_With_Extract_Body_WithJSON_Extract() {
			RestAssured.baseURI = "https://gorest.co.in";
//			
//			int userId = given().log().all()
//				.header("Authorization", "Bearer 9a8ff32407603ab822a750a16997a948c4803a41abed9856302151b934b6a965")
//					.when().log().all()
//						.get("/public/v2/users/3614597")
//							.then()
//								.extract().path("id");
//			
//			
//			System.out.println(userId);
			
			
			Response response = given().log().all()
				.header("Authorization", "Bearer 9a8ff32407603ab822a750a16997a948c4803a41abed9856302151b934b6a965")
					.when().log().all()
						.get("/public/v2/users/3614597");
							
					int userId = response.then().extract().path("id");
					String email = response.then().extract().path("email");
			
			System.out.println(userId);
			System.out.println(email);
			
		}
		
		
	
}
