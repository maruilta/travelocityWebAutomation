package com.automation.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {
    public WebDriver driver;
    /**
     * Set the browser and url that will be used.
     */
    public Driver(String browser){
        System.setProperty("webdriver.chrome.driver", "./src/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.travelocity.com/");
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver; //to manage the dom.
    }
    public WebDriver getDriver(){
        return this.driver;
    }
}
