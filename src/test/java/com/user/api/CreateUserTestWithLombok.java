package com.user.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fake.api.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserTestWithLombok {
	
	
public static String getRandomEmailId() {
		
		return "apiautomation"+System.currentTimeMillis()+"@mail.com";
		//return "apiautomation"+UUID.randomUUID()+"@mail.com";
		
	}
	

	@Test
	public void CreateUserTest() {
		RestAssured.baseURI = "https://gorest.co.in";

		User user = new User("Sanjay Kumar", getRandomEmailId(), "male", "active");

		Response response = RestAssured.given().contentType(ContentType.JSON)
				.header("Authorization", "Bearer 9a8ff32407603ab822a750a16997a948c4803a41abed9856302151b934b6a965")
				.body(user).when().post("/public/v2/users");

		Integer userId = response.jsonPath().get("id");
		System.out.println("userId  :" + userId);

		// GET API to get the same user:

		// 2.get the same user and verify it: GET

		Response getResponse = RestAssured.given()
				.header("Authorization", "Bearer 9a8ff32407603ab822a750a16997a948c4803a41abed9856302151b934b6a965")
				.when().log().all().get("/public/v2/users/" + userId);

		// de-serialization:

		ObjectMapper mapper = new ObjectMapper();

		try {
			User userRes = mapper.readValue(getResponse.getBody().asString(), User.class);

			System.out.println(
					userRes.getId() + ":" + userRes.getEmail() + ":" + userRes.getStatus() + ":" + userRes.getGender());
			Assert.assertEquals(userId, userRes.getId());
			Assert.assertEquals(user.getName(), userRes.getName());
			Assert.assertEquals(user.getEmail(), userRes.getEmail());

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	@Test
	public void CreateUser_WithBuilderPattern_Test() {
		RestAssured.baseURI = "https://gorest.co.in";

		User user = new User.UserBuilder()
			.name("Sanjay")
			.email(getRandomEmailId())
			.status("active")
			.gender("male")
			.build();

		Response response = RestAssured.given().log().all()
				.contentType(ContentType.JSON)
				.header("Authorization", "Bearer 9a8ff32407603ab822a750a16997a948c4803a41abed9856302151b934b6a965")
				.body(user)
				.when().log().all()
				.post("/public/v2/users");

		Integer userId = response.jsonPath().get("id");
		System.out.println("userId  :" + userId);

		// GET API to get the same user:

		// 2.get the same user and verify it: GET

		Response getResponse = RestAssured.given()
				.header("Authorization", "Bearer 9a8ff32407603ab822a750a16997a948c4803a41abed9856302151b934b6a965")
				.when().log().all().get("/public/v2/users/" + userId);

		// de-serialization:

		ObjectMapper mapper = new ObjectMapper();

		try {
			User userRes = mapper.readValue(getResponse.getBody().asString(), User.class);

			System.out.println(
					userRes.getId() + ":" + userRes.getEmail() + ":" + userRes.getStatus() + ":" + userRes.getGender());
			Assert.assertEquals(userId, userRes.getId());
			Assert.assertEquals(user.getName(), userRes.getName());
			Assert.assertEquals(user.getEmail(), userRes.getEmail());

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
