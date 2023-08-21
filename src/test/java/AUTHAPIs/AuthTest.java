package AUTHAPIs;



import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class AuthTest {

	//allure report
	@BeforeTest
	public void allureSetup() {
		RestAssured.filters(new AllureRestAssured());
	}
	
	@Test
	public void jwtAuthWithJsonBody() {
		RestAssured.baseURI ="https://fakestoreapi.com";
		String  jwtTokenId     = RestAssured.given()
					.contentType(ContentType.JSON)
					.body("{\n"
							+ "    \"username\": \"mor_2314\",\n"
							+ "    \"password\": \"83r5^_\"\n"
							+ "}")
					.when()
						.post("/auth/login")
							.then()	
								 .assertThat().statusCode(200)
								 	.and()
								 		.extract().path("token");
		System.out.println(jwtTokenId);
	
		//eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjIsInVzZXIiOiJtb3JfMjMxNCIsImlhdCI6MTY5MDk3NDYzNX0.z2B3EFkTspYsy7_TggMSMt9Qm3Bg2mh71t3zqkD75SQ
		
	String tokenArr[] = jwtTokenId.split("\\.");
	System.out.println(tokenArr[0]);
	System.out.println(tokenArr[1]);
	System.out.println(tokenArr[2]);
	
						
	}
	
	@Test
	public void basicAuthTest() {
		RestAssured.baseURI ="https://the-internet.herokuapp.com";
		String  responseBody  = RestAssured.given()
					.auth().basic("admin", "admin")
					.when()
						.get("/basic_auth")
							.then()	
								 .assertThat().statusCode(200)
								 	.and()
								 		.extract().body().asString();
		System.out.println(responseBody);
	}
	
	@Test
	public void preemitiveAuthTest() {
		RestAssured.baseURI ="https://the-internet.herokuapp.com";
		String  responseBody  = RestAssured.given()
					.auth().preemptive().basic("admin", "admin")
					.when()
						.get("/basic_auth")
							.then()	
								 .assertThat().statusCode(200)
								 	.and()
								 		.extract().body().asString();
		System.out.println(responseBody);
	}
	@Test
	public void digestiveAuthTest() {
		RestAssured.baseURI ="https://the-internet.herokuapp.com";
		String  responseBody  = RestAssured.given()
					.auth().digest("admin", "admin")
					.when()
						.get("/basic_auth")
							.then()	
								 .assertThat().statusCode(200)
								 	.and()
								 		.extract().body().asString();
		System.out.println(responseBody);
	}
	
	@Test
	public void apiKeyAuthTest() {
		RestAssured.baseURI ="http://api.weatherapi.com/";
		String  responseBody  = RestAssured.given()
					.queryParam("q", "London")
					.queryParam("aqi", "no")
					.queryParam("key", "5dc22ddde42f430ab8815832232306")
					.when()
						.get("/v1/current.json")
							.then()	
								 .assertThat().statusCode(200)
								 	.and()
								 		.extract().body().asString();
		System.out.println(responseBody);
		
	
	
						
	}
}
