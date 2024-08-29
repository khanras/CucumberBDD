package com.uiautomation.framework.driverContext;

import com.uiautomation.framework.logger.CustomException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.logging.Level;

public class WebdriverActions {
    private static final Logger LOG = LoggerFactory.getLogger(WebdriverActions.class);
    private WebDriver driver;

    public WebDriver getWebDriver(String browser) throws CustomException {
        switch (browser.toUpperCase()) {
            case "CHROME":
                this.driver = this.setUpChromeBrowser();
                break;
            case "IE":
                this.driver = this.setUpIEBrowser();
                break;
            case "FIREFOX":
                this.driver = this.setUpFirefoxBrowser();
                break;
            case "SAFARI":
                this.driver = this.setUpSafariBrowser();
                break;
        }
        this.driver.manage().window().maximize();
        return this.driver;
    }

    private WebDriver setUpSafariBrowser() throws CustomException {
        WebDriverManager.safaridriver().setup();
        return new SafariDriver();/*
        try {
            WebDriverManager.safaridriver().setup();
            return new SafariDriver();
        } catch (Exception var) {
            throw new CustomException("Local webdriver was not instantiated properly." + var.getStackTrace());
        }*/
    }

    private WebDriver setUpFirefoxBrowser() throws CustomException {
        try {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        } catch (Exception var) {
            throw new CustomException("Local webdriver was not instantiated properly." + var.getStackTrace());
        }
    }

    private WebDriver setUpChromeBrowser() throws CustomException {
        try {
            WebDriverManager.chromedriver().setup();
            ChromeOptions optionsCH = new ChromeOptions();
            optionsCH.setExperimentalOption("useAutomationExtension", false);
            LoggingPreferences loggingPreferences = new LoggingPreferences();
            loggingPreferences.enable("browser", Level.ALL);
            optionsCH.setCapability("goog:loggingPrefs", loggingPreferences);
            return new ChromeDriver(optionsCH);
        } catch (Exception var) {
            throw new CustomException("Local webdriver was not instantiated properly." + var.getStackTrace());
        }
    }

    private WebDriver setUpIEBrowser() throws CustomException {
        try {
            WebDriverManager.iedriver().setup();
            return new InternetExplorerDriver();
        } catch (Exception var) {
            throw new CustomException("Local webdriver was not instantiated properly." + var.getStackTrace());
        }
    }

    public void closeBrowser(WebDriver driver, String scenarioName) {
        if (driver != null) {
            LOG.info("Closing Session {} | Scenario {}", ((RemoteWebDriver) driver).getSessionId(), scenarioName);
            driver.quit();
        }
    }
}
