package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List; 


public class Booking extends BasePage {
    WebDriver driver;

    public Booking(WebDriver driver){
        super(driver);
        this.driver = driver;
    }
    /**
     * Selectors
     */
    @FindBy(css = "a[aria-controls=\"wizard-flight-pwa\"]")
    private WebElement flightButton;
    @FindBy(css = "a[aria-controls=\"wizard-flight-tab-roundtrip\"]")
    private WebElement roundTripButton;
    @FindBy(css = "div[id=\"adaptive-menu\"]")
    private WebElement travelersMenu;
    @FindBy(css = "input[id=\"adult-input-0\"]")
    private WebElement passengerAdults;
    @FindBy(css = "button[data-testid=\"guests-done-button\"]")
    private WebElement travelersDoneButton;
    @FindBy(css = ".adultStepInput.uitk-flex.uitk-flex-align-items-center.uitk-flex-justify-content-space-between.uitk-step-input.uitk-step-input-mounted > .uitk-flex.uitk-flex-item.uitk-step-input-controls > button:nth-of-type(2)")
    private WebElement increaseAdults;
    @FindBy(css = "div[id=\"preferred-class-input\"]")
    private WebElement flightCategory;
    @FindBy(css = ".uitk-menu-container[aria-hidden='false'] a:nth-of-type(1) > .uitk-menu-list-item-label")
    private WebElement economyClass;
    @FindBy(css = "button[aria-label=\"Leaving from\"]")
    private WebElement leavingFromButton;
    @FindBy(css = "[data-index='0'] > .uitk-button")
    private WebElement originOption;
    @FindBy(css = "button[aria-label=\"Going to\"]")
    private WebElement travelToButton;
    @FindBy(css = "[data-index='0'][data-stid='location-field-leg1-destination-result-item'] .is-subText")
    private WebElement destinationOption;
    @FindBy(css = "button[id=\"d1-btn\"]")
    private WebElement departureDate;
    @FindBy(css = ".uitk-date-picker-menu-pagination-container > button:nth-of-type(2)")
    private WebElement nextMonth;
    @FindBy(css = "#wizard-flight-tab-roundtrip > div.uitk-layout-grid.uitk-layout-grid-gap-three.uitk-layout-grid-columns-small-4.uitk-layout-grid-columns-medium-6.uitk-layout-grid-columns-large-12.uitk-spacing.uitk-spacing-padding-block-three > div.uitk-layout-grid-item.uitk-layout-grid-item-columnspan-small-4.uitk-layout-grid-item-columnspan-medium-6.uitk-layout-grid-item-columnspan-large-4 > div > div > div:nth-child(1) > div > div.uitk-date-picker-menu-container.uitk-date-picker-menu-container-double.uitk-menu-container.uitk-menu-open.uitk-menu-pos-left.uitk-menu-container-autoposition.uitk-menu-container-text-nowrap > div > div.uitk-calendar > div.uitk-date-picker-menu-months-container > div:nth-child(2) > table > tbody")
    private WebElement calendarDeparture;
    @FindBy(css = "#wizard-flight-tab-roundtrip > div.uitk-layout-grid.uitk-layout-grid-gap-three.uitk-layout-grid-columns-small-4.uitk-layout-grid-columns-medium-6.uitk-layout-grid-columns-large-12.uitk-spacing.uitk-spacing-padding-block-three > div.uitk-layout-grid-item.uitk-layout-grid-item-columnspan-small-4.uitk-layout-grid-item-columnspan-medium-6.uitk-layout-grid-item-columnspan-large-4 > div > div > div:nth-child(2) > div > div.uitk-date-picker-menu-container.uitk-date-picker-menu-container-double.uitk-menu-container.uitk-menu-open.uitk-menu-pos-left.uitk-menu-container-autoposition.uitk-menu-container-text-nowrap > div > div.uitk-calendar > div.uitk-date-picker-menu-months-container > div:nth-child(1) > table > tbody")
    private WebElement calendarArrive;
    @FindBy(css = "button[data-stid=\"apply-date-picker\"]")
    private WebElement saveDate;
    @FindBy(css = "button[id=\"d2-btn\"]")
    private WebElement returnDate;
    @FindBy(css = "button[data-testid=\"submit-button\"]")
    private WebElement submitSearch;
    @FindBy(css = "[aria-label=\"Leaving from Las Vegas (LAS - McCarran Intl.)\"]")
    private WebElement resultLeavingFrom;
    @FindBy(css = "[aria-label='Flying to Los Angeles, CA (LAX-Los Angeles Intl.)']")
    private WebElement resultGoingTo;

    private static final int FLIGHT_DURATION = 10;

    /**
     * Select two passengers.
     */
    private static final String TOTAL_PASSENGERS = "2";
    public void selectPassenger(){
        travelersMenu.click();
        if (!TOTAL_PASSENGERS.equals(passengerAdults.getAttribute("value"))){
            for (int i = 1; i < Integer.parseInt(TOTAL_PASSENGERS); i++){
                getWaitS().until(ExpectedConditions.visibilityOf(increaseAdults));
                increaseAdults.click();
            }
        }
        travelersDoneButton.click();
    }
    /**
     * Select economic class.
     */
    public void selectFlightCategory(){
        flightCategory.click();
        getWaitM().until(ExpectedConditions.elementToBeClickable(economyClass));
        economyClass.click();
    }
    /**
     * Pick de departure date.
     */
    public void selectDate(WebElement calendarComponent, String day){
        List<WebElement> columns = calendarComponent.findElements(By.tagName("button"));
        for (WebElement cell: columns) {
            if (cell.getAttribute("data-day").equals(day)){
                getWaitM();
                cell.click();
                break;
            }
        }
        saveDate.click();
    }
    /**
     * Find a flight from Los Angeles to Las Vegas
     */
    public String searchFlights(String flyingFrom, String flyingTo){
        getWaitL().until(ExpectedConditions.elementToBeClickable(flightButton));
        flightButton.click();
        roundTripButton.click();
        selectPassenger();
        selectFlightCategory();

        leavingFromButton.click();
        getWaitL();
        leavingFromButton.sendKeys("Los Angeles");
        getWaitL().until(ExpectedConditions.elementToBeClickable(originOption));
        originOption.click();

        travelToButton.click();
        getWaitL();
        travelToButton.sendKeys("Las Vegas");
        getWaitL().until(ExpectedConditions.elementToBeClickable(destinationOption));
        destinationOption.click();

        departureDate.click();
        getWaitM().until(ExpectedConditions.visibilityOf(nextMonth));
        nextMonth.click();
        String today = DatePicker.getToday();
        selectDate(calendarDeparture, today);
        returnDate.click();
        String dayArrive = String.valueOf(Integer.parseInt(DatePicker.getToday())+FLIGHT_DURATION);
        selectDate(calendarArrive, dayArrive);
        getWaitM().until(ExpectedConditions.elementToBeClickable(submitSearch));
        submitSearch.click();
        getWaitL().until(ExpectedConditions.visibilityOf(resultLeavingFrom));

        return resultLeavingFrom.getText();
    }
}
