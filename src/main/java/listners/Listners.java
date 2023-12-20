package listners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Base;
import utilities.ExtentReporter;


public class Listners extends Base implements ITestListener {
	ExtentReports ExtentReport = ExtentReporter.getExtentReport();
	
	ExtentTest extentTest;

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		//System.out.println("adddjidihdi");
		
		String	test=result.getName();
		extentTest = ExtentReport.createTest(test+" executed started");
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		String	test=result.getName();
		
		
		extentTest.log(Status.PASS,test+" test passed");
		
	}

	public void onTestFailure(ITestResult result) {
    
		
		String testName = result.getName();
		
		 
		try {
			driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchFieldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		
		
		try {
			String screenFilePath=(String) takeScreenshot(result.getMethod().getMethodName(),driver);
			extentTest.addScreenCaptureFromPath(screenFilePath, testName);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
      // String	test=result.getName();
		
		
		//extentTest.log(Status.FAIL,testName+" test failed");
		extentTest.fail(result.getThrowable());
		
	}

	public void onTestSkipped(ITestResult result) {
		
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		;
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ExtentReport.flush();
	}

	
	
	
}
