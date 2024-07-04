package frameworkPractice.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import frameworkPractice.AbtractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {

	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	
	By ProductsList = By.cssSelector(".mb-3");
	By filteredProduct = By.cssSelector(".card-body button:last-of-type");
	By toaster = By.cssSelector("#toast-container");
	By spinner = By.cssSelector(".ng-animating");
	
	public List<WebElement> getProductCatalogue() {
		
		waitForElementToBeVisible(ProductsList);
		return products;		
	}
	
	public cartPage AddProductToCart(List<String> itemList) {
		
		getProductCatalogue().stream().filter(product -> {
			String productName = product.findElement(By.cssSelector("b")).getText();
			
			return itemList.contains(productName);
		}).forEach(product -> {
			
			product.findElement(filteredProduct).click();
			
			waitForElementToBeVisible(toaster);
			try {
				waitForElementToDisappear(spinner);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		return new cartPage(driver);

}
}
