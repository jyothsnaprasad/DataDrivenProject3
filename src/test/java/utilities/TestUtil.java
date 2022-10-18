package utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import base.BaseTest;

public class TestUtil extends BaseTest{
	
	public static String fileName;

	public static void captureScreenshot() {
		
		Date d = new Date();
		String fileName = d.toString().replace(":","_").replace(" ","_") + ".jpg";
		
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		
			try {
				FileUtils.copyFile(screenshotFile, new File(".//reports//" + fileName));
				FileUtils.copyFile(screenshotFile, new File("target/surefire-reports/html/" + fileName));
			} 
			
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	
	
	
	@DataProvider(name = "dp")
	public Object[][] getData(Method m) {
	
		
		String sheetName = m.getName();
		
		int rowNum = excel.getRowCount(sheetName);
		int colNum = excel.getColumnCount(sheetName);
		
		Object[][] data = new Object[rowNum-1][colNum];
		
		
		for(int rows=2; rows<=rowNum; rows++) {
			
			
			for(int cols=0; cols<colNum; cols++) {
				
					
				data[rows-2][cols] = excel.getCellData(sheetName, cols, rows);
				
				
			}
		}
		
		
		
		return data;
		
		
		
	}
	
	
}
