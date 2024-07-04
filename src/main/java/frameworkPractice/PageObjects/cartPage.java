package frameworkPractice.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworkPractice.AbtractComponents.AbstractComponents;

public class cartPage extends AbstractComponents {
	
	WebDriver driver;
	
	public cartPage(WebDriver driver) {
		super(driver);
	    this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> elements;

	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement checkOut;

	public cartPage checkCartItems() {

		for (WebElement element : elements) {
			System.out.println(element.getText());
		}
		
		
		return this;

	}

	public checkOutPage checkOut() {
		checkOut.click();
		

		return new checkOutPage(driver);
	}
	
	public cartPage continueShopping() {
		return null;
	}
	
	public cartPage buyNow() {
		return null;
		
	}
	
	public cartPage deleteItem() {
		return null;
		
	}


}
