package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import extentlisteners.ExtentListeners;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExcelReader;
import utilities.MonitoringMail;

public class BaseTest {

	/*
	 * TestNG Screenshots Reporting Log4j Properties Database JavaMail ExcelReading
	 * Keywords
	 */

	public static WebDriver driver;
	public static Properties OR = new Properties();
	public static Properties Config = new Properties();
	public static FileInputStream fis;
	public ExcelReader excel = new ExcelReader(".//src//test//resources//excel//testdata.xlsx");
	public static Logger log = Logger.getLogger(BaseTest.class);
	public static MonitoringMail mail = new MonitoringMail();
	public static WebDriverWait wait;
	public WebElement dropdown;
	
	
	/*   for clicking locators  	 */
	
	
	public void click(String locator) {

		try {
			if (locator.endsWith("_XPATH")) {
				
				//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(locator)))).click();
				driver.findElement(By.xpath(OR.getProperty(locator))).click();
			} else if (locator.endsWith("_CSS")) {
				//wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(OR.getProperty(locator)))).click();
				driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
			} else if (locator.endsWith("_ID")) {
				//wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.getProperty(locator)))).click();
				driver.findElement(By.id(OR.getProperty(locator))).click();
			}

			log.info("Clicking on an Element: " + locator);
			ExtentListeners.test.info("Clicking on an Element: " + locator);
		} catch (Throwable t) {

			log.info("Error while clicking on an Element : " + locator);
			ExtentListeners.test.fail("Error while clicking on an Element : " + locator);
			Assert.fail(t.getMessage());
		}
	}
	
	
	/*   for selecting dropdowns  	 */
	
	public void select(String locator, String value) {

		try {
			if (locator.endsWith("_XPATH")) {
				
				//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(locator)))).click();
				dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
			} else if (locator.endsWith("_CSS")) {
				//wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(OR.getProperty(locator)))).click();
				dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
			} else if (locator.endsWith("_ID")) {
				//wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.getProperty(locator)))).click();
				dropdown = driver.findElement(By.id(OR.getProperty(locator)));
			}

			log.info("Clicking on an Element: " + locator);
			ExtentListeners.test.info("Clicking on an Element: " + locator);
			
			Select select = new Select(dropdown);
			select.selectByVisibleText(value);
			
			log.info("Selecting on an Element: " + locator + "with value as : "+value);
			ExtentListeners.test.info("Selecting on an Element: " + locator + "with value as : "+value);
			 
		} catch (Throwable t) {

			log.info("Error while selecting on an Element : " + locator);
			ExtentListeners.test.fail("Error while selecting on an Element : " + locator);
			Assert.fail(t.getMessage());
		}
	}
	
	
	
	/*   for checking elements  	 */
	
	//Assert.assertTrue(isElementPresent("button"))
	
	public boolean isElementPresent(String locator) {

		try {
			if (locator.endsWith("_XPATH")) {
				driver.findElement(By.xpath(OR.getProperty(locator)));
			} else if (locator.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(OR.getProperty(locator)));
			} else if (locator.endsWith("_ID")) {
				driver.findElement(By.id(OR.getProperty(locator)));
			}

			log.info("Verifying Element presence for : " + locator);
			ExtentListeners.test.info("Verifying Element presence for : " + locator);
			return true;
		} catch (Throwable t) {

			log.info("Error while finding an Element : " + locator);
			return false;
		}
	}
	
	
	/*   for typing in locators  	 */

	public void type(String locator, String value) {
		try {
			if (locator.endsWith("_XPATH")) {
				driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
			} else if (locator.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
			} else if (locator.endsWith("_ID")) {
				driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
			}

			log.info("Typing in an Element: " + locator + "  entered the value as : " + value);
			ExtentListeners.test.info("Typing in an Element: " + locator + "  entered the value as : " + value);
		} catch (Throwable t) {

			log.info("Error while typing in an Element : " + locator);
			ExtentListeners.test.fail("Error while typing in an Element : " + locator);
			Assert.fail(t.getMessage());
		}
	}


	
	
	@BeforeSuite
	public void setUp() {

		if (driver == null) {
			PropertyConfigurator.configure(".//src//test//resources//properties//log4j.properties");

			log.info("Test Execution started!!! ");

			try {
				fis = new FileInputStream(".//src//test//resources//properties//Config.properties");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				Config.load(fis);
				log.info("Config properties loaded!!! ");

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				fis = new FileInputStream(".//src//test//resources//properties//OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				OR.load(fis);
				log.info("OR Properties loaded!!!");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		if (Config.getProperty("browser").equals("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			log.info("Chrome Broswer launched!!!");
		}

		else if (Config.getProperty("browser").equals("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			log.info("Firefox Broswer launched!!!");
		}

		driver.get(Config.getProperty("testsiteurl"));
		log.info("navigated to  :" + Config.getProperty("testsiteurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")),
				TimeUnit.SECONDS);

	/*	try {
			DbManager.setMysqlDbConnection();
			log.info("database connection established!!!");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		wait = new WebDriverWait(driver, Integer.parseInt(Config.getProperty("explicit.wait")));
*/
	}

	
	
	@AfterSuite
	public void tearDown() {
		driver.quit();
		log.info("Test Execution Completed!!!");
	}

}
