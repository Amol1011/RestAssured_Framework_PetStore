package api.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.javafaker.Faker;

import api.endpoints.*;
import api.payloads.User;
import io.restassured.response.Response;
public class UserTests_Logger {
Faker faker;
User payload;
public Logger logger;
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
		logger=LogManager.getLogger(this.getClass());
	}

	@Test(priority = 1)
	public void testPostUser()
	{
		logger.info("=====Starting testPostUser========");
		//System.out.println("=====Starting testPostUser========");
		Response response=UserEndPoints2.createUser(payload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		//System.out.println("=====Ending testPostUser========");
		logger.info("=====Ending testPostUser========");
	}
	
	@Test(priority = 2)
	public void testGetUser()
	{
		logger.info("=====Starting testGetUser========");
		//System.out.println("=====Starting testGetUser========");
		Response response=UserEndPoints2.readUser(this.payload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.statusCode(),200);
		//System.out.println("=====Ending testGetUser========");
		logger.info("=====Ending testGetUser========");
	}
	
	
	@Test(priority = 3)
	public void testUpdateUser()
	{
		logger.info("=====Starting testUpdateUser========");
		//System.out.println("=====Starting testUpdateUser========");
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setEmail(faker.internet().safeEmailAddress());
		Response response=UserEndPoints2.updateUser(payload.getUsername(), payload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		this.testGetUser();
		//System.out.println("=====Ending testUpdateUser========");
		logger.info("=====Ending testUpdateUser========");
	}
	
	@Test(priority = 4)
	public void testDeleteUser()
	{
		logger.info("=====Starting testDeleteUser========");
		//System.out.println("=====Starting testDeleteUser========");
		Response response=UserEndPoints2.deleteUser(payload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		//System.out.println("=====Ending testDeleteUser========");
		logger.info("=====Ending testDeleteUser========");
	}
}
