package com.hr.steps;

import com.hr.pages.Base;
import com.hr.pages.BookingPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class DeleteBooking extends Base {
    BookingPage bookingPage = new BookingPage();

//    @When("User tries to delete an existing booking")
//    public void deleteBooking() throws InterruptedException {
//        int rowID = bookingPage.selectOneOfTheRowsToDelete();
//        bookingPage.deleteRow(rowID);
//    }

    @When("User tries to delete the first booking")
    public void deleteFirstBooking() throws Exception {
        boolean isDeleted = bookingPage.deleteRow(2);
        Assert.assertEquals("no row to delete",true,isDeleted);
    }

    @Then("The booking should be disappeared")
    public void isDeleteSuccessfull() {
        Assert.assertEquals("after delete row count should be less then before",1,bookingPage.isDeleteSuccessfull());
    }
}
