package com.uiautomation.framework.runner;

import com.uiautomation.framework.report.ExtendReporter;
import com.uiautomation.framework.utils.DateTimeUtil;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;

import java.util.ArrayList;
import java.util.List;

@CucumberOptions(
        features = {"src/test/java/com/project/testngCucumber/feature/"}
        ,glue = {"com.project.testngCucumber.stepDef"}
        //,plugin ={"pretty" , "html:OutputReport" , "json:OutputReport/feature"+".json"}
)
public class Runner extends AbstractTestNGCucumberTests {
    public static List<String> jsonFilesPath = new ArrayList<>();
    @BeforeTest
    public void beforeTest(){
        String testName = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getName();
        System.out.println("Test name::"+testName);
        String plugin = "pretty, html:OutputReport, json:OutputReport/cucumber-json-reports/"+testName+"_"+ DateTimeUtil.getCurrentTimeStamp("ddMMyyyyHHmmss")+".json";
        jsonFilesPath.add("./"+plugin.split("json:")[1]);
        System.setProperty("cucumber.plugin",plugin);
        String tags = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("tags");
        System.setProperty("cucumber.options","--tags '"+ tags + "'");
    }
    @AfterSuite
    public void afterSuit(){
        System.out.println("Json Files path: "+jsonFilesPath);
        new ExtendReporter(jsonFilesPath,"./ExtendReport","Cucumber Report");
    }
}