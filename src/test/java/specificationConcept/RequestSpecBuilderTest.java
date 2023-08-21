package specificationConcept;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecBuilderTest {
	
	
	public static RequestSpecification user_req_spec() {
		RequestSpecification requestSpec = new RequestSpecBuilder()
				.setBaseUri("https://gorest.co.in")	
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", "Bearer 9a8ff32407603ab822a750a16997a948c4803a41abed9856302151b934b6a965")
				.build();
		
		return requestSpec;
		
	}

	@Test
	
	public void getUser_With_Request_Spec() {
		
		
		
		RestAssured.given().log().all()
			.spec(user_req_spec())
					.get("/public/v2/users/")
						.then()
							.statusCode(200);
	}
	
	@Test
	
	public void getUser_With_Param_Request_Spec() {
		RestAssured.given().log().all()
			.queryParam("name","sanjay")
			.queryParam("status", "active")
			.spec(user_req_spec())
					.get("/public/v2/users/")
						.then().log().all()
							.statusCode(200);
	}	
	
	
}
