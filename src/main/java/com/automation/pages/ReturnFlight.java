package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ReturnFlight extends BasePage{
    WebDriver driver;

    public ReturnFlight(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    @FindBy(css = "ul[data-test-id=\"listings\"]")
    private WebElement returnListResults;
    @FindBy(css = "button[data-test-id=\"select-button\"]")
    private WebElement continueButton;
    @FindBy(css = "li[aria-label=\"Review your trip\"]")
    private WebElement reviewTripLabel;
    @FindBy(css = "a[data-test-id=\"forcedChoiceNoThanks\"]")
    private WebElement noPopUp;

    public String selectThirdFlight() {
        getWaitM().until(ExpectedConditions.visibilityOfAllElements(returnListResults));
        List<WebElement> columns = returnListResults.findElements(By.cssSelector("button[data-test-id=\"select-link\"]"));
        getWaitM();
        columns.get(2).click();
        getWaitM().until(ExpectedConditions.visibilityOfAllElements(continueButton));
        continueButton.click();
        try {
            getWaitS().until(ExpectedConditions.visibilityOf(noPopUp));
            noPopUp.click();
        } catch (Exception e) {
        }
        getNewWindows();
        getWaitM().until(ExpectedConditions.visibilityOf(reviewTripLabel));
        String labelReturningFlight = reviewTripLabel.getText();
        return labelReturningFlight;
    }

    public void getNewWindows(){
        for(String winHandleBefore : driver.getWindowHandles()){
            driver.switchTo().window(winHandleBefore);
            driver.manage().window().maximize();
        }
    }
}
