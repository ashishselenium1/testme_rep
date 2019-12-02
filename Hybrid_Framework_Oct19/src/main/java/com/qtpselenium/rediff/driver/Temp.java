package com.qtpselenium.rediff.driver;

import java.io.File;
import java.util.Date;

import com.qtpselenium.rediff.util.Xls_Reader;

public class Temp {

	public static void main(String[] args) {
		/*
		String path=System.getProperty("user.dir")+"//reports//";
		Date d = new Date();
		String folderName=d.toString().replace(":", "-");
		System.out.println(folderName);
		new File(path+folderName+"//screenshots").mkdirs();
		*/
		/*
		String testName="LoginTest";
		Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"\\TestCases.xlsx");
		int rows = xls.getRowCount(testName);
		int cols = xls.getColumnCount(testName);
		
		
		
		for(int rNum=2;rNum<=rows;rNum++) {
			
			for(int cNum=0;cNum<cols;cNum++) {
				String data = xls.getCellData(testName, cNum, rNum);
				System.out.println(data);
			}
			System.out.println("--------");
		}
		*/
		String test  = "LoginTest";
		Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"\\TestCases.xlsx");
		int rows = xls.getRowCount("TestCases");
		
		for(int rNum=2;rNum<=rows;rNum++) {
			String testName = xls.getCellData("TestCases", "TCID", rNum);
			if(testName.equals(test)) {
				String runMode = xls.getCellData("TestCases", "Runmode", rNum);
				System.out.println(testName +" ----- "+ runMode);
			}
		}
		
		
		
		
		

	}

}
