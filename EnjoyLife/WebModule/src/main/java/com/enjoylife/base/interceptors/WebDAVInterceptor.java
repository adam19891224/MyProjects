package com.enjoylife.base.interceptors;

import com.enjoylife.utils.BowserUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Adam
 * 2016/7/28
 */
@Component
public class WebDAVInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        String version = BowserUtils.getBowserVersion(request);
        if(version.equalsIgnoreCase("ie6")
                || version.equalsIgnoreCase("ie7")
                || version.equalsIgnoreCase("ie8")
                || version.equalsIgnoreCase("ie9")){
            request.getServletContext().getRequestDispatcher("/hope.html").forward(request, response);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
