package tests.stepdefinitions;

import dataProvider.ConfigFileReader;
import groovy.util.logging.Slf4j;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.CommonActions;
import org.example.elements.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.RestAssuredExample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;
import static org.junit.Assert.assertTrue;

public class StepDefinitions extends CommonActions {

    WebDriver driver = new ChromeDriver();
    ConfigFileReader configFileReader;
    HomePage homePage = new HomePage(driver);
    Logger log = LoggerFactory.getLogger(getClass());
    SearchPage searchPage = new SearchPage(driver);
    AccountCreatePage accountCreatePage = new AccountCreatePage(driver);
    LoginPage loginPage = new LoginPage(driver);
    MyBooksPage myBooksPage = new MyBooksPage(driver);

    @And("user opens OpenLibrary home page")
    public void openHomePage() {
        homePage.displayLogo();
        log.info("Opened home page");
    }

    @When("search by {string} keyword")
    public void searchByKeyword(String keyword) {
        homePage.doSearch(keyword);
        doImplicitlyWait();
        searchPage = new SearchPage(driver);
        searchPage.searchResultList.isDisplayed();
    }

    @Then("list all search results")
    public void listAllSearchResults() {
        List<String> list = new ArrayList<>();
        searchPage.getSearchResultList().forEach(c -> {
            list.add(c.getText());
        });
        log.info("List all records returned by search result:");
        list.forEach(record -> {
            System.out.println(format("Book title: %s", record));
        });

    }

    @And("browse Trending item")
    public void browseTrendingItem() {
        homePage.browseDropdown.click();
        homePage.browseDropdownTrendingItem.isDisplayed();
        homePage.browseDropdownTrendingItem.click();
        doImplicitlyWait();
    }

    @When("user clicks on {string} button")
    public void userClicksOnSignUpButton(String btnName) {
        log.info(format("Click on '%s' button", btnName));
        homePage.signUpBtn.click();
        doImplicitlyWait();
    }

    @Then("user is redirected to {string} page")
    public void userIsRedirectedToSignUpPage(String pageName) {
        assertTrue(getLocator(accountCreatePage.headerText, pageName).isDisplayed());
    }

    @And("user populates all required data for sign up")
    public void userSignsUp() {
        accountCreatePage.completeAllRequiredFieldsSignUp(true, false);
        accountCreatePage.signUpBtn.click();
    }

    @And("user populates invalid user name")
    public void userPopulatesInvalidUsername() {
        accountCreatePage.completeAllRequiredFieldsSignUp(false, false);
        accountCreatePage.signUpBtn.click();
    }

    @And("error message regarding user name is displayed")
    public void userNameErrorMessageIsDisplayed() {
        accountCreatePage.checkInvalidUserNameMessage();
    }

    @Then("user checks welcome message")
    public void userCheckWelcomeMessage() {
        accountCreatePage.checkCreatedUserMessage(configFileReader.getEmail(), configFileReader.getUserName());
    }

    @And("user logs in with valid credentials")
    public void loginWithValidCredentials() {
        loginPage.doLogIn(true, false);
    }

    @And("user logs in with invalid password")
    public void loginWithInvalidPassword() {
        homePage.logInBtn.click();
        loginPage.doLogIn(false, false);
    }

    @And("wrong password message is displayed")
    public void wrongPwdMessageIsDisplayed() {
        loginPage.checkWrongPasswordMessage();
    }

    @Then("My Books page is displayed")
    public void myBooksPageIsDisplayed() {
        assertTrue(myBooksPage.pageTitle.isDisplayed());
    }

    @And("user logs out")
    public void userLogsOut() {
        myBooksPage.doLogOut();
        assertTrue(homePage.logInBtn.isDisplayed());
        assertTrue(homePage.signUpBtn.isDisplayed());
    }
}
