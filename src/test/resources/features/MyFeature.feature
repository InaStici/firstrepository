Feature: Test feature

  Scenario : Search books by author
    Given open home page
    When search by 'Summer' keyword
    Then list all search results

  Scenario: browse Trending item
    Given open home page
    Then browse Trending item

  Scenario: api test
    Given api test example

