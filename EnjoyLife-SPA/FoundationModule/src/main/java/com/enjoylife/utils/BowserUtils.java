package com.enjoylife.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Adam
 * 2016/8/4
 */
public class BowserUtils {

    public static String getBowserVersion(HttpServletRequest request){

        String agent = getRequestAgent(request);

        if (agent != null) {
            if(agent.indexOf("msie 7") > 0){
                return "ie7";
            }else if(agent.indexOf("msie 8") > 0){
                return "ie8";
            }else if(agent.indexOf("msie 9") > 0){
                return "ie9";
            }else if(agent.indexOf("msie 10") > 0){
                return "ie10";
            }else if(agent.indexOf("msie") > 0){
                return "ie";
            }else if(agent.indexOf("opera") > 0){
                return "opera";
            }else if(agent.indexOf("opera") > 0){
                return "opera";
            }else if(agent.indexOf("firefox") > 0){
                return "firefox";
            }else if(agent.indexOf("webkit") > 0){
                return "webkit";
            }else if(agent.indexOf("gecko") > 0 && agent.indexOf("rv:11") > 0){
                return "ie11";
            }else{
                return "others";
            }
        }else{
            return "notbowser";
        }
    }

    private static String getRequestAgent(HttpServletRequest request){

        return StringUtils.isNotNull(request.getHeader("User-Agent")) ?
                request.getHeader("User-Agent").toLowerCase() : null;
    }

}
