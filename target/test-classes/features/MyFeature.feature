Feature: Test feature file

  Scenario: Login to OpenLibrary
    Given open home page
#    And 'Sign Up' button is displayed
    When user click on 'Sign Up' button

  Scenario : Search books by author
    Given open home page
    When search by 'Summer' keyword
    Then list all search results

  Scenario: browse Trending item
    Given open home page
    Then browse Trending item
#
#  Scenario: api test
#    Given api test example

