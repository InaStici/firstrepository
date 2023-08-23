Feature: Log in and Log out functionalities

  Scenario: Log in and Log out functionalities
    Given user opens OpenLibrary home page
    When user logs in with valid credentials
    Then My Books page is displayed
    And user logs out

  Scenario: Log in with a wrong password
    Given user opens OpenLibrary home page
    When user logs in with invalid password
    Then wrong password message is displayed