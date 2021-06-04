package qtpselenium.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ValidationKeywords extends GenericKeywords{

	public boolean validateElementPresent(String locatorKey) {
		By locator=getLocator(locatorKey);
		// how do find the kind of locator
		
		WebElement e  = null;
		try {
		   wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		   wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		   e = driver.findElement(locator);
     	}catch(Exception ex) {
     		return false;
     	}
		
		return true;
	}
	
	public void validateText(String locator,String expectedText) {
		
	}
	
	public void validateTitle(String expectedTitle) {
		
	}
	
	
}
