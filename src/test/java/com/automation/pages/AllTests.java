package com.automation.pages;

import org.testng.annotations.Test;
import org.testng.annotations.*;
import org.testng.Assert;

public class AllTests extends BaseTest {

    public String browser;

    @Test(priority = 1, description = "Booking roundtrip flight, pick 2 adults travelers.")
    @Parameters({"leavingfrom", "goingto", "browser"})
    public void selectBooking(String flyingFrom, String flyingTo, String browser){
        this.browser = browser;
        Booking booking = getBookingProcess();
        String result = booking.searchFlights(flyingFrom, flyingTo);
        Assert.assertEquals("Las Vegas", result);
    }

    @Test(priority = 2, description = "Check flight results.")
    public void resultsVisibility(){
        DepartureFlight departureFlight = getDepartureProcess();
        boolean detailsFlight = departureFlight.validateFlightDetails();
        boolean allButtonVisible = departureFlight.validateButtonVisible();
        boolean durationVisible = departureFlight.validateFlightDuration();
        Assert.assertTrue(detailsFlight);
        Assert.assertTrue(allButtonVisible);
        Assert.assertTrue(durationVisible);
    }

    @Test(priority = 3, description = "Sort the results by flight duration.")
    public void getListSorted(){
        DepartureFlight departureFlight = getDepartureProcess();
        boolean validateListSorted = departureFlight.sortByShortestDuration();
        Assert.assertTrue(validateListSorted);
    }

    @Test(priority = 4, description = "Choose departing flight, pick the first result.")
    public void selectDepartingFlight(){
        DepartureFlight departureFlight = getDepartureProcess();
        String labelReturningFlight = departureFlight.selectFirstFlight();
        Assert.assertEquals("Choose returning flight",labelReturningFlight);
    }

    @Test(priority = 5, description = "Choose returning flight, pick the third result.")
    public void selectReturningFlight(){
        ReturnFlight returnFlight = getReturnProcess();
        String labelReviewTrip = returnFlight.selectThirdFlight();
        Assert.assertEquals("Review your trip",labelReviewTrip);
    }

    @Test(priority = 6, description = "Validate details are visible")
    public void validateDetailVisible(){
        CheckTripDetails checkTripDetails = getCheckTripProcess();
        boolean detailVisible = checkTripDetails.validateTripDetails();
        Assert.assertTrue(detailVisible);
    }

    @Test(priority = 7, description = "Checkout process")
    public void selectCheckOut(){
        CheckTripDetails checkTripDetails = getCheckTripProcess();
        String labelAfterCheckOut = checkTripDetails.continueBooking();
        Assert.assertEquals("Secure booking - only takes a few minutes!",labelAfterCheckOut);
    }

    @Test(priority = 8, description = "Validate details of the trip.")
    public void validateDetailTrip(){
        PassengersInformation validateDetailTrip = getPassengersInformation();
        boolean allDetailVisible = validateDetailTrip.validateLabels();
         Assert.assertTrue(allDetailVisible);
    }

    @Test(priority = 9, description = "Validate fields of travelers information.")
    public void validateFieldsWhoTravelling(){
        PassengersInformation validateDetailTrip = getPassengersInformation();
        boolean fieldsareEnabled = validateDetailTrip.whoTravel();
        Assert.assertTrue(fieldsareEnabled);
        //validateDetailTrip.setTravelerOneInformation();
    }

    @Test(priority = 10, description = "Validations")
    public void validateErrorMessage(){
        PassengersInformation validateDetailTrip = getPassengersInformation();
        String getErrorMessage = validateDetailTrip.validateError();
        Assert.assertEquals("invalid field.",getErrorMessage);
    }

}
