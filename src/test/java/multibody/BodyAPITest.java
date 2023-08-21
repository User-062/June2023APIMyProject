package multibody;

import java.io.File;

import org.mozilla.javascript.ast.NewExpression;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BodyAPITest {

	@Test
	public void bodyWithTextTest() {
		RestAssured.baseURI="http://httpbin.org";
		String payload = "Hi,This is Sanjay Kumar";
		
Response response = RestAssured.given()
			.contentType(ContentType.TEXT)
			.body(payload)
			.when()
			.post("/post");
		
	response.prettyPrint();
	System.out.println(response.statusCode());
	}
	
	@Test
	public void bodyWithJavaScriptTest() {
		RestAssured.baseURI="http://httpbin.org";
		String payload = "function login(){\n"
				+ "    int x=10;\n"
				+ "    int y= 12;\n"
				+ "    console.log(x+y);\n"
				+ "}";
		
Response response = RestAssured.given()
			.header("Content-Type","application/javascript")
			.body(payload)
			.when()
			.post("/post");
		
	response.prettyPrint();
	System.out.println(response.statusCode());
	}
	
	
	@Test
	public void bodyWithHTMlTest() {
		RestAssured.baseURI="http://httpbin.org";
		String payload = "<!DOCTYPE html>\n"
				+ "<html dir=\"ltr\" lang=\"en\">\n"
				+ "<head>\n"
				+ "<meta charset=\"UTF-8\" />\n"
				+ "</head>\n"
				+ "</html>";
		
Response response = RestAssured.given()
			.contentType(ContentType.HTML)
			.body(payload)
			.when()
			.post("/post");
		
	response.prettyPrint();
	System.out.println(response.statusCode());
	}
	
	@Test
	public void bodyWithXMlTest() {
		RestAssured.baseURI="http://httpbin.org";
		String payload = "<note>  \n"
				+ "    <to>Tove</to>\n"
				+ "    <from>Jani</from>\n"
				+ "    <heading>Reminder</heading>\n"
				+ "    <body>Don't forget me this weekend!</body>\n"
				+ "</note>";
		
Response response = RestAssured.given()
			.contentType(ContentType.XML)
			.body(payload)
			.when()
			.post("/post");
		
	response.prettyPrint();
	System.out.println(response.statusCode());
	}
	
	@Test
	public void bodyWithMultiPartTest() {
		RestAssured.baseURI="http://httpbin.org";
	
Response response = RestAssured.given()
			.contentType(ContentType.MULTIPART)
			.multiPart("name","testing")
			.multiPart("fileName", new File("/Users/mac/Downloads/docusign-title.pdf"))
			.when()
			.post("/post");
		
	response.prettyPrint();
	System.out.println(response.statusCode());
}
	
	@Test
	public void bodyWithBinaryFileTest() {
		RestAssured.baseURI="http://httpbin.org";
	
Response response = RestAssured.given()
			.header("Content-Type","application/pdf")
			.body(new File("/Users/mac/Downloads/docusign-title.pdf"))
			.when()
			.post("/post");
		
	response.prettyPrint();
	System.out.println(response.statusCode());
}
}