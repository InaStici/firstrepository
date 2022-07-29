package org.example.elements;

import dataProvider.ConfigFileReader;
import org.example.CommonActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;


public class HomePage extends CommonActions {

    @FindBy(xpath = "//div[@class = 'logo-txt']")
    public WebElement logo;

    @FindBy(xpath = "//div[@class = 'search-component']//input[@name = 'q']")
    public WebElement searchInput;

    @FindBy(xpath = "//div[@class = 'search-component']//input[@type = 'submit']")
    public WebElement searchSubmit;

    @FindBy(xpath = "//header[@id = 'header-bar']//ul[@class = 'navigation-component']/li/div[@class = 'browse-component header-dropdown']")
    public WebElement browseDropdown;

    @FindBy(xpath = "//header[@id = 'header-bar']//ul[@class = 'navigation-component']//div[contains(@class, 'browse-dropdown-component')]//li/a[contains(text(), 'Trending')]")
    public WebElement browseDropdownTrendingItem;

    @FindBy(xpath = "")
    public WebElement loginButton;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        configFileReader = new ConfigFileReader();
    }

    public void openHomePage() {
        driver.get(configFileReader.getUrl());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
    }

    public void displayLogo() {
        logo.isDisplayed();
    }

    public void doSearch(String searchKeyword){
        searchInput.sendKeys(searchKeyword);
        searchSubmit.click();
    }


}
