package org.example;

import dataProvider.ConfigFileReader;
import io.cucumber.java.BeforeAll;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class CommonActions {

    protected WebDriver driver;
    protected ConfigFileReader configFileReader;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        openBrowser();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    protected void openBrowser() {
        driver.get(configFileReader.getUrl());
        driver.manage().window().maximize();
        doImplicitlyWait();
        driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
    }

    protected void doImplicitlyWait(){
        driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
    }

    protected WebElement getLocator(WebElement xpath, String containingText){
        return driver.findElement(By.xpath(String.valueOf(xpath).formatted(containingText)));
    }

}
