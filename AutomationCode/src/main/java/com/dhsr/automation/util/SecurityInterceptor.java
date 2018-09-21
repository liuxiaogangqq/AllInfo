package com.dhsr.automation.util;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  

import org.springframework.web.servlet.HandlerInterceptor;  
import org.springframework.web.servlet.ModelAndView;  

  
  
public class SecurityInterceptor implements HandlerInterceptor {  

  //  private static final String LOGIN_URL = "login.html";  
  //  private static final String NOCON_URL = "NoPermission.html";  
    public static HttpSession session =null; 
    public LogInfo logInfo  = new LogInfo();
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {  
        //session = req.getSession(true);  
        //String requestUri = req.getRequestURI();
    
        return true;  
    }  
  
    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object arg2, ModelAndView arg3) throws Exception { 
    	
     }
  
    public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object arg2, Exception arg3) throws Exception { 
    	LogInfo.logInfo("---------------jinru3-------");
    }  
  
}  
