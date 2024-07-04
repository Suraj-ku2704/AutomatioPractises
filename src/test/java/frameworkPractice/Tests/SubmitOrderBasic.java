package frameworkPractice.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


public class SubmitOrderBasic {

@Test
public void consumerFlow() {
	
	String userName = "surajunnikrishnan@gmail.com";
	String PassWord = "Test@123";
	//String item = "adidas original";
	
	String item = "adidas original";
	
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	driver.get("https://rahulshettyacademy.com/client");
	
	driver.findElement(By.id("userEmail")).sendKeys(userName);
	driver.findElement(By.id("userPassword")).sendKeys(PassWord);
	driver.findElement(By.xpath("//input[contains(@class,'btn-block login-btn')]")).click();
	
	List<WebElement> products =driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream().filter(Product-> 
		Product.findElement(By.cssSelector("b")).getText().equals(item)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();


	
	WebElement toaster = driver.findElement(By.cssSelector("#toast-container"));
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(toaster));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
	driver.findElement(By.cssSelector(".btn.btn-custom[routerlink='/dashboard/cart']")).click();
}
}
