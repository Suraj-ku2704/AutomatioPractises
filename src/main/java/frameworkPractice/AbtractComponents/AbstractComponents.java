package frameworkPractice.AbtractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import frameworkPractice.PageObjects.cartPage;

public class AbstractComponents {
	
	WebDriver driver;
	WebDriverWait wait;
	public AbstractComponents(WebDriver driver) {
	
		 this.driver = driver;
		 this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 
	}
	
	
	
	public void waitForWebElementToAppear(WebElement findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));

	}
	
	
	public WebElement waitForElementToBeVisible(By findBy) {
		
		return wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		
	}

	public void waitForElementToBeInVisible(By findBy) {
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
		
	}
	
	public void waitForElementToDisappear(By spinner) throws InterruptedException
	{
		Thread.sleep(1000);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
//		wait.until(ExpectedConditions.invisibilityOf(ele));

	}
	
	public boolean isElementVisible(By successToaster2) {
	    try {
	        return ((WebElement) successToaster2).isDisplayed();
	    } catch (Exception e) {
	        return false;
	    }
	}
	
	By cart = By.cssSelector(".btn.btn-custom[routerlink='/dashboard/cart']");
	
	public cartPage goToCart() {
		
		driver.findElement(cart).click();
		return new cartPage(driver);
	}

	


}
