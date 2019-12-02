package com.qtpselenium.rediff.util;

import java.util.Hashtable;

public class DataUtil {
	// true - N
	// false - Y
	public static boolean isSkip(String test, Xls_Reader xls) {
		int rows = xls.getRowCount("TestCases");
		
		for(int rNum=2;rNum<=rows;rNum++) {
			String testName = xls.getCellData("TestCases", "TCID", rNum);
			if(testName.equals(test)) {
				String runMode = xls.getCellData("TestCases", "Runmode", rNum);
				if(runMode.equals("Y"))
					return false;
				else
					return true;
			}
		}
		
		return true;
		
	}
	
	public static Object[][] getData(String testName, Xls_Reader xls){
		int rows = xls.getRowCount(testName);
		int cols = xls.getColumnCount(testName);
		
		Object[][] objData = new Object[rows-1][1];
		Hashtable<String,String> table = null;
		int row=0;
		for(int rNum=2;rNum<=rows;rNum++) {
			table = new Hashtable<String,String>();
			for(int cNum=0;cNum<cols;cNum++) {
				
				//objData[rNum-2][cNum] = xls.getCellData(testName, cNum, rNum);
				//   00     01    02   03    04
				//   10     11    12   13    14
				//   20     21    22   23    24
				String data = xls.getCellData(testName, cNum, rNum);
				String colname = xls.getCellData(testName, cNum, 1);				
				table.put(colname, data);
				//System.out.println(data);
			}
			
			objData[row][0]=table;
			row++;
			System.out.println("--------");
		}
		
		return objData;

	}

}
