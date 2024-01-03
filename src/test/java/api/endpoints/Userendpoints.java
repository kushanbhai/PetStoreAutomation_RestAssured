package api.endpoints;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import api.payload.User;
//performs crud operations
public class Userendpoints {

	
	public static Response createUser(User payload) {
		
		Response resp =   given()
		  	.contentType("application/json")
		  	.accept("application/json")
		  	.body(payload)
		  .when()
		  .post(Routes.post_url);
		
		return resp;
	}
	
public static Response readUser(String username) {
		
		Response resp =   given()
		  	.contentType(ContentType.JSON)
		  	.accept(ContentType.JSON)
		  	.pathParam("username", username)
		  .when()
		  .get(Routes.get_url);
		
		return resp;
	}


public static Response updateUser(String username,User payload) {
	
	Response resp =   given()
	  	.contentType(ContentType.JSON)
	  	.accept(ContentType.JSON)
	  	.pathParam("username", username)
	  	.body(payload)
	  .when()
	  .put(Routes.update_url);
	
	return resp;
}

public static Response deleteUser(String username) {
	
	Response resp =   given()
	  	.contentType(ContentType.JSON)
	  	.accept(ContentType.JSON)
	  	.pathParam("username", username)
	  .when()
	  .delete(Routes.delete_url);
	
	return resp;
}
}
