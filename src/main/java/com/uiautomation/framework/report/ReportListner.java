package com.uiautomation.framework.report;

import com.uiautomation.framework.logger.CustomException;
import com.uiautomation.framework.utils.PropertiesUtil;
import org.testng.*;
import org.testng.annotations.IConfigurationAnnotation;
import org.testng.annotations.IDataProviderAnnotation;
import org.testng.annotations.IFactoryAnnotation;
import org.testng.annotations.ITestAnnotation;
import org.testng.xml.XmlSuite;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

public class ReportListner implements ITestListener, IAlterSuiteListener {
    @Override
    public void alter(List<XmlSuite> list) {
        XmlSuite xmlSuite = (XmlSuite)list.get(0);
        xmlSuite.setDataProviderThreadCount(10);
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
