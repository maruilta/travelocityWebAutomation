package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    private WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebDriverWait getWaitS(){
        wait = new WebDriverWait(driver, 10);
        return wait;
    }
    public WebDriverWait getWaitM(){
        wait = new WebDriverWait(driver, 30);
        return wait;
    }
    public WebDriverWait getWaitL(){
        wait = new WebDriverWait(driver, 60);
        return wait;
    }

    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }
}
