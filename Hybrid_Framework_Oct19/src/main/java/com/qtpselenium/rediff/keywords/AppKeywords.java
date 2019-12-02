package com.qtpselenium.rediff.keywords;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;

public class AppKeywords extends ValidationKeywords{

	public AppKeywords(ExtentTest test) {
		System.out.println("Inside constructor");
		this.test=test;
		prop = new Properties();
		envProp = new Properties();
		String path = System.getProperty("user.dir")+"//src/test//resources//";
		try {
			FileInputStream fs = new FileInputStream(path+"project.properties");
			prop.load(fs);
			String env = prop.getProperty("env");
			fs = new FileInputStream(path+env+".properties");
			envProp.load(fs);
			//System.out.println(envProp.getProperty("url"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void verifylogin() {
		System.out.println("Validating Login");
		// You
	}
	
	public void login() {
		type("username_textfield_xpath",getProperty("adminUsername"));
		click("username_submit_button_css");
		type("password_textfield_id",getProperty("adminPassword"));
		verifyElementPresent("password_submit_button_xpath");
		click("password_submit_button_xpath");

	}
	
	public void deletePortFolio(String folioName) {
		Select s = new Select(getObject("portfolioid_id"));
		s.selectByVisibleText(folioName);
		click("deletePortfolio_id");
		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();
	}
	
}
