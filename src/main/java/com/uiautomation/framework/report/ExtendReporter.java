package com.uiautomation.framework.report;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.presentation.PresentationMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ExtendReporter {
    List<String> jsonReportFiles = new ArrayList<>();
    String extendReportPath;
    String reportName;
    public ExtendReporter(List<String> jsonReportFiles,String extendReportPath,String reportName) {
        this.jsonReportFiles=jsonReportFiles;
        this.extendReportPath=extendReportPath;
        this.reportName = reportName;
    }

    public void createReport() {
        Configuration configuration = new Configuration(new File(this.extendReportPath), this.reportName);
        //configuration.setRunWithJenkins(false);
        //configuration.setBuildNumber("Test");
        configuration.addPresentationModes(PresentationMode.PARALLEL_TESTING);
        ReportBuilder rb =new ReportBuilder(this.jsonReportFiles, configuration);
        rb.generateReports();
    }
}
