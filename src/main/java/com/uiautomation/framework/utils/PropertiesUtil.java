package com.uiautomation.framework.utils;

import com.uiautomation.framework.logger.CustomException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    private static PropertiesUtil propertiesUtilInstance = null;
    private Properties propApp = new Properties();
    private PropertiesUtil() throws CustomException {
        this.setProperties();
    }

    private void setProperties() throws CustomException {
        try {
            ClassLoader classLoader = PropertiesUtil.class.getClassLoader();
            InputStream in = classLoader.getResourceAsStream("application.properties");
            propApp.load(in);
            in.close();
        } catch (IOException e) {
            throw new CustomException("");
        }
    }

    public static synchronized PropertiesUtil getPropertiesUtil() throws CustomException {
        if (propertiesUtilInstance==null){
            synchronized (PropertiesUtil.class){
                if (propertiesUtilInstance==null) {
                    propertiesUtilInstance = new PropertiesUtil();
                }
            }
        }
        return propertiesUtilInstance;
    }
    public String getApplication(String key){
        return propApp.getProperty(key);
    }
}
