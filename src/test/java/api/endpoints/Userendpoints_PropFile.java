package api.endpoints;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
//performs crud operations
public class Userendpoints_PropFile {

	static ResourceBundle getURL(){
		ResourceBundle routes = ResourceBundle.getBundle("routes");//load properties file
		return routes;
	}
	public static Response createUser(User payload) {
		String post_url = getURL().getString("post_url");
		Response resp =   given()
		  	.contentType("application/json")
		  	.accept("application/json")
		  	.body(payload)
		  .when()
		  .post(post_url);
		
		return resp;
	}
	
public static Response readUser(String username) {
		String get_url = getURL().getString("get_url");
		Response resp =   given()
		  	.contentType(ContentType.JSON)
		  	.accept(ContentType.JSON)
		  	.pathParam("username", username)
		  .when()
		  .get(get_url);
		
		return resp;
	}


public static Response updateUser(String username,User payload) {
	String update_url = getURL().getString("update_url");
	Response resp =   given()
	  	.contentType(ContentType.JSON)
	  	.accept(ContentType.JSON)
	  	.pathParam("username", username)
	  	.body(payload)
	  .when()
	  .put(update_url);
	
	return resp;
}

public static Response deleteUser(String username) {
	String delete_url = getURL().getString("delete_url");
	Response resp =   given()
	  	.contentType(ContentType.JSON)
	  	.accept(ContentType.JSON)
	  	.pathParam("username", username)
	  .when()
	  .delete(delete_url);
	
	return resp;
}
}
