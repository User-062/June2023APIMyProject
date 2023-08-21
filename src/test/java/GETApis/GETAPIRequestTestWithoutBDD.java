package GETApis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GETAPIRequestTestWithoutBDD {
	
	
	//NON BDD APPROACH:
	
	RequestSpecification request;
	
	@BeforeTest
	
	public void setup() {
		RestAssured.baseURI = "https://gorest.co.in";

		request = RestAssured.given();
		request.header("Authorization", "Bearer 9a8ff32407603ab822a750a16997a948c4803a41abed9856302151b934b6a965");
		
	}

	@Test

	public void getUserAPITest() {

		
		Response response = request.get("/public/v2/users/");

		// status code
		int statuCode = response.statusCode();
		System.out.println("status code :" + statuCode);

		// verification Point
		Assert.assertEquals(statuCode, 200);

		// status msg
		String statusMsg = response.statusLine();
		System.out.println("Status msg :" + statusMsg);

		// fetch the body:
		response.prettyPrint();

		// fetch the header:
		String contentType = response.header("Content-Type");
		System.out.println(contentType);

		// fetch all headers:
		List<Header> headersList = response.headers().asList();

		System.out.println(headersList.size());

		for (Header h : headersList) {
			System.out.println(h.getName() + ":" + h.getValue());
			
			

		}

	}
	
	@Test

	public void getAllUsersWithQueryParameter_APITest() {

		
		request.queryParam("name","sanjay");
		request.queryParam("status","active");
		
		
		Response response = request.get("/public/v2/users/");

		// status code
		int statuCode = response.statusCode();
		System.out.println("status code :" + statuCode);

		// verification Point
		Assert.assertEquals(statuCode, 200);

		// status msg
		String statusMsg = response.statusLine();
		System.out.println("Status msg :" + statusMsg);

		// fetch the body:
		response.prettyPrint();

		// fetch the header:
		String contentType = response.header("Content-Type");
		System.out.println(contentType);

		// fetch all headers:
		List<Header> headersList = response.headers().asList();

		System.out.println(headersList.size());

		for (Header h : headersList) {
			System.out.println(h.getName() + ":" + h.getValue());

		}

	}
	

	@Test

	public void getAllUsersWithQueryParameter_WithHashMap_APITest() {

		
		
		Map<String,String> queryParamsMap = new HashMap<String,String>();
		queryParamsMap.put("name","sanjay");
		queryParamsMap.put("gender","male");
		
		request.queryParams(queryParamsMap);
		
		
		Response response = request.get("/public/v2/users");

		// status code
		int statuCode = response.statusCode();
		System.out.println("status code :" + statuCode);

		// verification Point
		Assert.assertEquals(statuCode, 200);

		// status msg
		String statusMsg = response.statusLine();
		System.out.println("Status msg :" + statusMsg);

		// fetch the body:
		response.prettyPrint();

	}

}
