package com.uiautomation.framework.runner;

import com.uiautomation.framework.report.ExtendReporter;
import com.uiautomation.framework.utils.DateTimeUtil;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

@CucumberOptions(
        features = {"src/test/resources/features/"}
        , glue = {"com.project.testngCucumber.stepDef"}
        //,plugin ={"pretty" , "html:OutputReport" , "json:OutputReport/feature"+".json"}
        //, tags = "testCase03"
)
public class Runner extends AbstractTestNGCucumberTests {
    public static List<String> jsonFilesPath = new ArrayList<>();

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @BeforeTest
    public void beforeTest() {
        String testName = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getName();
        System.out.println("Test name::" + testName);
        String plugin = "pretty, html:OutputReport/TestReport.html, com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:, json:OutputReport/cucumber-json-reports/" + testName + "_" + DateTimeUtil.getCurrentTimeStamp("ddMMyyyyHHmmss") + ".json";
        jsonFilesPath.add("./" + plugin.split("json:")[1]);
        System.setProperty("cucumber.plugin", plugin);
        String tags = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("tags");
        if (tags != null && !tags.isEmpty()) {
            System.setProperty("cucumber.filter.tags", tags);
        }
    }

    @AfterSuite
    public void afterSuit() {
        System.out.println("Json Files path: " + jsonFilesPath);
        new ExtendReporter(jsonFilesPath, "./ExtendReport", "Cucumber Report").createReport();
    }
}