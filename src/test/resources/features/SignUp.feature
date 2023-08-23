Feature: Sign up test feature file

  Background:
    Given user opens OpenLibrary home page
    When user clicks on 'Sign Up' button
    Then user is redirected to 'Sign Up' page

  Scenario: Sign up to OpenLibrary with all required data
    When user populates all required data for sign up
    Then user checks welcome message

  Scenario: Sign up with an invalid user name
    When user populates invalid user name
    Then error message regarding user name is displayed