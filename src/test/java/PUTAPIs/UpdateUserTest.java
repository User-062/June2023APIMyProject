package PUTAPIs;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*; 

public class UpdateUserTest {
	
	//create user - Post --->userId
	//update user - PUT ---->/userId
	
public static String getRandomEmailId() {
		
		return "apiautomation"+System.currentTimeMillis()+"@mail.com";
		//return "apiautomation"+UUID.randomUUID()+"@mail.com";
		
	}
	
	@Test
	public void updateUserTest() {
		RestAssured.baseURI = "https://gorest.co.in";

		User user = new User("Sanjay Kumar", getRandomEmailId(), "male", "active");
		
		//1. POST - Create User

		Response response = RestAssured.given().log().all()
				.contentType(ContentType.JSON)
				.header("Authorization", "Bearer 9a8ff32407603ab822a750a16997a948c4803a41abed9856302151b934b6a965")
				.body(user)
				.when().log().all()
					.post("/public/v2/users");

		Integer userId = response.jsonPath().get("id");
		System.out.println("userId  :" + userId);
		
		System.out.println("-------------------------------------------------");
		
		
		//update the existing user
		user.setName("Sanjay Sagar");
		
		user.setStatus("inactive");
		
		//2.PUT - Update User
		
		RestAssured.given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer 9a8ff32407603ab822a750a16997a948c4803a41abed9856302151b934b6a965")
		.body(user)
		.when()
			.put("/public/v2/users/" +userId)
				.then().log().all()
					.assertThat()
						.statusCode(200)
							.and()
								.assertThat()
									.body("id", equalTo(userId))
										.and()
											.body("name",equalTo(user.getName()))
												.and()
													.body("status", equalTo(user.getStatus()));
				}

}
