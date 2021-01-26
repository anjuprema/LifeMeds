package com.lifemeds.client;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class HomePageTest {
	ConfigureSelenium config = new ConfigureSelenium();
	WebDriver driver = config.getChromeDriver();

	
	@Test
	public void checkHomePageAvailability() {	       
		driver.get(config.getSiteUrl());
        WebElement titleLink = driver.findElement(By.id("projectTitleLink"));
        String title = titleLink.getText();
        if(title.contentEquals("Life Meds")) {
        	assert(true);
        }else assert(false);      
        
	}
	
	@Test
	public void checkCategoryAvailability() throws InterruptedException {
		driver.get(config.getSiteUrl());
		WebElement categoryLink = driver.findElement(By.xpath("/html/body/app-root/div[2]/div/div/div[1]/ul[1]/li[2]"));
		
		Thread.sleep(2000);
		if(categoryLink != null) assert(true);
    	else assert(false);
	}
	
	@Test
	public void checkSellerAvailability() throws InterruptedException {
		driver.get(config.getSiteUrl());
		WebElement sellerLink = driver.findElement(By.xpath("/html/body/app-root/div[2]/div/div/div[1]/ul[2]/li[2]"));
		
		Thread.sleep(2000);
		if(sellerLink != null) assert(true);
    	else assert(false);
	}
	
	@Test
	public void checkSearchFeature() throws InterruptedException {
		driver.get(config.getSiteUrl());
		WebElement searchInput = driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li/form/input"));
		searchInput.sendKeys("Immunity");
		
		WebElement searchButton = driver.findElement(By.id("searchButton"));
		searchButton.click();
		
		WebElement item = driver.findElement(By.xpath("/html/body/app-root/div[2]/div/div/div[2]/app-medicine/div/div[1]"));
		
		Thread.sleep(2000);
		if(item != null) assert(true);
    	else assert(false);
	}

}
