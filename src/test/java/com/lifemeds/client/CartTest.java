package com.lifemeds.client;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartTest {
	ConfigureSelenium config = new ConfigureSelenium();
	WebDriver driver = config.getChromeDriver();

	
	@Test
	public void addItemToCart() {	       
		driver.get(config.getSiteUrl());
        WebElement itemToAdd = driver.findElement(By.xpath("/html/body/app-root/div[2]/div/div/div[2]/app-medicine/div/div[1]/div/div[2]/p[2]/button"));
        itemToAdd.click();
        
        WebElement itemToAdd2 = driver.findElement(By.xpath("/html/body/app-root/div[2]/div/div/div[2]/app-medicine/div/div[2]/div/div[2]/p[2]/button"));
        itemToAdd2.click();
        
        WebElement itemToAdd3 = driver.findElement(By.xpath("/html/body/app-root/div[2]/div/div/div[2]/app-medicine/div/div[3]/div/div[2]/p[2]/button"));
        itemToAdd3.click();
        
        WebElement cartCount = driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/div/a/span"));
        
        if(cartCount.getText().contentEquals("3")) {
        	assert(true);
        }else assert(false);
        
	}
	
	@Test
	public void makePurchaseTest() throws InterruptedException {
		driver.get(config.getSiteUrl());
        WebElement itemToAdd = driver.findElement(By.xpath("/html/body/app-root/div[2]/div/div/div[2]/app-medicine/div/div[1]/div/div[2]/p[2]/button"));
        itemToAdd.click();
        
        WebElement itemToAdd2 = driver.findElement(By.xpath("/html/body/app-root/div[2]/div/div/div[2]/app-medicine/div/div[2]/div/div[2]/p[2]/button"));
        itemToAdd2.click();
        
        WebElement itemToAdd3 = driver.findElement(By.xpath("/html/body/app-root/div[2]/div/div/div[2]/app-medicine/div/div[3]/div/div[2]/p[2]/button"));
        itemToAdd3.click();
        
        WebElement cartLink = driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/div/a"));
        cartLink.click();
        
        WebElement checkoutButton = driver.findElement(By.xpath("/html/body/app-root/div[2]/div/div/div[2]/app-cart/section/div/div[2]/div/div/button"));
        checkoutButton.click();
        
        Thread.sleep(1000);
        WebElement loginButton = driver.findElement(By.id("loginButton"));
        if(loginButton != null) {
        	WebElement loginField = driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/span/a[1]"));
            loginField.click();
            
            WebElement uNameField = driver.findElement(By.id("userName"));
            uNameField.sendKeys("anjuprema");
            
            WebElement passwordField = driver.findElement(By.id("userPassword"));
            passwordField.sendKeys("anjuprema");
            
            loginButton.click();            
        }
        Thread.sleep(1000);
        WebElement checkoutButton2 = driver.findElement(By.xpath("/html/body/app-root/div[2]/div/div/div[2]/app-cart/section/div/div[2]/div/div/button"));
        if(checkoutButton2 != null) checkoutButton2.click();
        
        WebElement deliveryAddress = driver.findElement(By.id("deliveryAddress"));
        deliveryAddress.sendKeys("Test Address");
        
        WebElement cardNumber = driver.findElement(By.id("cardNumber"));
        cardNumber.sendKeys("12345678");
        
        WebElement expiryMonth = driver.findElement(By.id("expiryMonth"));
        expiryMonth.sendKeys("12/2030");
        
        WebElement cvvCode = driver.findElement(By.id("cvvCode"));
        cvvCode.sendKeys("12/2030");
        
        WebElement purchaseButton = driver.findElement(By.id("paynowbutton"));
        purchaseButton.click();
        
        Thread.sleep(1000);
        
        WebElement orderElement = driver.findElement(By.xpath("/html/body/app-root/div[2]/div/div/div[2]/app-order/table/tbody/tr[1]/td[2]"));
        if(orderElement.getText().contentEquals("Test Address")) {
        	assert(true);
        }else assert(false);
	}

}
