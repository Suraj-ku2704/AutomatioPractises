package frameworkPractice.Tests;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class SubmitOrderReference {

	@Test
	public void consumerFlow() throws InterruptedException {

		String userName = "surajunnikrishnan@gmail.com";
		String PassWord = "Test@123";

		String[] itemArray = { "ADIDAS ORIGINAL", "IPHONE 13 PRO" };
		List<String> itemList = Arrays.asList(itemArray);

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");

		driver.findElement(By.id("userEmail")).sendKeys(userName);
		driver.findElement(By.id("userPassword")).sendKeys(PassWord);
		driver.findElement(By.xpath("//input[contains(@class,'btn-block login-btn')]")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		wait.until(ExpectedConditions.visibilityOfAllElements(products));

		products.stream().filter(product -> {
			String productName = product.findElement(By.cssSelector("b")).getText();
			return itemList.contains(productName);
		}).forEach(product -> {
			
			product.findElement(By.cssSelector(".card-body button:last-of-type")).click();

			WebElement toaster = driver.findElement(By.cssSelector("#toast-container"));

			wait.until(ExpectedConditions.visibilityOf(toaster));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));

		});


		driver.findElement(By.cssSelector(".btn.btn-custom[routerlink='/dashboard/cart']")).click();
		
		List<WebElement> elements = driver.findElements(By.cssSelector(".cartSection h3"));
		
		for (WebElement element : elements) {
            System.out.println(element.getText());
        }
		
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='CVV Code ']/following-sibling::input")));
		driver.findElement(By.xpath("//div[text()='CVV Code ']/following-sibling::input")).sendKeys("123");
		driver.findElement(By.xpath("//div[text()='Name on Card ']/following-sibling::input")).sendKeys("Suraj");
		
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
		
		List<WebElement> option = driver.findElements(By.xpath("//span[text()=' India']"));
		
		for (WebElement suggestion : option) {
			if (suggestion.getText().equalsIgnoreCase("india")) {
				suggestion.click();
				break;
			}
		}
		
		driver.findElement(By.cssSelector("a.action__submit")).click();
		System.out.println(driver.findElement(By.cssSelector("td h1")).getText());
		
		driver.quit();
		
	}
}
