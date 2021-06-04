package qtpselenium.testcases;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import qtpselenium.driver.ApplicationKeywords;
import qtpselenium.reports.ExtentManager;

public class BuyStockTest {
// git
// jenkins
	
	ExtentReports rep;
	ExtentTest test;
	
	@BeforeMethod
	public void init() {
		rep = ExtentManager.getReports();
		test = rep.createTest("Buy Stock Test");
	}
	
	@AfterMethod
	public void quit() {
		if(rep != null)
			rep.flush();
	}

	
	@Test
	public void buyStockTest(ITestContext context) {
		// login
		 context.setAttribute("reportHandle", test);

		  ApplicationKeywords app = new ApplicationKeywords(test);
		  app.log("Starting Buy stick test");
		  app.openBrowser("Chrome");
		  app.defaultLogin();
		  app.select("port_dropdown_id", "Apr18");
		  app.click("add_stock_id");
		  app.type("stockname_id", "Reliance Industries Ltd");
		  app.hitEnter("stockname_id");
		  app.click("calendar_id");
		  app.selectDate("15/02/2020");
		  app.type("addstockqty_id", "100");
		  app.type("addstockprice_id", "500");
		  app.click("addStockButton_id");
		  int row = app.getRowNumWithCellData("Reliance Industries Ltd");
		  System.out.println("Row "+row);
		  if(row==-1)
            app.logFailure("Stock not created", true);		 
		// buy the stock
		
	}

}
