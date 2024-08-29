package com.project.testngCucumber.stepDef;

import com.uiautomation.framework.driverContext.ScenarioContext;
import com.uiautomation.framework.logger.CustomException;
import com.project.testngCucumber.pages.WindowHandlePage;
import com.uiautomation.framework.utils.ScreenshotUtil;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class TestCase01Step {
    private ScenarioContext scenarioContext;
    private WindowHandlePage windowHandlePage;
    private ScreenshotUtil screenshotUtil;
    public TestCase01Step(ScenarioContext scenarioContext) throws CustomException {
        this.scenarioContext=scenarioContext;
        this.windowHandlePage = new WindowHandlePage(scenarioContext);
        this.screenshotUtil = new ScreenshotUtil(scenarioContext);
    }
    @Given("User on the home page")
    public void user_on_the_home_page() {
        System.out.println("User on the home page");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @When("Login using valid credentials")
    public void login_using_valid_credentials() {
        System.out.println("Login using valid credentials");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("User can login successfully")
    public void user_can_login_successfully() {
        System.out.println("User can login successfully");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @And("User check if any broken link available in the page")
    public void userCheckIfAnyBrokenLinkAvailableInThePage() {
        scenarioContext.driver.get("http://google.com/");
        List<WebElement> allLinks = scenarioContext.driver.findElements(By.tagName("a"));
        for (WebElement element : allLinks){
            String url = element.getAttribute("href");
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
                httpURLConnection.setRequestMethod("HEAD");
                httpURLConnection.connect();
                int response = httpURLConnection.getResponseCode();
                if (response>=400){
                    System.out.println("Broken Link: "+url);
                }else {
                    System.out.println("Valid Link: "+url);

                }
                System.out.println(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Given("User on the home page of {string}")
    public void userOnTheHomePageOfURL(String URL) throws CustomException {
        scenarioContext.driver.get(URL);
        System.out.println(screenshotUtil.captureScreenshot("HOME_PAGE"));
    }

    @When("User click on links to open multiple window")
    public void userClickOnLinksToOpenMultipleWindow() throws CustomException {
        windowHandlePage.clickOnNewTabButton();
        System.out.println(screenshotUtil.captureScreenshot("New_Tab_Before"));
    }

    @Then("User validate the windows")
    public void userValidateTheWindows() {
        windowHandlePage.switchToChildWindow();
        System.out.println(screenshotUtil.captureScreenshot("New_Tab_After"));

    }
}
