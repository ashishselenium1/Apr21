package qtpselenium.testcases;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import qtpselenium.driver.ApplicationKeywords;
import qtpselenium.reports.ExtentManager;

public class DeleteStockTest {

	ExtentReports rep;
	ExtentTest test;
	
	@BeforeMethod
	public void init() {
		rep = ExtentManager.getReports();
		test = rep.createTest("Delete Stock Test");
	}
	
	@AfterMethod
	public void quit() {
		if(rep != null)
			rep.flush();
	}
	
	
	
	@Test
	public void deleteStockTest(ITestContext context) {
		 context.setAttribute("reportHandle", test);
		 ApplicationKeywords app = new ApplicationKeywords(test);
		  app.log("Starting Buy stick test");
		  app.openBrowser("Chrome");
		  app.defaultLogin();
		  app.select("port_dropdown_id", "Apr18");
		  //table[@id='stock']/tbody/tr[1]/td[1]/input
	}
	
	
	
	
	
	
	
	
}
