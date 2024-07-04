package frameworkPractice.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import frameworkPractice.AbtractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;
	String sucessText;

	public LandingPage(WebDriver driver) {

		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	public LandingPage goTo() {

		driver.get("https://rahulshettyacademy.com/client");
		return this;
	}

	@FindBy(id = "userEmail")
	WebElement uName;

	@FindBy(id = "userPassword")
	WebElement pWord;

	@FindBy(xpath = "//input[contains(@class,'btn-block login-btn')]")
	WebElement loginButton;

	By successToaster = By.xpath("//div[contains(text(),'Login Successfully')]");

	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

	By genericErrorToaster = By.xpath("//div[contains(@class,'toast-message')]");

	public ProductCatalogue Login(String userName, String passWord) throws InterruptedException {

		uName.sendKeys(userName);
		pWord.sendKeys(passWord);
		loginButton.click();

		return new ProductCatalogue(driver);

	}
	
	public LandingPage LoginSuccessMsg() {

		WebElement successElement = waitForElementToBeVisible(successToaster);
		sucessText = successElement.getText();
		System.out.println(sucessText);

		return this;

	}

	public String LoginFailMsg() {

		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}




}
