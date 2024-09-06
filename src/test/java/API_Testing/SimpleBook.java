package API_Testing;



import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class SimpleBook {
	
	@Test
	public void status() {
		given().get("https://simple-books-api.glitch.me/status").then().statusCode(200).log().all();
	}
	
	@Test
	public void ListofBooks() {
		given().get("https://simple-books-api.glitch.me/books").then().statusCode(200).log().all();
	}
	
	@Test
	public void GetASingleBook() {
		given().get("https://simple-books-api.glitch.me/books/4").then().statusCode(200).log().all();
		
	}
	
	@Test
	public void APIAuthentication() {
		JSONObject js = new JSONObject();
		js.put("clientName", "Shubham");
		js.put("clientEmail", "shubhamsdf@example.com");
		given().contentType("application/json").body(js.toJSONString()).when().post("https://simple-books-api.glitch.me/api-clients/").then().statusCode(201).log().all();
	}
	
	@Test
	public void SubmitAnOrder() {
		RestAssured.baseURI="https://simple-books-api.glitch.me";
		JSONObject js = new JSONObject();
		js.put("bookId", 1);
		js.put("customerName", "Shubh");
		given().header("Authorization","Bearer "+ "d1f0814723c71108dcab3623e68efc0a01c155ad2dbcdfbdcd6913b29965d301")
		.contentType(ContentType.JSON).body(js.toJSONString()).when().post("/orders").then().statusCode(201).log().all();
		
	}
	
	@Test
	public void GetAllOrders() {
		given().header("Authorization","Bearer "+"d1f0814723c71108dcab3623e68efc0a01c155ad2dbcdfbdcd6913b29965d301")
		.get("https://simple-books-api.glitch.me/orders").then().statusCode(200).log().all();
		
	}
	
	@Test
	public void GetAnOrder() {
	
		given().header("Authorization","Bearer "+"d1f0814723c71108dcab3623e68efc0a01c155ad2dbcdfbdcd6913b29965d301")
		.get("https://simple-books-api.glitch.me/orders/7AZ4DGJzKb3fpXg-UftzA").then().statusCode(200).log().all();
		
		
	}
	
	@Test
	public void UpdateAnOrder() {
		RestAssured.baseURI="https://simple-books-api.glitch.me";
		JSONObject js = new JSONObject();
		js.put("customerName", "Shubham Shrivastava");
		given().header("Authorization","Bearer "+"d1f0814723c71108dcab3623e68efc0a01c155ad2dbcdfbdcd6913b29965d301")
		.contentType(ContentType.JSON).body(js.toJSONString())
		.when().patch("/orders/7AZ4DGJzKb3fpXg-UftzA").then().statusCode(204).log().all();
		
	}

	@Test
	public void DeleteAnOrder() {
		given().header("Authorization","Bearer "+"d1f0814723c71108dcab3623e68efc0a01c155ad2dbcdfbdcd6913b29965d301")
		.delete("https://simple-books-api.glitch.me/orders/7AZ4DGJzKb3fpXg-UftzA").then().statusCode(200).log().all();
		
	}

}
