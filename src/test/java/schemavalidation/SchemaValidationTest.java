package schemavalidation;

import static io.restassured.RestAssured.given;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class SchemaValidationTest {
	
			@Test
	
			public void addUserSchemaTest() {
			
			RestAssured.baseURI = "https://gorest.co.in";
			
			//1.add user -POST
			given().log().all()
				.contentType(ContentType.JSON)
					.body(new File("./src/test/resources/data/adduser.json"))
					.header("Authorization", "Bearer 9a8ff32407603ab822a750a16997a948c4803a41abed9856302151b934b6a965").
				when()
					.post("/public/v2/users/").
				then().log().all()
					.assertThat()
						.statusCode(201)
							.and()
								.body(matchesJsonSchemaInClasspath("createuserschema.json"));
									
			
		
	}
			
			@Test
			
			public void getUserSchemaTest() {
			
			RestAssured.baseURI = "https://gorest.co.in";
			
			//1.add user -POST
			given().log().all()
				.contentType(ContentType.JSON)
					.header("Authorization", "Bearer 9a8ff32407603ab822a750a16997a948c4803a41abed9856302151b934b6a965").
				when()
					.get("/public/v2/users/").
				then().log().all()
					.assertThat()
						.statusCode(200)
							.and()
								.body(matchesJsonSchemaInClasspath("getuserschema.json"));
									

}

}