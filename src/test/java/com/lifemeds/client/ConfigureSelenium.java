package com.lifemeds.client;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ConfigureSelenium {
	private WebDriver driver;
	private String url = "http://localhost:4200";
	
	public WebDriver getChromeDriver() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/static/driver/chromedriver.exe");
		driver = new ChromeDriver();
		return driver;
	}
	
	public String getSiteUrl() {
		return url;
	}
}
