package com.qtpselenium.rediff.keywords;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qtpselenium.rediff.reports.ExtentManager;

public class GenericKeywords {

	WebDriver driver = null;
	Properties prop=null;// project.prop
	Properties envProp=null;// env specific properties file
	ExtentTest test;
	
	public void openBrowser(String browserName) {
		
		test.log(Status.INFO,"Opening browser "+ browserName);
		if(getProperty("gridRun").equals("Y")) {
			
			DesiredCapabilities cap=null;
			if(browserName.equals("Mozilla")){
				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				cap.setJavascriptEnabled(true);
				
				cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				
			}else if(browserName.equals("Chrome")){
				 cap = DesiredCapabilities.chrome();
				 cap.setBrowserName("chrome");
				 cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			}
			
			try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
				if(browserName.equals("Mozilla"))
					driver= new FirefoxDriver();
				else if(browserName.equals("Chrome"))
					driver = new ChromeDriver();
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	public void navigate(String urlKey) {
		test.log(Status.INFO,"Navigating to URL "+ getProperty(urlKey));
		driver.get(getProperty(urlKey));
	}
	
	public void click(String objectKey) {
		test.log(Status.INFO,"Clicking on object "+getProperty(objectKey));
		getObject(objectKey).click();
			}
	
	public void type(String objectKey, String data) {
		test.log(Status.INFO,"Typing in object "+ getProperty(objectKey) + ". Data is "+ data);
		getObject(objectKey).sendKeys(data);
	}
	
	public void clear(String objectKey) {
		test.log(Status.INFO,"Clearing text field "+ getProperty(objectKey));
		getObject(objectKey).clear();
	}
	
	public void wait(String timeInSec) {
		try {
			int t = Integer.parseInt(timeInSec);
			Thread.sleep(t*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void quit() {
		driver.quit();
	}
	/********************************Utility functions*************************************/
	
	public WebElement getObject(String objectKey) {
		WebElement e = null;
		try {
			// presence
			if(objectKey.endsWith("_xpath"))
				e = driver.findElement(By.xpath(getProperty(objectKey)));
			else if(objectKey.endsWith("_css"))
				e = driver.findElement(By.cssSelector(getProperty(objectKey)));
			else if(objectKey.endsWith("_id"))
				e = driver.findElement(By.id(getProperty(objectKey)));
			else if(objectKey.endsWith("_name"))
				e = driver.findElement(By.name(getProperty(objectKey)));
			
			// interaction
			WebDriverWait wait = new WebDriverWait(driver, 10);
			if(objectKey.endsWith("_xpath"))
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(getProperty(objectKey))));
			else if(objectKey.endsWith("_css"))
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(getProperty(objectKey))));
			else if(objectKey.endsWith("_id"))
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(getProperty(objectKey))));
			else if(objectKey.endsWith("_name"))
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(getProperty(objectKey))));
			
			
		}catch(Exception ex) {
			// report the error
			//reportFailure("Unable to extract object "+objectKey+". Exception - "+ ex.getMessage());
			reportFailure("Unable to extract object "+objectKey);
		}
		
		return e;
		
	}
	// true  - present
	// false - not present
	public boolean isElementPresent(String objectKey) {
		int total = 0;
		if(objectKey.endsWith("_xpath"))
			total = driver.findElements(By.xpath(getProperty(objectKey))).size();
		else if(objectKey.endsWith("_css"))
			total = driver.findElements(By.cssSelector(getProperty(objectKey))).size();
		else if(objectKey.endsWith("_id"))
			total = driver.findElements(By.id(getProperty(objectKey))).size();
		else if(objectKey.endsWith("_name"))
			total = driver.findElements(By.name(getProperty(objectKey))).size();
			
				
				
		if(total == 0)
			return false;
		else
			return true;
	}
	
	public String getProperty(String key) {
		if(prop.containsKey(key))
			return prop.getProperty(key);
		else if(envProp.containsKey(key))
			return envProp.getProperty(key);
		else
		{
			// report errr
			return null;
		}
	}
	
	public void reportFailure(String failureMessage) {
		// fail in extent reports
		test.log(Status.FAIL, failureMessage);
		// take screenshot and put in reports
		takeScreenShot();
		// fail in testng
		Assert.fail(failureMessage);
	}
	
	public void takeScreenShot(){
		// fileName of the screenshot
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_")+".png";
		// take screenshot
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			// get the dynamic folder name
			FileUtils.copyFile(srcFile, new File(ExtentManager.screenshotFolderPath+screenshotFile));
			//put screenshot file in reports
			test.log(Status.FAIL,"Screenshot-> "+ test.addScreenCaptureFromPath(ExtentManager.screenshotFolderPath+screenshotFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
