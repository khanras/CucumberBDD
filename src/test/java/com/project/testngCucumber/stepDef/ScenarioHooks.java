package com.project.testngCucumber.stepDef;

import com.uiautomation.framework.driverContext.ScenarioContext;
import com.uiautomation.framework.driverContext.WebdriverActions;
import com.uiautomation.framework.logger.CustomException;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class ScenarioHooks {
    private WebdriverActions wda;
    private ScenarioContext scenarioContext;
    private WebDriver driver;

    public ScenarioHooks(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;
    }

    @Before
    public void beforeScenario(Scenario scenario) throws CustomException {
        String browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
        if (this.scenarioContext.driver==null) {
            this.wda = new WebdriverActions();
            this.driver = wda.getWebDriver(browser);
        }
        this.scenarioContext.driver = this.driver;
    }

    @After
    public void afterScenario(Scenario scenario){
        if (scenario.isFailed()){
            scenario.embed(((TakesScreenshot)scenarioContext.driver).getScreenshotAs(OutputType.BYTES), "image/png","Failed Scenario name: "+scenario.getName());
        }
        wda.closeBrowser(scenarioContext.driver, scenario.getName());
    }

}
