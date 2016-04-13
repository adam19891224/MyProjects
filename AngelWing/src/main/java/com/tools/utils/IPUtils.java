package com.tools.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/4
 */
public class IPUtils {

    public static String getClientIp(HttpServletRequest request){
        // 请求的IP
        String ip = request.getHeader("x-forwarded-for");
        if (!StringUtils.isNotNull(ip) || StringUtils.isEqual("unknown", ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (!StringUtils.isNotNull(ip) || StringUtils.isEqual("unknown", ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (!StringUtils.isNotNull(ip) || StringUtils.isEqual("unknown", ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
