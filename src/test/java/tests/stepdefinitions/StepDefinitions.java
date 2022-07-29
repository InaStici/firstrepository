package tests.stepdefinitions;

import dataProvider.ConfigFileReader;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.elements.HomePage;
import org.example.elements.SearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import service.RestAssuredExample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

public class StepDefinitions {

    WebDriver driver = new ChromeDriver();
    ConfigFileReader configFileReader;

    @Given("Display Hello World text")
    public void runFirstTest() {
        System.out.println("my first test");
    }

    @And("sum {int} and {int}")
    public void doCount(int a, int b) {
        int sum = a + b;
        System.out.println("sum: " + sum);
    }

    @And("open home page")
    public void openBrowser() {

        configFileReader = new ConfigFileReader();
        System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        homePage.displayLogo();
    }

    @When("search by {string} keyword")
    public void searchByKeyword(String keyword){
        HomePage homePage = new HomePage(driver);
        homePage.doSearch(keyword);
        driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
        SearchPage searchPage = new SearchPage(driver);
        searchPage.searchResultList.isDisplayed();
    }

    @Then("list all search results")
    public void listAllSearchResults(){
        SearchPage searchPage = new SearchPage(driver);
        List<String> list = new ArrayList<>();
        searchPage.getSearchResultList().forEach(c -> {
            list.add(c.getText());
        });

        list.forEach(record -> {
            System.out.println(format("Book title: %s", record));
        });

    }

    @And("browse Trending item")
    public void browseTrendingItem(){
        HomePage homePage = new HomePage(driver);
        homePage.browseDropdown.click();
        homePage.browseDropdownTrendingItem.isDisplayed();
        homePage.browseDropdownTrendingItem.click();
        driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
    }

    @Given("api test example")
    public void apiTestExample(){
        new RestAssuredExample().checkAvatarAndId();
    }

    @After
    public void afterScenario(){
        driver.close();
    }

}
