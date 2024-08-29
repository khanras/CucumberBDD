package com.uiautomation.framework.utils;

import com.uiautomation.framework.driverContext.ScenarioContext;
import com.uiautomation.framework.logger.CustomException;
import com.uiautomation.framework.report.CreateDirectory;
import com.uiautomation.framework.uiElements.BasePage;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil extends BasePage {
    public static Logger LOG = LoggerFactory.getLogger(ScreenshotUtil.class);
    private WebDriver driver;
    public ScreenshotUtil(ScenarioContext scenarioContext) throws CustomException {
        super(scenarioContext);
        this.driver= scenarioContext.driver;
    }

    public String captureScreenshot(String screenshotName){
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy_hhmmss");
        Date currentDate = new Date();
        String strDate = sdf.format(currentDate);
        Augmenter aug = new Augmenter();
        TakesScreenshot screenshot = (TakesScreenshot)aug.augment(this.driver);
        File src = screenshot.getScreenshotAs(OutputType.FILE);
        screenshotName = StringUtils.substring(screenshotName,0,30);
        String dest = CreateDirectory.getReportPath("/ExtendReport/Screenshots/")+screenshotName+"_"+strDate+".png";
        File target = new File(dest);
        try {
            FileUtils.copyFile(src,target);
        } catch (IOException e) {
            new CustomException("Screenshot file copy to the destination directory is failed!");
        }
        return dest;
    }

    public byte[] captureScreenshotAsBytes() {
        return ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.BYTES);
    }


}
