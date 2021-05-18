package com.uiautomation.framework.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomException extends Exception{
    public static final Logger LOG = LoggerFactory.getLogger(CustomException.class);
    public CustomException(){
        super();
    }
    public CustomException(String message){
        super(message);
        LOG.error(message);
    }
    public CustomException(String message,Throwable throwable){
        super(message,throwable);
        LOG.error(message);
        LOG.error(throwable.getMessage(),throwable);
    }
}
