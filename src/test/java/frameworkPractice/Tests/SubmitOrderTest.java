package frameworkPractice.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.Test;

import frameworkPractice.PageObjects.ProductCatalogue;
import frameworkPractice.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {

	@Test(dataProvider = "getData")
	public void submitOrder(HashMap<String, String> input, HashMap<String, List<String>> itemList)
			throws IOException, InterruptedException {

		ProductCatalogue productCatalogue = landingPage.Login(input.get("userName"), input.get("PassWord"));
		landingPage.LoginSuccessMsg();
		productCatalogue.AddProductToCart(itemList.get("item")).goToCart().checkCartItems().checkOut()
				.enterCardDetails(input.get("cvvNum"), input.get("cardUserName"))
				.selectCountry(input.get("countrySearchText"), input.get("expectedCountry")).placeOrder();

		
	}

	
}
