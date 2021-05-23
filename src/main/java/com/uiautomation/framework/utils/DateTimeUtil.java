package com.uiautomation.framework.utils;

import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;

public class DateTimeUtil {
    private DateTimeUtil(){
        throw new IllegalStateException("This class has all static method. It cannot be instantiated.");
    }
    public static String getCurrentTimeStamp(String format){
        Date date = new Date();
        return DateFormatUtils.format(date,format);
    }
}
