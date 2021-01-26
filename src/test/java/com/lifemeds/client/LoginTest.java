package com.lifemeds.client;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
	@Test
    public void testUserLogin() throws InterruptedException
    {
		ConfigureSelenium config = new ConfigureSelenium();
		WebDriver driver = config.getChromeDriver();
        driver.get(config.getSiteUrl());
        
        
        WebElement loginField = driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/span/a[1]"));
        loginField.click();
        
        WebElement uNameField = driver.findElement(By.id("userName"));
        uNameField.sendKeys("anjuprema");
        
        WebElement passwordField = driver.findElement(By.id("userPassword"));
        passwordField.sendKeys("anjuprema");
        
        WebElement loginButton = driver.findElement(By.id("loginButton"));
        loginButton.click(); 
        
        Thread.sleep(2000);
        
        WebElement welcomeText = driver.findElement(By.id("errorMessageSpan"));
        System.out.println(welcomeText.getText());
        if(welcomeText.getText().contentEquals("Login Successful")) {
        	assert(true);
        }else {
        	assert(false);
        }
        
        driver.close();
    }
}
