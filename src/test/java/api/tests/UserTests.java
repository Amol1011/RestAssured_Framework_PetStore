package api.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import com.github.javafaker.Faker;

import api.endpoints.*;
import api.payloads.User;
import io.restassured.response.Response;
public class UserTests {
Faker faker;
User payload;
	@BeforeClass
	public void setup()
	{
		faker=new Faker();
		payload=new User();
		payload.setId(faker.number().numberBetween(10000,99999));
		System.out.println(payload.getId());
		payload.setUsername(faker.name().username());
		System.out.println(payload.getUsername());
		payload.setFirstName(faker.name().firstName());
		System.out.println(payload.getFirstName());
		payload.setLastName(faker.name().lastName());
		System.out.println(payload.getLastName());
		payload.setEmail(faker.internet().safeEmailAddress());
		System.out.println(payload.getEmail());
		payload.setPassword(faker.internet().password(5,10));
		System.out.println(payload.getPassword());
		payload.setPhone(faker.phoneNumber().cellPhone());
		System.out.println(payload.getPhone());
		System.out.println(payload.getUserStatus());
	}

	@Test(priority = 1)
	public void testPostUser()
	{
		System.out.println("=====Starting testPostUser========");
		Response response=UserEndPoints.createUser(payload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		System.out.println("=====Ending testPostUser========");
	}
	
	@Test(priority = 2)
	public void testGetUser()
	{
		System.out.println("=====Starting testGetUser========");
		Response response=UserEndPoints.readUser(this.payload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.statusCode(),200);
		System.out.println("=====Ending testGetUser========");
	}
	
	
	@Test(priority = 3)
	public void testUpdateUser()
	{
		System.out.println("=====Starting testUpdateUser========");
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setEmail(faker.internet().safeEmailAddress());
		Response response=UserEndPoints.updateUser(payload.getUsername(), payload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		this.testGetUser();
		System.out.println("=====Ending testUpdateUser========");
	}
	
	@Test(priority = 4)
	public void testDeleteUser()
	{
		System.out.println("=====Starting testDeleteUser========");
		Response response=UserEndPoints.deleteUser(payload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		System.out.println("=====Ending testDeleteUser========");
	}
}
