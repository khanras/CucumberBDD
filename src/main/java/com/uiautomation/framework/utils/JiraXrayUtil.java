package com.uiautomation.framework.utils;

import com.uiautomation.framework.logger.CustomException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;

public class JiraXrayUtil {
    public static Logger LOG = LoggerFactory.getLogger(JiraXrayUtil.class);
    private String url;
    private String jiraUserName;
    private String jiraPassword;
    public JiraXrayUtil(){
        setApiRequestParameter();
    }
    public void clearJsonFileDirectory(){
        File dir = new File("./OutputReport/cucumber-json-reports/");
        LOG.info("Deleting existing JSON files from folder: "+dir.getName());
        try {
            FileUtils.cleanDirectory(dir);
        } catch (IOException e) {
            LOG.error("Deleting JSON report folder action failed. Folder: "+dir.getName());
            new CustomException(e.getMessage());
        }
        LOG.info("All JSON files are deleted successfully from folder: "+dir.getName());
    }

    public void exportResultToJira(){
        File[] files = null;
        File dir = new File("./OutputReport/cucumber-json-reports/");
        files = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".json");
            }
        });
        if (files != null && files.length > 0){
            FileInputStream fis = null;
            for (File jsonFile: files){
                try {
                    fis = new FileInputStream(jsonFile);
                    byte[] data = new byte[(int) jsonFile.length()];
                    fis.read(data);
                    String str = new String(data,"UTF-8");
                    sendApiRequest(str);
                } catch (IOException e) {
                    new CustomException("");
                } finally {
                    if (fis!=null){
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private void sendApiRequest(String body) {
        setApiRequestParameter();
        ApiUtil apiUtil = new ApiUtil(this.url);
        apiUtil.setRequestBody(body);
        apiUtil.setRequestHeader("Accept","application/json");
        apiUtil.setRequestHeader("Content-Type","application/json");
        EncryptionUtil encryptionUtil = new EncryptionUtil(this.jiraUserName+":"+this.jiraPassword);
        apiUtil.setRequestHeader("Authorization","Basic"+encryptionUtil.getEncodedText());
        apiUtil.sendHttpRequest("POST");
        int responseCode = apiUtil.response.statusCode();
        if (responseCode >= 200 && responseCode <=201){
            LOG.info("Result successfully exported to JIRA. \nResponse code: {}\nResponse message: {}",responseCode,apiUtil.getResponseBodyAsString());
        }else {
            LOG.error("Result was not exported to JIRA. \nResponse code: {}\nResponse message: {}",responseCode,apiUtil.getResponseBodyAsString());
        }
    }

    private void setApiRequestParameter() {
        this.url = System.getenv("JIRAXRAYAPI");
        this.jiraUserName = System.getenv("JIRAUSERNAME");
        this.jiraPassword = System.getenv("JIRAPASSWORD");
    }
}
