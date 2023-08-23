package org.example.elements;

import org.example.CommonActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccountCreatePage extends CommonActions {

    // Input data for sign up section
    @FindBy(xpath = "//div[@id= 'contentHead']/h1[text() = '%s']")
    public WebElement headerText;

    @FindBy(id = "emailAddr")
    public WebElement emailInput;

    @FindBy(id = "username")
    public WebElement userNameInput;

    @FindBy(id = "password")
    public WebElement passwordInput;

    @FindBy(id = "password2")
    public WebElement confirmPasswordInput;

    @FindBy(id = "ia_newsletter")
    public WebElement receiveNewsCheckBox;

    @FindBy(xpath = "//span[@aria-checked = 'false' and @ id = 'recaptcha-anchor']")
    public WebElement recapchaUncheckedCheckBox;

    @FindBy(xpath = "//span[@aria-checked = 'true' and @ id = 'recaptcha-anchor']")
    public WebElement recapchaCheckedCheckBox;

    @FindBy(xpath = "//small[contains(text(), \"By signing up, you agree to the Internet Archive's\")]")
    public WebElement termsOfServiceText;

    @FindBy(xpath = "//small/a[(text() = 'Terms of Service')]")
    public WebElement termsOfServiceLink;

    @FindBy(xpath = "//div[@class = 'formElement bottom']/button[@id = 'signup']")
    public WebElement signUpBtn;

    @FindBy(xpath = "//div[@class = 'formElement bottom']/a[text() = 'Cancel']")
    public WebElement cancelBtn;

    // Sign up completed section
    @FindBy(xpath = "//div[@id= 'contentHead']/h1[text() = 'Hi, %s']")
    public WebElement headerWelcomeText;

    @FindBy(id = "contentBody")
    public WebElement contentBodyText;

    @FindBy(id = "usernameMessage")
    public WebElement invalidUserNameText;

    public AccountCreatePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void completeAllRequiredFieldsSignUp(boolean isUserNameValid, boolean isChecked) {
        emailInput.sendKeys(configFileReader.getEmail());

        if (isUserNameValid)
            userNameInput.sendKeys(configFileReader.getUserName());
        else userNameInput.sendKeys(configFileReader.getInvalidUserName());

        passwordInput.sendKeys(configFileReader.getPassword());
        confirmPasswordInput.sendKeys(configFileReader.getPassword());

        if (isChecked)
            receiveNewsCheckBox.click();

        recapchaUncheckedCheckBox.click();
        doImplicitlyWait();
        assertTrue(recapchaCheckedCheckBox.isDisplayed());
    }


    public void checkCreatedUserMessage(String email, String userName) {
        // The following message should be displayed when user signed up
        String message = String.format("We’ve sent an email to %s containing a link to verify your account. " +
                "Click/tap on the link to finish creating your Internet Archive account.", email);
        assertTrue(getLocator(headerWelcomeText, userName).isDisplayed());
        assertEquals("Check message after sign up", message, contentBodyText.getAttribute("p"));
    }

    public void checkInvalidUserNameMessage() {
        // The following message should be displayed when introducing a username that doesn't meet the requirements
        String message = "Must be between 3 and 20 letters and numbers";
        assertTrue(invalidUserNameText.isDisplayed());
        assertEquals("Check message for invalid user name", message, invalidUserNameText.getText());
    }

}
