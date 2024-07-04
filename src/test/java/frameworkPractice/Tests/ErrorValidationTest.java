package frameworkPractice.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import frameworkPractice.TestComponents.BaseTest;

public class ErrorValidationTest extends BaseTest {

	@Test(dataProvider = "getData")
	public void loginErrorValidation(HashMap<String, String> input, HashMap<Object, List<String>> itemList) throws IOException, InterruptedException {

		landingPage.Login(input.get("userName"), input.get("incorrectPassWord"));
		Assert.assertEquals("Incorrect email ors password.",landingPage.LoginFailMsg());
	
		
		
	}
}
