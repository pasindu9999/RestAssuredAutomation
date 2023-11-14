package tests;

import org.json.simple.JSONObject;
import org.testng.Assert;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Test1 {
	
	@Test
	public void test_1() {
		
		Response response = given()
	            .auth().basic("admin", "password").get("http://localhost:7081/api/books");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.getTime());
		System.out.println(response.getBody().asString());
	//	System.out.println(response.getStatusLine());	
		
		int statusCode = response.getStatusCode();
		
		Assert.assertEquals(statusCode, 201);
	}
	
	@Test
	public void test_2() {
		
		baseURI = "http://localhost:7081/api";
		given()
        .auth().basic("admin", "password").get("/books");
	}
	
	@Test
	public void test_create_book_as_admin() {
		
		baseURI = "http://localhost:7081/api";
		
	    
	    JSONObject request = new JSONObject();
	    request.put("title", "Book 2"); 
	    request.put("author", "Person 2");
	    
	    System.out.println(request.toJSONString());
	    
	    given().
	    	auth().basic("admin", "password").
	    	contentType(ContentType.JSON).
	    	accept(ContentType.JSON).
	    	body(request.toJSONString()).
	    when().
	    	post("/books").
	    then().
	    	statusCode(201).
	    	log().all(); 
	    
		/*
		 * int statusCode = response.getStatusCode();
		 * 
		 * Assert.assertEquals(statusCode, 201);
		 */
	}
	
	@Test
	public void test_create_book_as_user() {
		
		baseURI = "http://localhost:7081/api";
		
	    
	    JSONObject request = new JSONObject();
	    request.put("title", "Book 3"); 
	    request.put("author", "Person 3");
	    
	    System.out.println(request.toJSONString());
	    
	    given().
	    	auth().basic("user", "password").
	    	contentType(ContentType.JSON).
	    	accept(ContentType.JSON).
	    	body(request.toJSONString()).
	    when().
	    	post("/books").
	    then().
	    	statusCode(201).
	    	log().all(); 	   
	}
	
	@Test
	public void test_create_book_with_empty_title_as_user() {
		
		baseURI = "http://localhost:7081/api";
		
	    
	    JSONObject request = new JSONObject();
	    request.put("author", "Person 2");
	    
	    System.out.println(request.toJSONString());
	    
	    given().
	    	auth().basic("user", "password").
	    	contentType(ContentType.JSON).
	    	accept(ContentType.JSON).
	    	body(request.toJSONString()).
	    when().
	    	post("/books").
	    then().
	    	statusCode(201).
	    	log().all(); 	   
	}
	
	@Test
	public void test_create_book_with_empty_title_as_admin() {
		
		baseURI = "http://localhost:7081/api";
		
	    
	    JSONObject request = new JSONObject();
	    request.put("author", "Person 2");
	    
	    System.out.println(request.toJSONString());
	    
	    given().
	    	auth().basic("admin", "password").
	    	contentType(ContentType.JSON).
	    	accept(ContentType.JSON).
	    	body(request.toJSONString()).
	    when().
	    	post("/books").
	    then().
	    	statusCode(201).
	    	log().all(); 	   
	}
	
	@Test
	public void test_create_book_with_empty_author_as_user() {
		
		baseURI = "http://localhost:7081/api";
		
	    
	    JSONObject request = new JSONObject();
	    request.put("title", "book 2");
	    
	    System.out.println(request.toJSONString());
	    
	    given().
	    	auth().basic("user", "password").
	    	contentType(ContentType.JSON).
	    	accept(ContentType.JSON).
	    	body(request.toJSONString()).
	    when().
	    	post("/books").
	    then().
	    	statusCode(201).
	    	log().all(); 	   
	}
	
	@Test
	public void test_create_book_with_empty_author_as_admin() {
		
		baseURI = "http://localhost:7081/api";
		
	    
	    JSONObject request = new JSONObject();
	    request.put("title", "book 3");
	    
	    System.out.println(request.toJSONString());
	    
	    given().
	    	auth().basic("admin", "password").
	    	contentType(ContentType.JSON).
	    	accept(ContentType.JSON).
	    	body(request.toJSONString()).
	    when().
	    	post("/books").
	    then().
	    	statusCode(201).
	    	log().all(); 	   
	}
	
	@Test
	public void test_create_book_with_negative_id() {
		
		baseURI = "http://localhost:7081/api";
		
	    
	    JSONObject request = new JSONObject();
	    request.put("id", 2);
	    request.put("title", "book 6"); 
	    request.put("author", "Person 3");
	    
	    System.out.println(request.toJSONString());
	    
	    given().
	    	auth().basic("user", "password").
	    	contentType(ContentType.JSON).
	    	accept(ContentType.JSON).
	    	body(request.toJSONString()).
	    when().
	    	post("/books").
	    then().
	    	statusCode(201).
	    	log().all(); 	   
	}
	
	@Test (priority = 2)
	public void test_create_book_with_duplicate_id_as_user() {
		
		baseURI = "http://localhost:7081/api";
		
	    
	    JSONObject request = new JSONObject();
	    request.put("id", "1");
	    request.put("title", "Book 8"); 
	    request.put("author", "Person 3");
	    
	    
	    System.out.println(request.toJSONString());
	    
	    given().
	    	auth().basic("user", "password").
	    	contentType(ContentType.JSON).
	    	accept(ContentType.JSON).
	    	body(request.toJSONString()).
	    when().
	    	post("/books").
	    then().
	    	statusCode(201).
	    	log().all(); 	   
	}
}
