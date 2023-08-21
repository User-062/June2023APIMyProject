package POSTAPIs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class BookingAuthTest {

	@Test
	public void getBookingAuthTokenTest_WithJSON_String() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		String tokenId = given()
			.contentType(ContentType.JSON)
			.body("{\n"
					+ "    \"username\" : \"admin\",\n"
					+ "    \"password\" : \"password123\"\n"
					+ "}")
			.when()
				.post("/auth")
					.then()
						.assertThat()
							.statusCode(200)
								.extract()
									.path("token");
		
		
		System.out.println(tokenId);
		Assert.assertNotNull(tokenId);
	}
	
	@Test
	public void getBookingAuthTokenTest_With_JSON_File() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		String tokenId = given()
			.contentType(ContentType.JSON)
			.body(new File("./src/test/resources/data/basicauth.json"))
			.when()
				.post("/auth")
					.then()
						.assertThat()
							.statusCode(200)
								.extract()
									.path("token");
		
		
		System.out.println(tokenId);
		Assert.assertNotNull(tokenId);	
	
	}
	
	//post ---- add a user -- user id -- 123 -- assert(201,body)
	//get  ----> get a user --> /users/123 -- 200 --uerid = 123
	
	@Test
	public void addUserTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		//1.add user -POST
	int userId	= given().log().all()
			.contentType(ContentType.JSON)
				.body(new File("./src/test/resources/data/adduser.json"))
				.header("Authorization", "Bearer 9a8ff32407603ab822a750a16997a948c4803a41abed9856302151b934b6a965").
			when()
				.post("/public/v2/users/").
			then().log().all()
				.assertThat()
					.statusCode(201)
						.and()
							.body("name", equalTo("Sanjay kumar"))
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
								.body("id", equalTo(userId));
					
	}
	
}
