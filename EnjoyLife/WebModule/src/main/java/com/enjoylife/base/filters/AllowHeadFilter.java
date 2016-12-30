package com.enjoylife.base.filters;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ranmin-zhouyuhong
 * 2016/12/30
 */
@Component
public class AllowHeadFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Methods", "POST, GET");
        response.setHeader("response", "POST, GET");
        filterChain.doFilter(servletRequest, response);
    }

    @Override
    public void destroy() {

    }
}
