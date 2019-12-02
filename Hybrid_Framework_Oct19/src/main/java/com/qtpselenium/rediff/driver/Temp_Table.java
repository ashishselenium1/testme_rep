package com.qtpselenium.rediff.driver;

import java.util.Hashtable;

public class Temp_Table {

	public static void main(String[] args) {
		Hashtable<String,String> table = new Hashtable<String,String>();
		table.put("key","value");
		table.put("name","Selenium");
		table.put("place","India");
		
		System.out.println(table.get("place"));

	}

}
