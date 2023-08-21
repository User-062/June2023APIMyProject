package specificationConcept;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;


public class ResponseSpecBuilderTest {
	
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
       
       
       public static ResponseSpecification get_res_spec_401_Auth_Fail() {
      	 ResponseSpecification res_spec_401_AUTH_FAIL =  new ResponseSpecBuilder()
      	   		.expectStatusCode(401)
      	   		.expectHeader("Server", "cloudflare")
      	   		.build();
      	 
      	 return res_spec_401_AUTH_FAIL;
         }
       
       
       @Test
       public void get_user_res_200_spec_Test() {
    	   RestAssured.baseURI = "https://gorest.co.in";
    	   		given()
    	   			.header("Authorization", "Bearer 9a8ff32407603ab822a750a16997a948c4803a41abed9856302151b934b6a965")
    	   				.when()
    	   					.get("/public/v2/users/")
    	   						.then()
    	   							.assertThat()
    	   								.spec(get_res_spec_200_OK_With_Body());   				   			
    	   
       }
       
       @Test
       public void get_user_res_401_spec_Test() {
    	   RestAssured.baseURI = "https://gorest.co.in";
    	   		given()
    	   			.header("Authorization", "Bearer 9a8ff32407603ab822a750a16997a948c4803a41abed9856302151b934b6a9651111")
    	   				.when()
    	   					.get("/public/v2/users/")
    	   						.then()
    	   							.assertThat()
    	   								.spec(get_res_spec_401_Auth_Fail());   			
}
       
}
