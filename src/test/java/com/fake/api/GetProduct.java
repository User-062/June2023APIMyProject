package com.fake.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetProduct {
	@Test
	public void getProductTest_BDD_Style_1() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
	Response response =	given()
			.when()
			.get("/products");
				
										
		
	}
}

