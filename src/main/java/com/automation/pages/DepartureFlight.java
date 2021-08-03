package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DepartureFlight extends BasePage {
    WebDriver driver;

    public DepartureFlight(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    @FindBy(css = "select[id=\"listings-sort\"]")
    private WebElement dropdownSortBy;
    @FindBy(css = "ul[data-test-id=\"listings\"]")
    private WebElement flightResultList;
    @FindBy(css = "button[data-test-id=\"select-button\"]")
    private WebElement continueButton;
    @FindBy(css = "div[class=\"uitk-text uitk-type-left uitk-type-300 uitk-type-bold uitk-step-indicator-step-details-wrapper\"]")
    private WebElement labelReturning;

    private static final String SORT_BY_SHORT_DURATION = "DURATION_INCREASING";

    public boolean validateButtonVisible(){
        boolean allButtonsVisible = true;

        getWaitM().until(ExpectedConditions.elementToBeClickable(flightResultList));
        List<WebElement> columns = flightResultList.findElements(By.cssSelector("button[data-test-id=\"selected-link\"]"));
        for (WebElement cell : columns){
            getWaitS().until(ExpectedConditions.elementToBeClickable(cell));
            allButtonsVisible &= cell.isDisplayed();
        }
        return allButtonsVisible;
    }

    public boolean validateFlightDuration() {
        getWaitS().until(ExpectedConditions.visibilityOfAllElements(flightResultList));
        List<WebElement> columns = flightResultList.findElements(By.className("uitk-type-200 uitk-text-emphasis-theme"));
        for (WebElement cell : columns) {
            getWaitM().until(ExpectedConditions.visibilityOf(cell));
            if (StringUtils.isBlank(cell.getText())) {
                return false;
            }
        }
        return true;
    }

    public boolean validateFlightDetails() {
        getWaitS().until(ExpectedConditions.visibilityOf(flightResultList));
        List<WebElement> columns = flightResultList.findElements(By.className("data-test-id=\"flight-operated\""));
        for (WebElement cell : columns) {
            getWaitM().until(ExpectedConditions.visibilityOf(cell));
            if (StringUtils.isBlank(cell.getText())) {
                return false;
            }
        }
        return true;
    }

    public boolean sortByShortestDuration() {
        getWaitS().until(ExpectedConditions.elementToBeClickable(dropdownSortBy));
        dropdownSortBy.click();
        Select sortByDropDown = new Select(dropdownSortBy);
        sortByDropDown.selectByValue(SORT_BY_SHORT_DURATION);
        getWaitS();
       try {
            getWaitM().until(ExpectedConditions.visibilityOfAllElements(flightResultList));
            List<WebElement> columns = flightResultList.findElements(By.cssSelector("div[data-test-id=\"journey-duration\"]"));
            getWaitS();
            List<Integer> listMinutes = new ArrayList<>();
            for (WebElement cell : columns) {
                int totalMinutes = getTotalMinutes(cell);
                getWaitS();
                listMinutes.add(totalMinutes);
                List<Integer> listMinutesSorted = listMinutes.stream().sorted().collect(Collectors.toList());
                for (int i = 0; i < listMinutes.size(); i++) {
                    if (listMinutes.get(i) != listMinutesSorted.get(i)) {
                        return false;
                    }
                }
            }
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            getWaitS();
        }
        return true;
    }
    /**
     * Select the first flight in the list.
     */
    public String selectFirstFlight() {
        getWaitM().until(ExpectedConditions.visibilityOfAllElements(flightResultList));
        List<WebElement> columns = flightResultList.findElements(By.cssSelector("button[data-test-id=\"select-link\"]"));
        getWaitM().until(ExpectedConditions.elementToBeClickable(columns.get(0)));
        columns.get(0).click();
        getWaitM().until(ExpectedConditions.visibilityOfAllElements(continueButton));
        continueButton.click();
        getWaitL().until(ExpectedConditions.visibilityOf(labelReturning));
        String labelReturningFlight = labelReturning.getText();
        getWaitS();
        return labelReturningFlight;
    }
    /**
     * Get flight duration in minutes.
     */
   private int getTotalMinutes(WebElement cell) {
        String timeText = cell.getText();
        String[] times = timeText.split(" ");
        times[0] = times[0].replace("h", "");
        times[1] = times[1].replace("m", "");
        int totalMinutes = (Integer.parseInt(times[0]) * 60) + (Integer.parseInt(times[1]));
        return totalMinutes;
    }
}
