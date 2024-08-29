package com.uiautomation.framework.uiElements;

import com.uiautomation.framework.driverContext.ScenarioContext;
import com.uiautomation.framework.logger.CustomException;
import com.uiautomation.framework.utils.PropertiesUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseUIActions {
    WebDriver driver;
    WebDriverWait webDriverWait;
    public long webElementWaitTime;

    public BaseUIActions(ScenarioContext scenarioContext) throws CustomException {
        this.driver=scenarioContext.driver;
        this.webElementWaitTime = Long.parseLong(PropertiesUtil.getPropertiesUtil().getApplication("WebElementWaitTime"));
        this.webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds(this.webElementWaitTime));
    }
    public synchronized void sendKeys(WebElement element,String value) throws CustomException {
        this.waitForElementToBeClickable(element);
        this.scrollIntoView(element);
        element.sendKeys(value);
    }

    public synchronized void scrollIntoView(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor)this.driver;
        jse.executeScript("arguments[0].scrollIntoView();",new Object[]{element});
    }
    public synchronized void jseClick(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor)this.driver;
        jse.executeScript("arguments[0].click();",new Object[]{element});
    }
    public synchronized void click(WebElement element) throws CustomException {
        this.waitForElementToBeClickable(element);
        this.scrollIntoView(element);
        element.click();
    }

    public synchronized void waitForElementToBeClickable(WebElement element) throws CustomException {
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (NoSuchElementException | TimeoutException var) {
            throw new CustomException("Unable to click on the element. Element took longer than expected to appear or no such element exist. "+var.getStackTrace());
        }
    }

    public synchronized void waitForElementToBeVisible(WebElement element) throws CustomException {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(element));
        } catch (NoSuchElementException | TimeoutException var) {
            throw new CustomException("Unable to click on the element. Element took longer than expected to appear or no such element exist. "+var.getStackTrace());
        }
    }
    public synchronized void applyPageLoadTimeOut(int timeOut) throws Throwable {
        driver.manage().timeouts().pageLoadTimeout(timeOut, TimeUnit.SECONDS);
       this.finalize();
    }

}
