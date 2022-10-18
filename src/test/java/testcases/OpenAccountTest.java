package testcases;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.TestUtil;

public class OpenAccountTest extends BaseTest{
	
	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void OpenAccount(String customer, String currency) {
		
		click("openaccount_XPATH");
		select("customer_XPATH",customer);
		select("currency_XPATH",currency);
		click("process_XPATH");
		
		Alert alert = driver.switchTo().alert();
		Assert.assertTrue(alert.getText().contains("Account created successfully"), "Account not added successfully");
		alert.accept();
		
	}
}
