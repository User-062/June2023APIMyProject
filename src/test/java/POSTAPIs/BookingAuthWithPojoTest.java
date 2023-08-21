package POSTAPIs;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.Credentials;

public class BookingAuthWithPojoTest {
	
	//POJO - Plain Old Java Object
	//Can not extend any other class
	//oop encapsulation - data hiding
	//private class vars --json body
	//public getter/setter
	
	
	//serialization --->Java object to other format:file, media, json/xml/text/pdf
	//pojo to json -serialization
	//json to pojo -Deserialization
	
	
	
	public class BookingAuthTest {

		@Test
		public void getBookingAuthTokenTest_WithJSON_String() {
			
			RestAssured.baseURI = "https://restful-booker.herokuapp.com";
			
			Credentials creds = new Credentials("admin","password123");
			
			String tokenId = given().log().all()
				.contentType(ContentType.JSON)
				.body(creds)
				.when().log().all()
					.post("/auth")
						.then()
							.assertThat()
								.statusCode(200)
									.extract()
										.path("token");
			
			
			System.out.println(tokenId);
			Assert.assertNotNull(tokenId);
			
		}
	}

}
