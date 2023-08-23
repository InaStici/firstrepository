package org.example.elements;

import org.example.CommonActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyBooksPage extends CommonActions {

    @FindBy(xpath = "//h1[@class = 'details-title' and contains(text(), 'My Books')]")
    public WebElement pageTitle;

    @FindBy(xpath = "//div[@class = 'hamburger-component header-dropdown']//img[@class = 'hamburger__icon logged']")
    public WebElement hamburgerMenu;

    @FindBy(xpath = "//li//button[text() = 'Log out']")
    public WebElement logOutBtn;

    public MyBooksPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void doLogOut(){
        hamburgerMenu.click();
        logOutBtn.click();
    }
}
