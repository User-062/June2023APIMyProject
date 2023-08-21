package XMLAPIs;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class GetCircuitXMLAPITest {
	
	@Test
	
	public void xmlTest() {
		RestAssured.baseURI ="https://ergast.com/";
		
		
		       Response response = RestAssured.given()
		    		   				.when()
		    		   					.get("/api/f1/2017/circuits.xml")
		    		   						.then()
		    		   						.extract().response();
		       
		       
		String responseBody = response.body().asString();
		System.out.println(responseBody);
		
		//create object of 

		XmlPath xmlPath = new XmlPath(responseBody);
		
		List<String> circuitNames=xmlPath.getList("MRData.CircuitTable.Circuit.CircuitName");
		for(String e : circuitNames) {
			System.out.println(e);
		}
		
		System.out.println("--------------------------------");
		 
		List<String> circuitIds = xmlPath.getList("MRData.CircuitTable.Circuit.@circuitId");
		for(String e : circuitIds) {
			System.out.println(e);
		}
		System.out.println("--------------------------------");
		
		//fetch the locality where circuitId = americas
		String locality=xmlPath.get("**.findAll { it.@circuitId == 'americas' }.Location.Locality").toString();
		System.out.println(locality);
		
		System.out.println("--------------------------------");
		
		String latVal=xmlPath.get("**.findAll { it.@circuitId == 'americas' }.Location.@lat").toString();
		
		String longVal =xmlPath.get("**.findAll { it.@circuitId == 'americas' }.Location.@long").toString();
		
		System.out.println(latVal);
		
		System.out.println(longVal);
		
		System.out.println("--------------------------------");
		
		//Fetch locality where circuitId = americas circuitId = bahrain
		
		String data = xmlPath.get("**.findAll { it.@circuitId == 'americas' || it.@circuitId == 'bahrain' }.Location.Locality").toString();
		
		System.out.println(data);
		
		System.out.println("--------------------------------");
		
		String circuitName=xmlPath.get("**.findAll { it.@circuitId == 'americas' }.CircuitName").toString();
		System.out.println(circuitName);
		
		System.out.println("--------------------------------");
		
		String url=xmlPath.get("**.findAll { it.@circuitId == 'americas' }.@url").toString();
		System.out.println(url);
		
		System.out.println("--------------------------------");
		
	}

}
