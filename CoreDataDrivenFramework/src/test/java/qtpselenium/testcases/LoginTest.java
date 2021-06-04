package qtpselenium.testcases;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import qtpselenium.driver.ApplicationKeywords;
import qtpselenium.reports.ExtentManager;
import qtpselenium.util.DataUtil;
import qtpselenium.util.Xls_Reader;

public class LoginTest {
	
	ExtentReports rep;
	ExtentTest test;
	
	@BeforeMethod
	public void init() {
		rep = ExtentManager.getReports();
		test = rep.createTest("Login Test");
	}
	
	@AfterMethod
	public void quit() {
		if(rep != null)
			rep.flush();
	}
	

	
	@Test(dataProvider = "getData")
	//public void doLogin(JSONObject data,ITestContext context) {
	public void doLogin(Hashtable<String,String> data,ITestContext context) {

		// init the prop file
		  context.setAttribute("reportHandle", test);
		  if(data.get("Runmode").equals("N")) {
			  test.log(Status.SKIP, "Skipping test as runmode was N");
			  throw new SkipException("Skipping test as runmode was N");
		  }
		  ApplicationKeywords app = new ApplicationKeywords(test);
		  
		  app.log("Starting Login Test");
		  app.openBrowser((String)data.get("Browser"));
	      app.navigate("url");
	      // check if search field present
	      // validate text, title
	      app.log("Logging in");
	      app.logFailure("Title of page not corect",false); // non critical
	      //if(!app.validateElementPresent("username_id"))
	    	//  app.logFailure("Username Texfield not present",true);// critical
	      
	      app.type("username_id", (String)data.get("Username"));
	      app.logFailure("Some spelling mistake",false);// non critical
	      app.type("password_xpath", (String)data.get("Password"));
	      
	      
	      //if(!app.validateElementPresent("submit_button_css"))
	    	// app.logFailure("Submit button not present",true);//critical

	      app.click("submit_button_css");
	      test.log(Status.PASS, "Login Test Passed");
	      app.assertAll();
	}
	

	@DataProvider
	public Object[][] getData() throws FileNotFoundException, IOException, ParseException{
		String testName="Login Test";
		//return new DataUtil().getJSONData("session_suite.json", testName);
		Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"//data//xls//session_suite.xlsx");
		Object[][] data= new DataUtil().getXLSData(testName, xls);
		return data;
	}
	
	
	
}
