package com.project.testngCucumber.pages;

import com.uiautomation.framework.driverContext.ScenarioContext;
import com.uiautomation.framework.logger.CustomException;
import com.uiautomation.framework.uiElements.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WindowHandlePage extends BasePage {
    ScenarioContext scenarioContext;
    public WindowHandlePage(ScenarioContext scenarioContext) throws CustomException {
        super(scenarioContext);
        this.scenarioContext=scenarioContext;
    }
    @FindBy(how=How.ID,using = "tabButton")
    private WebElement newTabButton;

    public void clickOnNewTabButton() throws CustomException {
        baseUIActions.jseClick(newTabButton);
        //baseUIActions.click(newTabButton);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scenarioContext.driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
    }

    public void switchToChildWindow(){
        String parentWindow = scenarioContext.driver.getWindowHandle();
        Set<String> childWindows = scenarioContext.driver.getWindowHandles();
        for (String window:childWindows){
            if (!window.equalsIgnoreCase(parentWindow)){
                scenarioContext.driver.switchTo().window(window);
            }
        }
    }

    public void frameHandle(){
        scenarioContext.driver.switchTo().frame("");
    }

    public void alartHandle(){
        Alert alert = scenarioContext.driver.switchTo().alert();
        alert.accept();
        alert.dismiss();
        alert.getText();
        alert.sendKeys("Test data");
    }
}
