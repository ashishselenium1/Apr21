package qtpselenium.listener;

// Test Context -  scope of the test
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class MyTestNGListener implements ITestListener{

	public void onTestFailure(ITestResult result) {
		System.out.println("Test Failed");
		//System.out.println(result.getName());
		//System.out.println(result.getTestName());
		ExtentTest test = (ExtentTest)result.getTestContext().getAttribute("reportHandle");
		test.log(Status.FAIL, result.getThrowable().getMessage());
		//System.out.println(result.getStatus());
		System.out.println(result.getThrowable().getMessage());
		
	}
	
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test Passed "+result.getName());
		ExtentTest test = (ExtentTest)result.getTestContext().getAttribute("reportHandle");
		test.log(Status.PASS, "Test Passed");
				

	}
}
