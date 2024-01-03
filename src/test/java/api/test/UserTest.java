package api.test;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;


import static org.hamcrest.Matchers.*;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.Userendpoints;
import api.endpoints.Userendpoints;
import api.payload.User;
import groovyjarjarantlr4.v4.runtime.misc.LogManager;

public class UserTest {
    Faker faker;
    User userpayload;
    
    public Logger logger;
	
    @BeforeClass
	public void setupData() {
		faker = new Faker();
		userpayload = new User();
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5,10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		
		
		logger = org.apache.logging.log4j.LogManager.getLogger(this.getClass());
	
	}
    @Test(priority = 1)
    public void test_PostUser() {
    	logger.info("***********************creating user********************");
    	Response response = Userendpoints.createUser(userpayload);
//    	response.then().log().all();
//    	System.out.println(response.asString());
    	Assert.assertEquals(response.getStatusCode(), 200);
    	logger.info("***********************user is created********************");
    }
    
    @Test(priority = 2)
    public void test_GettUser() {
    	logger.info("***********************reading user info********************");
    	Response response = Userendpoints.readUser(this.userpayload.getUsername());
    	response.then().log().all();
//    	System.out.println(response.asString());
    	Assert.assertEquals(response.getStatusCode(), 200);
    	logger.info("***********************user info is displayed********************");
    }
    
    @Test(priority = 3)
    public void test_UpdateUser() {
    	logger.info("***********************updating user info********************");
    	userpayload.setFirstName(faker.name().firstName());
    	userpayload.setLastName(faker.name().lastName());
    	userpayload.setEmail(faker.internet().safeEmailAddress());
    	
    	Response response = Userendpoints.updateUser(this.userpayload.getUsername(),userpayload);
    	response.then().log().body().statusCode(200);
    	
    	//checking data after updation
    	Response responseafterupdate = Userendpoints.readUser(this.userpayload.getUsername());
    	responseafterupdate.then().log().all();
//    	System.out.println(response.asString());
    	Assert.assertEquals(responseafterupdate.getStatusCode(), 200);
    	logger.info("***********************user info is updated********************");

    }
    @Test(priority = 4)
    public void delete_User() {
    	logger.info("***********************deleting user info********************");
    	Response resp = Userendpoints.deleteUser(this.userpayload.getUsername());
    	resp.then().log().body().statusCode(200);
    	logger.info("***********************user info is deleted********************");
    }
}
