package com.qtpselenium.rediff.testcases;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.qtpselenium.rediff.driver.DriverScript;
import com.qtpselenium.rediff.reports.ExtentManager;
import com.qtpselenium.rediff.util.DataUtil;

public class DeletePortfolioTest extends BaseTest {
String testName ="DeletePortfolioTest";
	
	@BeforeMethod
	public void init() {
		rep = ExtentManager.getInstance(System.getProperty("user.dir")+"//reports//");
		test = rep.createTest(testName);
	}
	
			
	@Test(dataProvider = "getData")
	public void deletePortFolio(Hashtable<String,String> data) {
		if(data.get("Runmode").equals("N") || DataUtil.isSkip(testName, xls)) {
			test.log(Status.SKIP, "Skipping the test as Runmode is N");
			throw new SkipException("Skipping the test as Runmode is N");
		}
		
		test.log(Status.INFO, "Starting login test");
	    app = new DriverScript(test);
		test.log(Status.INFO, "Executing test");
		app.executeTest(testName, xls,data);
		test.log(Status.PASS, "Test Passed");
		
	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName, xls);
	}


}
