package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckTripDetails extends BasePage {
    WebDriver driver;

    public CheckTripDetails(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    @FindBy(css = ".uitk-text-emphasis-theme.uitk-type-500")
    private WebElement tripTotal;
    @FindBy(css = "button[data-test-id=\"goto-checkout-button\"]")
    private WebElement checkOutButton;
    @FindBy(css = "[data-test-id='flight-review-0'] .uitk-heading-4")
    private WebElement departureCity;
    @FindBy(css = "[data-test-id='flight-review-1'] .uitk-heading-4")
    private WebElement returnCity;
    @FindBy(css = "h1[class=\"flights generic-header\"]")
    private WebElement verificationCheckOut;

    public boolean validateTripDetails() {
        boolean allDetailVisible = true;
        getWaitM().until(ExpectedConditions.visibilityOf(tripTotal));
        allDetailVisible &= tripTotal.isDisplayed();
        allDetailVisible &= departureCity.isDisplayed();
        allDetailVisible &= returnCity.isDisplayed();
        return allDetailVisible;
    }

    public String continueBooking() {
        getWaitS().until(ExpectedConditions.visibilityOf(checkOutButton));
        checkOutButton.click();
        getWaitS().until(ExpectedConditions.visibilityOf(verificationCheckOut));
        String getVerificationCheckOut = verificationCheckOut.getText();
        return getVerificationCheckOut;
    }
}
