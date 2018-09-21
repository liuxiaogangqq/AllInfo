package com.dhsr.automation.util;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;


public class LogInfo{
    private static final String LOGGER_NAME = "com.dhsr.smartid.util";
    private static final String LOG_CONF_NAME = "log4j.xml";
    private static final String ERROR_LOG_ENTRY_SEPARATOR = ", ";
    private static final String EMPTY_STRING = new String();
    private static Logger log = null;

    //Initialize and load log configuration
    static{  
        try {
			//先从服务器上指定位置加载log4j配置文件
            File file = new File(System.getProperty("user.dir")+"/"+LOG_CONF_NAME);
            if(file.exists()){
            	System.out.println("cunzai");
                DOMConfigurator.configure(System.getProperty("user.dir")+"/"+ LOG_CONF_NAME);
            }
            //如果服务器上指定位置log4j配置文件不存在，加载jar包中的log4j配置文件
            else{
              	System.out.println("nocunzai");
                InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(LOG_CONF_NAME);
                new DOMConfigurator().doConfigure(in, LogManager.getLoggerRepository());
            }
        } catch (Exception e) {
            System.err.println("Loading log4j configuration failed, errorMsg:" + e.getMessage());
        }
        log = Logger.getLogger(LOGGER_NAME);
        log.trace("Log initializing ...");
    }

    public static void logAudit(String message) {
        log.info(message);
    }
    
    public static void logTrace(String message)
    {
        log.debug(message);
    }
    
    public static void logError(Object location, String method, String errorMessage)
    {
        logError(location, method, null, errorMessage);
    }

    public static void logError(Object location, String method, Exception exp, String errorMessage)
    {
        String className = location.getClass().getName();
        if( errorMessage == null )
        {
            errorMessage = "";
        }
        String errorMsg = className + " " + method + ": " + errorMessage;
        StringBuffer msg = new StringBuffer(errorMsg);

        if (exp != null)
        {
            msg.append(getStackTrace(exp));
        }
        else{
            msg.append(getStackTrace(new Throwable()));
        }
        log.error(msg);
    }

    public static void logError(Object location, String method, Exception exp, String sessionId, String errorMessage)
    {
        String msg = sessionId + ERROR_LOG_ENTRY_SEPARATOR + errorMessage;
        logError(location, method, exp, msg);
    }
    
    private static String getStackTrace(Throwable t)
    {
        if (t == null)
        {
            return EMPTY_STRING;
        }
        try
        {
            StringWriter localSw = new StringWriter();
            PrintWriter localPw = new PrintWriter(localSw);
            t.printStackTrace(localPw);
           // StringBuffer msg = new StringBuffer(localSw.toString());
            return localSw.toString();
        }
        catch (Throwable u)
        {
            return EMPTY_STRING;
        }
    }
    
    public static void logInfo(Object location, String message)
    {
        String className = location.getClass().getName();
        String msg = className + ": " + message;
        StringBuffer msgss = new StringBuffer(msg);
        log.info(msgss);
    }

    public static void logInfo(String message)
    {
    	 StringBuffer msg = new StringBuffer(message);
        log.info(msg);
    }
}