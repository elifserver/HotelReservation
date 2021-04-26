package com.hr.steps;

import com.hr.pages.Base;
import com.hr.pages.BookingPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.io.IOException;

public class AddBooking extends Base {
    BookingPage bookingPage;

    @When("User navigates to Hotel Reservation page")
    public void openHomePage() throws IOException {
        bookingPage = new BookingPage();
        bookingPage.navigateToHotelBookingPage();
    }

    @When("User types proper name and lastname")
    public void typeValidNameAndLastName() {
        bookingPage.typeValidFirstAndLastName();
    }

    @And("User types a valid price")
    public void userTypesAValidPrice() {
        bookingPage.fillPriceWithValidPrice();
    }

    @And("User {string} deposit option")
    public void userDepositOption(String enabledDisabled) {
        bookingPage.chooseDepositOptionAs(enabledDisabled);
    }

    @And("User selects {string} for check in")
    public void selectCheckInDate(String dateAlias) {
        bookingPage.enterCheckInDate(dateAlias);
    }

    @And("User selects {string} for check out")
    public void selectCheckOutDate(String dateAlias) {
        bookingPage.enterCheckOutDate(dateAlias);
    }

    @Then("User tries to save the booking")
    public void saveBooking() {
        bookingPage.clickSaveButton();
    }

    @Then("Booking should be saved")
    public void bookingShouldBeSaved() throws InterruptedException {
        Assert.assertTrue("New booking could not be found on the table, please check!",bookingPage.isBookingDoneSuccessfully());
    }

}

