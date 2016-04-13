package com.system.aop;

import com.tools.utils.IPUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogsAspect {

	//控制器日志
	private Logger logger = Logger.getLogger("ControllerLogger");
	
	/**
	 * 定义切入点
	 */
	@Pointcut("@annotation(com.system.annotation.ControllerLogs)")
	public void controllerPoint() {

	}
	
	/**
	 * 前置通知 凡是有controllerPoint()切入点的代理方法方法，
	 * 在执行该方法之前都会进入这个通知中
	 * @param joinPoint
	 *            切点
	 */
	@Before("controllerPoint()")
	public void doBefore(JoinPoint joinPoint) {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		// 请求方法
		String method = joinPoint.getTarget().getClass().getName() + "."
				+ joinPoint.getSignature().getName() + "()";
		// 拼接结果字符串
		String res = "IP:  " + IPUtils.getClientIp(request) + "  访问方法:  " + method;
		//调用controller日志工具输出日志
		logger.info(res);
	}
}
