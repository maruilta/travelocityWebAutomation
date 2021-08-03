package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PassengersInformation extends BasePage {
    WebDriver driver;

    PassengersInformation(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * Selectors
     */

    @FindBy(css = "h2.faceoff-module-title")
    private WebElement whoTravelerLabel;
    @FindBy(css = "#flightInsuranceContainer > section > header > h2")
    private WebElement protectFlightLabel;
    @FindBy(css = ".module-title-urgency")
    private WebElement wayToPayLabel;
    @FindBy(css = "h3.faceoff-module-title")
    private WebElement sendInformationLabel;
    @FindBy(css = ".review-and-book-header")
    private WebElement reviewBookTripLabel;
    @FindBy(css = "input[id=\"firstname[0]\"]")
    private WebElement inputFirstName;
    @FindBy(css = "input[data-tealeaf-name=\"middleName\"]")
    private WebElement inputMiddleName;
    @FindBy(css = "input[id=\"lastname[0]\"]")
    private WebElement inputLastName;
    @FindBy(css = "select[class=\"cko-field always-include gb-whitelist alpha3CountryCode\"]")
    private WebElement selectorCountry;
    @FindBy(css = "input[id=\"phone-number[0]\"]")
    private WebElement inputCellphoneNumber;
    @FindBy(css = "input[id=\"gender_male[0]\"]")
    private WebElement inputGenderMale;
    @FindBy(css = "select[data-custom-rules=\"validateDateOfBirth\"]")
    private WebElement selectorDateofBirth;
    @FindBy(css = "button[id=\"complete-booking\"]")
    private WebElement continueBookingButton;
    @FindBy(css = "a[class=\"error-link\"]")
    private WebElement errorMessage;

    public boolean validateLabels() {
        boolean allLabelsVisibility = true;
        allLabelsVisibility &= whoTravelerLabel.isDisplayed();
        allLabelsVisibility &= protectFlightLabel.isDisplayed();
        allLabelsVisibility &= wayToPayLabel.isDisplayed();
        allLabelsVisibility &= sendInformationLabel.isDisplayed();
        allLabelsVisibility &= reviewBookTripLabel.isDisplayed();
        return allLabelsVisibility;
    }

    public boolean whoTravel() {
        boolean whoTravellingFields = true;
        whoTravellingFields &= inputFirstName.isEnabled();
        whoTravellingFields &= inputMiddleName.isEnabled();
        whoTravellingFields &= inputLastName.isEnabled();
        whoTravellingFields &= selectorCountry.isEnabled();
        whoTravellingFields &= inputCellphoneNumber.isEnabled();
        whoTravellingFields &= inputGenderMale.isEnabled();
        whoTravellingFields &= selectorDateofBirth.isEnabled();
        return whoTravellingFields;
    }

    public String validateError() {
        getWaitM().until(ExpectedConditions.elementToBeClickable(continueBookingButton));
        continueBookingButton.click();
        getWaitM().until(ExpectedConditions.elementToBeClickable(errorMessage));
        return errorMessage.getText();
    }

    /*public String setTravelerOneInformation() {
        inputFirstName.sendKeys("Juan");
        inputLastName.sendKeys("Perez");
        inputPhoneNumber.sendKeys("3451234567");
        inputGenderMale.click();
        selectorDateofBirth.click();
        return setTravelerOneInformation();
    }
    *public String setTravelerTwoInformation() {
    }
    */
}
