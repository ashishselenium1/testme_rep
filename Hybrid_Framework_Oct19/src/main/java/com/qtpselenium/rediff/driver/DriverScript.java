package com.qtpselenium.rediff.driver;

import java.util.Hashtable;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qtpselenium.rediff.keywords.AppKeywords;
import com.qtpselenium.rediff.util.Xls_Reader;

public class DriverScript {
	ExtentTest test;
	AppKeywords app;
	public DriverScript(ExtentTest test) {
		this.test=test;
	}

	//public static void main(String[] args) {
	public void executeTest(String testName,Xls_Reader xls,Hashtable<String,String> testData) {
		//String testName ="LoginTest";
		//String path = System.getProperty("user.dir")+"\\TestCases.xlsx";
	//	System.out.println(System.getProperty("user.dir")+"\\TestCases.xlsx");
		//Xls_Reader xls = new Xls_Reader(path);
		int rows = xls.getRowCount("Keywords");
		System.out.println("Total Rows "+rows);
	    app = new AppKeywords(test);//prop init
		
		for(int rNum=2;rNum<=rows;rNum++) {
			String tcid = xls.getCellData("Keywords", "TCID", rNum);
			if(testName.equals(tcid)) {
					String keyword = xls.getCellData("Keywords", "Keyword", rNum);
					String objectKey = xls.getCellData("Keywords", "Object", rNum);
					String key = xls.getCellData("Keywords", "Data", rNum);
					String data = testData.get(key);
					
					System.out.println(tcid +" -- "+ keyword+" -- "+objectKey +" -- "+ data);
				//	test.log(Status.INFO, tcid +" -- "+ keyword+" -- "+objectKey +" -- "+ data);
					if(keyword.equals("openBrowser"))
						app.openBrowser(data);
					else if(keyword.equals("navigate"))
						app.navigate(objectKey);
					else if(keyword.equals("click"))
						app.click(objectKey);
					else if(keyword.equals("type"))
						app.type(objectKey,data);
					else if(keyword.equals("verifyElementPresent"))
						app.verifyElementPresent(objectKey);
					else if(keyword.equals("verifylogin"))
						app.verifylogin();
					else if(keyword.equals("verifyTitle"))
						app.verifyTitle(objectKey);
					else if(keyword.equals("login"))
						app.login();
					else if(keyword.equals("clear"))
						app.clear(objectKey);
					else if(keyword.equals("deletePortFolio"))
						app.deletePortFolio(data);
					else if(keyword.equals("wait"))
						app.wait(objectKey);
			}
		}
	}

	public void quit() {
		app.quit();
		
	}

}
