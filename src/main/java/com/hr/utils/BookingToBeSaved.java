package com.hr.utils;

public class BookingToBeSaved {
    private String firstName;
    private String surName;
    private String price;
    private String deposit;
    private String checkIn;
    private String checkOut;

    public BookingToBeSaved(String firstName, String surName, String price, String deposit, String checkIn, String checkOut) {
        this.firstName = firstName;
        this.surName = surName;
        this.price = price;
        this.deposit = deposit;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public BookingToBeSaved() {
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public String getPrice() {
        return price;
    }

    public String getDeposit() {
        return deposit;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

}
