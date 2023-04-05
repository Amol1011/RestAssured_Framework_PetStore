package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints2 {

	static ResourceBundle getURL()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes");
		return routes;
	}
	public static Response createUser(User payload)
	{
		String url=getURL().getString("post_url1");
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(url);
		return response;
	}
	
	public static Response readUser(String userName) 
	{
		String url=getURL().getString("read_url1");
		Response response=given()
		.pathParams("username", userName)
		.when()
		.get(url);
		return response;
	}
	
	public static Response updateUser(String userName,User payload)
	{
		String url=getURL().getString("update_url1");
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.pathParam("username",userName)
		.body(payload)
		.when()
		.put(url);
		return response;
	}
	
	public static Response deleteUser(String userName) 
	{
		String url=getURL().getString("delete_url1");
		Response response=given()
		.pathParam("username", userName)
		.when()
		.delete(url);
		return response;
	}
}
