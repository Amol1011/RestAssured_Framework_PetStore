package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class Data_Driven_Tests {
	@Test(priority = 1,dataProvider="Data",dataProviderClass=DataProviders.class)
	 void testPostUser(String userID,String userName,String fname,String lname,String email,String password,String phone)
	{
		User payload=new User();
		payload.setId(Integer.parseInt(userID));
		payload.setUsername(userName);
		payload.setFirstName(fname);
		payload.setLastName(lname);
		payload.setEmail(email);
		payload.setPassword(password);
		payload.setPhone(phone);
		Response response=UserEndPoints.createUser(payload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	@Test(priority = 2,dataProvider="UserNames",dataProviderClass=DataProviders.class)
	void testDeleteUser(String UserName)
	{
		Response response=UserEndPoints.deleteUser(UserName);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(),200);
	}
}
