package com.crm.qa.extentReportListenerVersion4;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.crm.qa.base.TestBase;

	public class ExtentReporterNG extends TestBase implements ITestListener {
		


		public void onStart(ITestContext context) {
			System.out.println("*** Test Suite " + context.getName() + " started ***");
		}

		public void onFinish(ITestContext context) {
			System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
			ExtentTestManager.endTest();
			ExtentManager.getInstance().flush();
		}

		public void onTestStart(ITestResult result) {
			System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
			ExtentTestManager.startTest(result.getMethod().getMethodName());
		}

		public void onTestSuccess(ITestResult result) {
			System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
			ExtentTestManager.getTest().log(Status.PASS, "Test passed");
		}

		public void onTestFailure(ITestResult result) {
			System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
			//Capture ScreenShot for failed cases
			try {
				path = captureScreenshot(driver, result.getMethod().getMethodName());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//Attach ScreenShots with Extent reports
			try {
				ExtentTestManager.getTest().fail("Screenshot",
						MediaEntityBuilder.createScreenCaptureFromPath(path.toString()).build());
			} catch (IOException e) {
				System.out.println("An exception occured while taking screenshot " + e.getCause());
			}
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
		}

		public void onTestSkipped(ITestResult result) {
			System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
			ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
		}

		public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
		}

	}

