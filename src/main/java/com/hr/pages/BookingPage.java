package com.hr.pages;

import com.github.javafaker.Faker;
import com.hr.utils.BookingToBeSaved;
import com.hr.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.util.List;

public class BookingPage extends Base {
    @FindBy(id = "firstname")
    private WebElement firstName;

    @FindBy(id = "lastname")
    private WebElement lastName;

    @FindBy(id = "totalprice")
    private WebElement totalprice;

    @FindBy(id = "depositpaid")
    private WebElement depositpaid;

    @FindBy(id = "checkin")
    private WebElement checkInDate;

    @FindBy(id = "checkout")
    private WebElement checkOutDate;

    @FindBy(xpath = ".//input[@value=' Save ']")
    private WebElement saveButton;

    @FindBy(xpath = ".//div[@id='bookings']/div[@class='row']")
    private static List<WebElement> bookingTable;


    TestUtils utils = new TestUtils();
    Faker faker = new Faker();
    String fName;
    String lName;
    private BookingToBeSaved bookingToBeSaved = new BookingToBeSaved();
    private int bookingCountBeforeDelete = 0;
    private int bookingCountAfterDelete = 0;

    public BookingPage() {
    }

    public void navigateToHotelBookingPage() throws IOException {
        driver.navigate().to(utils.readFromGlobalPropertiesFile("baseUrl"));
    }

    public void typeValidFirstAndLastName() {
        fName = faker.name().firstName();
        lName = faker.name().lastName();
        firstName.sendKeys(fName);
        lastName.sendKeys(lName);
        bookingToBeSaved.setFirstName(fName);
        bookingToBeSaved.setSurName(lName);
    }

    public void fillFirstNameWith(String nameValue) {
        firstName.sendKeys(nameValue);
        bookingToBeSaved.setFirstName(nameValue);
    }

    public void fillLastNameWith(String lastNameValue) {
        lastName.sendKeys(lastNameValue);
        bookingToBeSaved.setSurName(lastNameValue);
    }

    public void fillPriceWithValidPrice() {
        String price = faker.commerce().price(100.00, 100000.00);
        totalprice.sendKeys(price);
        bookingToBeSaved.setPrice(price);
    }

    public void chooseDepositOptionAs(String depositValue) {
        String dropdownValue = strings.get(depositValue);
        bookingToBeSaved.setDeposit(dropdownValue);
        Select select = new Select(depositpaid);
        select.selectByVisibleText(dropdownValue);
    }

    private String calculateTheDate(String dateAlias) {
        String calculatedDate;
        switch (dateAlias) {
            case "today":
                calculatedDate = utils.getDateTime(0);
                break;
            case "yesterday":
                calculatedDate = utils.getDateTime(-1);
                break;
            case "next week today":
                calculatedDate = utils.getDateTime(7);
                break;
            case "last week today":
                calculatedDate = utils.getDateTime(-7);
                break;
            default:
                calculatedDate = utils.getDateTime(0);
                break;
        }
        return calculatedDate;
    }

    public void enterCheckInDate(String dateAlias) {
        String checkinDate = calculateTheDate(dateAlias);
        checkInDate.sendKeys(checkinDate);
        bookingToBeSaved.setCheckIn(checkinDate);
    }

    public void enterCheckOutDate(String dateAlias) {
        String checkoutDate = calculateTheDate(dateAlias);
        bookingToBeSaved.setCheckOut(checkoutDate);
        checkOutDate.sendKeys(checkoutDate);
    }

    public void clickSaveButton() {
        saveButton.click();
    }

    public boolean isBookingDoneSuccessfully() throws InterruptedException {
        waitForMilliseconds(5000);
        bookingTable = driver.findElements(By.xpath(".//div[@id='bookings']/div[@class='row']"));

        WebElement lastRow = bookingTable.get(bookingTable.size() - 1);
        List<WebElement> columns = lastRow.findElements(By.tagName("div"));

        System.out.println(columns.get(0).getText() + "--->>>" + bookingToBeSaved.getFirstName());
        System.out.println(columns.get(1).getText() + "--->>>" + bookingToBeSaved.getSurName());
        System.out.println(columns.get(2).getText() + "--->>>" + bookingToBeSaved.getPrice());
        System.out.println(columns.get(3).getText() + "--->>>" + bookingToBeSaved.getDeposit());
        System.out.println(columns.get(4).getText() + "--->>>" + bookingToBeSaved.getCheckIn());
        System.out.println(columns.get(5).getText() + "--->>>" + bookingToBeSaved.getCheckOut());

        boolean firstNameCheck = columns.get(0).getText().equalsIgnoreCase(bookingToBeSaved.getFirstName());
        if (!firstNameCheck) {
            return false;
        }
        boolean lastNameCheck = columns.get(1).getText().equalsIgnoreCase(bookingToBeSaved.getSurName());
        if (!lastNameCheck) {
            return false;
        }
        boolean priceCheck = columns.get(2).getText().equalsIgnoreCase(bookingToBeSaved.getPrice());
        if (!priceCheck) {
            return false;
        }
        boolean depositCheck = columns.get(3).getText().equalsIgnoreCase(bookingToBeSaved.getDeposit());
        if (!depositCheck) {
            return false;
        }
        boolean checkInDateCheck = columns.get(4).getText().equalsIgnoreCase(bookingToBeSaved.getCheckIn());
        if (!checkInDateCheck) {
            return false;
        }
        boolean checkOutDateCheck = columns.get(5).getText().equalsIgnoreCase(bookingToBeSaved.getCheckOut()
        );
        if (!checkOutDateCheck) {
            return false;
        }
        return true;
    }

//    public int selectOneOfTheRowsToDelete() {
//        int rowID = faker.number().numberBetween(2, bookingTable.size());
//        System.out.println("row to be deleted = " + rowID);
//        return rowID + 1;
//    }

    private int getBookingsCount() {
        return bookingTable.size();
    }

    public boolean deleteRow(int rowID) throws Exception {
        waitForMilliseconds(3000);
        bookingCountBeforeDelete = getBookingsCount();
        if (bookingCountBeforeDelete == 1) {
            return false;
        }
        WebElement e = driver.findElement(By.xpath(".//div[@id='bookings']/div[@class='row'][" + rowID + "]/div/input"));
        e.click();
        waitForMilliseconds(3000);
        bookingCountAfterDelete = getBookingsCount();
        return true;
    }

    public int isDeleteSuccessfull() {
        return Integer.compare(bookingCountBeforeDelete, bookingCountAfterDelete);
    }
}
