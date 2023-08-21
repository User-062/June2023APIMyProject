package DeleteAPIs;

import org.testng.annotations.Test;

import PUTAPIs.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*; 

public class DeleteUserTest {
	//1. post - create user - userID --201
	//2. delete - delete user -- /userID -- 204
	//3. get -- get user -- /userID -- 404 - Resource not found
	
public static String getRandomEmailId() {
		
		return "apiautomation"+System.currentTimeMillis()+"@mail.com";
		//return "apiautomation"+UUID.randomUUID()+"@mail.com";
		
	}
	
	@Test
	public void deleteUserTest() {
		RestAssured.baseURI = "https://gorest.co.in";

		User user = new User("Sanjay Kumar", getRandomEmailId(), "male", "active");
		
		//1. POST - Create User

		Response response = RestAssured.given().log().all()
				.contentType(ContentType.JSON)
				.header("Authorization", "Bearer 9a8ff32407603ab822a750a16997a948c4803a41abed9856302151b934b6a965")
				.body(user)
				.when().log().all()
					.post("/public/v2/users");
		
		response.prettyPrint();

		Integer userId = response.jsonPath().get("id");
		System.out.println("userId  :" + userId);
		
		System.out.println("-------------------------------------------------");
		
		//2. Delete - delete user
		
		RestAssured.given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer 9a8ff32407603ab822a750a16997a948c4803a41abed9856302151b934b6a965")
		.when().log().all()
			.delete("/public/v2/users/"+userId)
			.then().log().all()
				.assertThat()
					.statusCode(204);
		
		//3. Get the user -- GET:
		
		RestAssured.given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer 9a8ff32407603ab822a750a16997a948c4803a41abed9856302151b934b6a965")
		.when().log().all()
			.get("/public/v2/users/"+userId)
			.then().log().all()
				.assertThat()
					.statusCode(404)
						.assertThat()
							.body("message", equalTo("Resource not found"));
		
			

}
}