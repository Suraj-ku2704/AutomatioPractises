package frameworkPractice.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import frameworkPractice.AbtractComponents.AbstractComponents;

public class checkOutPage extends AbstractComponents {

	WebDriver driver;
	public checkOutPage(WebDriver driver) {
		super(driver);
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	By CVVfield = By.xpath("//div[text()='CVV Code ']/following-sibling::input");
	
	@FindBy(xpath="//div[text()='CVV Code ']/following-sibling::input")
	WebElement CVVCode;
	
	@FindBy(xpath="//div[text()='Name on Card ']/following-sibling::input")
	WebElement CardHolderName;
	
	public checkOutPage enterCardDetails(String cvv, String uName) {
				
		waitForElementToBeVisible(CVVfield);
		CVVCode.sendKeys(cvv);
		CardHolderName.sendKeys(uName);
		return this;
	}


	@FindBy(css="input[placeholder='Select Country']")
	WebElement selectCountry;
	
	@FindBy(xpath="//span[text()=' India']")
	List<WebElement> dropdownOptions;
	
	public checkOutPage selectCountry(String countrySearchText, String expectedCountry) {
		
		selectCountry.sendKeys(countrySearchText);
		
		
		for (WebElement suggestion : dropdownOptions) {
			if (suggestion.getText().equalsIgnoreCase(expectedCountry)) {
				suggestion.click();
				break;
			}
		}
		
		return this;
		
	}
	
	@FindBy(css="a.action__submit")
	WebElement  placeOrder;
	
	@FindBy(css="td h1")
	WebElement confirmMsgEle;
	
	public checkOutPage placeOrder() {
		
		
		placeOrder.click();
		String finalText = confirmMsgEle.getText();
		
		Assert.assertEquals("THANKYOU FOR THE ORDER.", finalText);
		
		return this;
	}

}
