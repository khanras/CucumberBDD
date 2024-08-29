package com.project.testngCucumber.stepDef;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.uiautomation.framework.driverContext.ScenarioContext;
import com.uiautomation.framework.driverContext.WebdriverActions;
import com.uiautomation.framework.logger.CustomException;
import com.uiautomation.framework.utils.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import java.io.IOException;

public class ScenarioHooks {
    private WebdriverActions wda;
    private ScenarioContext scenarioContext;
    private WebDriver driver;

    public ScenarioHooks(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Before
    public void beforeScenario(Scenario scenario) throws CustomException {
        String browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
        if (this.scenarioContext.driver == null) {
            this.wda = new WebdriverActions();
            this.driver = wda.getWebDriver(browser);
        }
        this.scenarioContext.driver = this.driver;
        scenario.log("Execution Started for: " + scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) throws CustomException {
        if (scenario.isFailed()) {
            ScreenshotUtil screenshotUtil = new ScreenshotUtil(this.scenarioContext);
            scenario.attach(screenshotUtil.captureScreenshotAsBytes(), "image/png","Failed Scenario name: "+scenario.getName());
            scenario.log("Screenshot captured here => "+screenshotUtil.captureScreenshot("New_Tab_Before"));
            /*// Capture the screenshot
            ScreenshotUtil screenshotUtil = new ScreenshotUtil(this.scenarioContext);
            String screenshotPath = screenshotUtil.captureScreenshot(scenario.getName());

            // Attach the screenshot to the Extent Report
            ExtentCucumberAdapter.getCurrentStep().fail("Test Failed",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());*/
        }
        wda.closeBrowser(scenarioContext.driver, scenario.getName());
        scenario.log("Execution completed for: " + scenario.getName());
    }
}
