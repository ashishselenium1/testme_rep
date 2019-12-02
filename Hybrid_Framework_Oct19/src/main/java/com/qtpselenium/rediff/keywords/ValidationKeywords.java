package com.qtpselenium.rediff.keywords;

import com.aventstack.extentreports.Status;

public class ValidationKeywords extends GenericKeywords{

	public void verifyTitle(String expectedTitleKey) {
		test.log(Status.INFO,"Validating title");
		String actualTitle = driver.getTitle();
		String expectedTitle = getProperty(expectedTitleKey);
		if(!expectedTitle.equals(actualTitle)) {
			reportFailure("Titles did not match. Got tile as "+actualTitle );
		}
			
		test.log(Status.INFO,"Titles match");
	}
	
	public void verifyElementPresent(String objectKey) {
		test.log(Status.INFO,"Validating Element Presence "+objectKey);
		if(!isElementPresent(objectKey)) {
			// report error
			reportFailure("Element not found "+ objectKey);
		}
		test.log(Status.INFO,"Object found");
	}
}
