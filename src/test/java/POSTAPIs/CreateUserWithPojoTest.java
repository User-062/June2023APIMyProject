package POSTAPIs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.util.UUID;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.User;

public class CreateUserWithPojoTest {
	
	//1. Direct supply the json string
	//2. pass the json file
	//3. pojo - java objects - to json with the help of jackson/rest assured
	
	
	public static String getRandomEmailId() {
		
		return "apiautomation"+System.currentTimeMillis()+"@mail.com";
		//return "apiautomation"+UUID.randomUUID()+"@mail.com";
		
	}
	
	@Test
	public void addUserTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		User user =new User("Sanjay", getRandomEmailId(), "male", "active");
		
		//1.add user -POST
	int userId	= given().log().all()
			.contentType(ContentType.JSON)
				.body(user)
				.header("Authorization", "Bearer 9a8ff32407603ab822a750a16997a948c4803a41abed9856302151b934b6a965").
			when()
				.post("/public/v2/users/").
			then().log().all()
				.assertThat()
					.statusCode(201)
						.and()
							.body("name", equalTo(user.getName()))
								.extract()
									.path("id");
		System.out.println("User ID is =" + userId);
		
		//get the same user and verify it:GET
		given().log().all()
		.header("Authorization", "Bearer 9a8ff32407603ab822a750a16997a948c4803a41abed9856302151b934b6a965")
			.when().log().all()
				.get("/public/v2/users/" + userId)
					.then().log().all()
						.assertThat()
							.statusCode(200)
								.body("id", equalTo(userId))
									.and()
										.body("name", equalTo(user.getName()))
											.and()
											.body("status", equalTo(user.getStatus()))
												.and()
													.body("email", equalTo(user.getEmail()));
	}
	
}


