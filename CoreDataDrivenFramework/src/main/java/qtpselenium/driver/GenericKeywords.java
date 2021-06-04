package qtpselenium.driver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import qtpselenium.reports.ExtentManager;
//click, type, open, navigate, select
public class GenericKeywords {
	WebDriver driver;
	WebDriverWait wait;
	Properties prop;
	ExtentTest test;
	SoftAssert softAssert = new SoftAssert();
	
	public void openBrowser(String browser) {
		log("Opening Browser "+browser);
		 if(browser.equalsIgnoreCase("Chrome")) {
	    	  System.setProperty("webdriver.chrome.driver", getProperty("chrome_driver_path"));
	    	  
	    	  ChromeOptions ops = new ChromeOptions();
	    	  ops.addArguments("--start-maximized");
	    	  ops.addArguments("--disable-notifications");
	    	  // add more
	    	  
	    	  driver = new ChromeDriver(ops);
	  		  
	      }else  if(browser.equalsIgnoreCase("Mozilla")) {
	    	  System.setProperty("webdriver.gecko.driver", getProperty("firefox_driver_path"));
	    	  driver = new FirefoxDriver();
	  		  
	      }else  if(browser.equalsIgnoreCase("Edge")) {
	    	  System.setProperty("webdriver.edge.driver", getProperty("edge_driver_path"));
	    	  driver = new EdgeDriver();
	  		
	      }
	      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	      wait = new WebDriverWait(driver,Duration.ofSeconds(10));

	}
	
	public void navigate(String urlKey) {
		log("Navigating to "+getProperty(urlKey));
		driver.get(getProperty(urlKey));		
	}
	
	public void click(String locatorKey) {
		log("Clicking on "+locatorKey);
		findElement(locatorKey).click();
	}
	
	public void type(String locatorKey, String data) {
		log("Typing in "+locatorKey);
		findElement(locatorKey).sendKeys(data);
	}
	
	public void select(String locatorKey,String data) {
		log("Selecting from "+locatorKey);
		Select s=  new Select(findElement(locatorKey));
		s.selectByVisibleText(data);

	}
	
	public void hitEnter(String locatorKey) {
		findElement(locatorKey).sendKeys(Keys.ENTER);
	}
	
	public int getRowNumWithCellData(String data) {
		
		List<WebElement> rows = driver.findElements(getLocator("tablerow_stock_xpath"));
		
		for(int rNum=0;rNum<rows.size();rNum++) {
			WebElement row = rows.get(rNum);
			List<WebElement> cells =row.findElements(By.tagName("td"));
			
			for(int cNum=0;cNum<cells.size();cNum++) {
				System.out.print(cells.get(cNum).getText()+" --- ");
				if(data.startsWith(cells.get(cNum).getText())) {
					// found the row
					return (rNum+1);
				}
			}
		}
		
		return -1;
		
	}

	
	
	/***************************Utility Functions********************************/
	
	// central function to extract the elements
	// present and visible
	public WebElement findElement(String locatorKey) {
		By locator=getLocator(locatorKey);
		// how do find the kind of locator
		
		WebElement e  = null;
		try {
		   wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		   wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		   e = driver.findElement(locator);
     	}catch(Exception ex) {
     		// report the failure in the reports
     		logFailure("Element not found "+ locatorKey, true);
     	}
		return e;
	}
	
	public String getProperty(String key) {
		return prop.getProperty(key);
	}
	
	public By getLocator(String locatorKey) {
		String value = getProperty(locatorKey);
		
		
		By locator=null;
		if(locatorKey.endsWith("_id"))
			locator = By.id(value);
		else if(locatorKey.endsWith("_xpath"))
			locator = By.xpath(value);
		else if(locatorKey.endsWith("_css"))
			locator = By.cssSelector(value);
		else if(locatorKey.endsWith("_name"))
			locator=By.name(value);
		
		return locator;
		
	}
	
	public void log(String msg) {
		test.log(Status.INFO, msg);
	}
	
	// failure
	// abrupt end to the flow
	
	public void logFailure(String errMsg,boolean stopTest) {
		// report in extent reports
		test.log(Status.FAIL, errMsg);
		// add the screenshot in reports
		takeScreenShot();
		// fail in testng
		softAssert.fail(errMsg);
		if(stopTest)
			assertAll();
	}
	
	public void takeScreenShot() {
		 // fileName of the screenshot
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_")+".png";
		// take screenshot
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			// get the dynamic folder name
			FileUtils.copyFile(srcFile, new File(ExtentManager.screenshotFolderPath+screenshotFile));
			//put screenshot file in reports
			test.addScreenCaptureFromPath(ExtentManager.screenshotFolderPath+screenshotFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void assertAll() {
		softAssert.assertAll();
	}
	
	

}
