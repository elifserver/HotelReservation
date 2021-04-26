Feature: Booking

  Background: User navigates to Home page
    Given User navigates to Hotel Reservation page

  Scenario: Save Booking - Happy path
    When User types proper name and lastname
    And User types a valid price
    And User "disables" deposit option
    And User selects "today" for check in
    And User selects "last week today" for check out
    And User tries to save the booking
    Then Booking should be saved


 Scenario: Delete Booking - Happy path
    When User tries to delete the first booking
    Then The booking should be disappeared

