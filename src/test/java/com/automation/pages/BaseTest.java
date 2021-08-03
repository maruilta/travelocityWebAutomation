package com.automation.pages;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Parameters;

public class BaseTest {
    public Driver driverBrowser;
    public WebDriver driver;

    private Booking booking;
    private DepartureFlight departureFlight;
    private ReturnFlight returnFlight;
    private CheckTripDetails checkTripDetails;
    private PassengersInformation passengersInformation;

    /**
     * Start browser and the site before test execution
     * @param browser
     * @param url
     */

    @BeforeSuite(alwaysRun = true)
    @Parameters({"browser", "url"})
    public void beforeSuite(String browser, String url){
        driverBrowser = new Driver(browser);
        driver = driverBrowser.getDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    public Booking getBookingProcess() {
        booking = new Booking(driver);
        return booking;
    }

    public DepartureFlight getDepartureProcess() {
        departureFlight = new DepartureFlight(driver);
        return departureFlight;
    }

    public ReturnFlight getReturnProcess() {
        returnFlight = new ReturnFlight(driver);
        return returnFlight;
    }

    public CheckTripDetails getCheckTripProcess() {
        checkTripDetails = new CheckTripDetails(driver);
        return checkTripDetails;
    }

    public PassengersInformation getPassengersInformation() {
        passengersInformation = new PassengersInformation(driver);
        return passengersInformation;
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite(){
        booking.tearDown();
    }
}
