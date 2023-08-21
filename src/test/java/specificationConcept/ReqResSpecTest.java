package specificationConcept;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class ReqResSpecTest {

	public static RequestSpecification user_req_spec() {
		RequestSpecification requestSpec = new RequestSpecBuilder()
				.setBaseUri("https://gorest.co.in")	
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", "Bearer 9a8ff32407603ab822a750a16997a948c4803a41abed9856302151b934b6a965")
				.build();
		
		return requestSpec;
		
	}
	
	 public static ResponseSpecification get_res_spec_200_OK_With_Body() {
    	 ResponseSpecification res_spec_200_ok =  new ResponseSpecBuilder()
    	   		.expectContentType(ContentType.JSON)
    	   		.expectStatusCode(200)
    	   		.expectHeader("Server", "cloudflare")
    	   		.expectBody("$.size()", equalTo(10))
    	   		.expectBody("id", hasSize(10))
    	   		.build();
    	 
    	 return res_spec_200_ok;
     }
	 
	 @Test
	 public void getUser_With_Req_Res_Spec_Test() {
		 given().log().all()
		 	.spec(user_req_spec())
		 	.get("/public/v2/users/")
		 		.then().log().all()
		 			.assertThat()
		 				.spec(get_res_spec_200_OK_With_Body());
		 	
	 }
	
	
	
}
