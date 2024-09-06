package API_Testing;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;


public class TrelloAPI {
	
	String id ; 
	
	@Test(priority = 1)
	public void createWork() {
		RestAssured.baseURI="https://api.trello.com";
		Response bases = given().contentType(ContentType.JSON).queryParam("name", "Shubham")
		.queryParam("key", "c6e345a05be3daf7ea819befc30c193e")
		.queryParam("token", "ATTAd5680741b8671f9645e06738a439be0d78569b6a2544ac47f7da0bb17bc1c0f3A864E432")
		.when().post("/1/boards/").then().extract().response();
		
		String extracted = bases.asString();
		JsonPath jp = new JsonPath(extracted);
		id = jp.get("id");
		System.out.println(id);
	}
	
	@Test(priority = 2)
	public void getaBoard() {
		RestAssured.baseURI="https://api.trello.com";
		given().contentType(ContentType.JSON)
		.queryParam("key", "c6e345a05be3daf7ea819befc30c193e")
		.queryParam("token", "ATTAd5680741b8671f9645e06738a439be0d78569b6a2544ac47f7da0bb17bc1c0f3A864E432")
		.get("/1/boards/"+id).then().statusCode(200).log().all();
	}
	
	@Test(priority = 3)
	public void DeleteABoard() {
		RestAssured.baseURI="https://api.trello.com";
		given().contentType(ContentType.JSON).queryParam("key", "c6e345a05be3daf7ea819befc30c193e")
		.queryParam("token", "ATTAd5680741b8671f9645e06738a439be0d78569b6a2544ac47f7da0bb17bc1c0f3A864E432")
		.delete("/1/boards/"+id).then().statusCode(200).log().all();
		
	}

}
