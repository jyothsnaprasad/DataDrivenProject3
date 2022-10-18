package testcases;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.TestUtil;

public class AddCustomerTest extends BaseTest {
	
	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void addCustomer(String firstName, String lastName, String postCode) {
		
		click("addCustomerBtn_XPATH");
		type("firstname_XPATH", firstName);
		type("lastname_XPATH",lastName);
		type("postcode_XPATH",postCode);
		click("addcust_XPATH");
		
		Alert alert = driver.switchTo().alert();
		Assert.assertTrue(alert.getText().contains("Customer added successfully"), "Customer not added successfully");
		alert.accept();
		
		
	}
	

}
