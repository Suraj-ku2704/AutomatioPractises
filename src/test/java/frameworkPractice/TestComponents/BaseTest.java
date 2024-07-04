package frameworkPractice.TestComponents;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import frameworkPractice.PageObjects.LandingPage;
import frameworkPractise.TestData.dataReader;
import java.util.stream.Collectors;


public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;
	


	public WebDriver intializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//frameworkPractise//resources//GlobalData.properties");
		prop.load(fis);

		String browser = prop.getProperty("browser");

		switch (browser) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		return driver;
	}

	@BeforeMethod
	public LandingPage launchApp() throws IOException {

		driver = intializeDriver();

		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	
	public String getScreenShot(String testCaseName,WebDriver driver) throws IOException {
		
		   if (driver == null) {
	            throw new IllegalStateException("Driver is not initialized.");
	        }
		
//		   File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//	        String destinationPath = System.getProperty("user.dir") + "//test-output//" + testCaseName + ".png";
//	        File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
//	        File destination = new File(destinationPath);
//	        FileUtils.copyFile(source, destination);
//	        
//	        return destinationPath;
	        
	    	TakesScreenshot ts = (TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
			FileUtils.copyFile(source, file);
			return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		
		
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		
//		String[] itemArray = { "ADIDAS ORIGINAL", "IPHONE 13 PRO" };
//		List<String> itemList = Arrays.asList(itemArray);
//
//
//		HashMap<Object, List<String>> listMap = new HashMap<Object, List<String>>();
//		listMap.put("item", itemList);

		dataReader dr = new dataReader();
		List<HashMap<String, String>> userData = dr.getJsonToMap(
				System.getProperty("user.dir") + "//src//test//java//frameworkPractise//TestData//dataFile.json");
		List<HashMap<String, String>> itemData = dr.getJsonToMap(
				System.getProperty("user.dir") + "//src//test//java//frameworkPractise//TestData//productList.json");

		List<String> itemList = itemData.stream().map(map -> map.get("item")).collect(Collectors.toList());

// Create the test data array
		Object[][] data = new Object[userData.size()][2];

		for (int i = 0; i < userData.size(); i++) {
		HashMap<Object, List<String>> listMap = new HashMap<>();
		listMap.put("item", itemList);
		data[i][0] = userData.get(i);
		data[i][1] = listMap;
		}
		
		return data;
		//return new Object[][] { { userData.get(0), listMap }, { userData.get(1), listMap } };

	}

//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("userName", "surajunnikrishnan@gmail.com");
//	map.put("PassWord", "Test@123");
//	map.put("incorrectPassWord", "Tst@123");
//	map.put("cardUserName", "Suraj");
//	map.put("cvvNum", "123");
//	map.put("countrySearchText", "ind");
//	map.put("expectedCountry", "India");
//	
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map1.put("userName", "sanjusamson@gmail.com");
//	map1.put("PassWord", "Test@123");
//	map1.put("incorrectPassWord", "Tst@123");
//	map1.put("cardUserName", "Sanju");
//	map1.put("cvvNum", "123");
//	map1.put("countrySearchText", "ind");
//	map1.put("expectedCountry", "India");

	@AfterMethod
    public void tearDown() {
        
            driver.quit();
        
    }
}
