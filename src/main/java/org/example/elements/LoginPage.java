package org.example.elements;

import org.example.CommonActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginPage extends CommonActions {

    @FindBy(xpath = "//div[@id= 'contentHead']/h1[text() = '%s']")
    public WebElement headerText;

    @FindBy(id = "username")
    public WebElement emailInput;

    @FindBy(id = "password")
    public WebElement passwordInput;

    @FindBy(id = "remember")
    public WebElement rememberMeCheckBox;

    @FindBy(name = "login")
    public WebElement loginBtn;

    @FindBy(xpath = "//form[@id = 'register']/div[@class = 'note']")
    public WebElement wrongPasswordText;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void doLogIn(boolean isValid, boolean isChecked) {
        // Already registered email for testing purpose
        emailInput.sendKeys(configFileReader.getRegisteredEmail());

        if (isValid)
            passwordInput.sendKeys(configFileReader.getPassword());
        else passwordInput.sendKeys(UUID.randomUUID().toString());

        if (isChecked)
            rememberMeCheckBox.click();
        loginBtn.click();
        doImplicitlyWait();
    }

    public void checkWrongPasswordMessage() {
        // The following message is displayed when introducing a wrong password for a registered email
        String message = "Wrong password. Please try again";
        assertTrue(wrongPasswordText.isDisplayed());
        assertEquals("Check message when introducing an invalid password", message, wrongPasswordText.getText());
    }
}
