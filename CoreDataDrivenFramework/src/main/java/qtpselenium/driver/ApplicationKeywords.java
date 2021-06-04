package qtpselenium.driver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;

import com.aventstack.extentreports.ExtentTest;

import qtpselenium.util.Constants;

// for your application
public class ApplicationKeywords extends ValidationKeywords{
	
	public ApplicationKeywords(ExtentTest test) {
		// init the prop file
		try {
			prop = new Properties();
			FileInputStream fs = new FileInputStream(Constants.PROP_FILE_PATH);
			prop.load(fs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	//	System.out.println(prop.getProperty("url"));
		this.test=test;
		
		
	}
	
	public void defaultLogin() {
		navigate("url");
		type("username_id", getProperty("defaultUsername"));
	    type("password_xpath", getProperty("defaultPassword"));
	    click("submit_button_css");
	     
		
	}

	
	public void buyNewStock() {
		log("Buying a new stock");
		
	}
	
	public void checkTransactionHistory() {
		
	}

	public void selectDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dateObj;
		try {
			dateObj = sdf.parse(date);
			System.out.println(dateObj);

			sdf = new SimpleDateFormat("d");
			String day = sdf.format(dateObj);
			System.out.println(day);
			
			sdf = new SimpleDateFormat("MMMM");
			String month = sdf.format(dateObj);
			System.out.println(month);
			
			sdf = new SimpleDateFormat("yyyy");
			String year = sdf.format(dateObj);
			System.out.println(year);
			
			String monthYearWanted=month+" "+year;
			String monthYearDisplayed=findElement("month_year_xpath").getText();
			
			while(!monthYearWanted.equals(monthYearDisplayed)) {
				// click on back
				click("backbutton_xpath");
				monthYearDisplayed=findElement("month_year_xpath").getText();
			}
           driver.findElement(By.xpath("//td[text()='"+day+"']")).click();


		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
