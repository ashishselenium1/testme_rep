package com.qtpselenium.rediff.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.qtpselenium.rediff.driver.DriverScript;
import com.qtpselenium.rediff.reports.ExtentManager;
import com.qtpselenium.rediff.util.Xls_Reader;

public class BaseTest {
	
	Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"\\TestCases.xlsx");
	ExtentReports rep;
	ExtentTest test;
	DriverScript app;

	
	@AfterMethod
	public void quit() {
		rep.flush();
		if(app!=null)
			app.quit();
	}
	

}
