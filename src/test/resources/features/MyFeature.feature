Feature: Test feature file

  Scenario : Search books by author
    Given user opens OpenLibrary home page
    When search by 'Summer' keyword
    Then list all search results

  Scenario: browse Trending item
    Given user opens OpenLibrary home page
    Then browse Trending item

