package com.system.shiro.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;

import com.web.manager.user.entity.User;

/**
 * shiro的filter，用于过滤用户
 *
 */
public class ShiroFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		
		//把request和response转为httpservletrequest和httpservletrepsonse
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		//通过session得到当前的用户
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
		if(user != null){
			filterChain.doFilter(httpRequest, httpResponse);
		}else{
			httpResponse.sendRedirect(httpRequest.getServletContext().getContextPath() + "/login.html");
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void destroy() {
		
	}
}
