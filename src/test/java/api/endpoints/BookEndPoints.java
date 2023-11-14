package api.endpoints;
import io.restassured.http.ContentType;
import api.payload.Book;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BookEndPoints {
	
	public static Response createBookUser(Book payload)
    {
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonPayload;
		
		try {
	        jsonPayload = objectMapper.writeValueAsString(payload);
	    } catch (Exception e) {
	        // Handle the exception, e.g., log it or throw it
	        throw new RuntimeException("Failed to serialize the object to JSON.", e);
	    }
		
        Response response = given()
                .contentType(ContentType.JSON)
                .auth().basic("user", "password")
                .accept(ContentType.JSON)
                .body(jsonPayload)
                .when()
                .post(Routes.post_url);

        return response;
    }
	
	public static Response createBookAdmin(Book payload)
    {
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonPayload;
		
		try {
	        jsonPayload = objectMapper.writeValueAsString(payload);
	    } catch (Exception e) {
	        // Handle the exception, e.g., log it or throw it
	        throw new RuntimeException("Failed to serialize the object to JSON.", e);
	    }
	
        Response response = given()
                .contentType(ContentType.JSON)
                .auth().basic("admin", "password")
                .accept(ContentType.JSON)
                .body(jsonPayload)
                .when()
                .post(Routes.post_url);

        return response;
    }
	
	public static Response createBook(int author, String title)
    {
        String requestBody = String.format("{ \"author\": %d, \"title\": \"%s\" }", author, title);

        Response response = given()
                .contentType(ContentType.JSON)
                .auth().basic("admin", "password")
                .accept(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(Routes.post_url);

        return response;
    }
	
	public static Response createBook(String author, int title)
    {
        String requestBody = String.format("{ \"author\": %s, \"title\": \"%d\" }", author, title);

        Response response = given()
                .contentType(ContentType.JSON)
                .auth().basic("admin", "password")
                .accept(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(Routes.post_url);

        return response;
    }

}
