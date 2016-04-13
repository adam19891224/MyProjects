package com.system.interceptors;

import com.system.annotation.SubmitToken;
import com.tools.utils.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * 防止重复提交拦截器
 * Created by IntelliJ IDEA
 * User: adam
 * Date: 2015/11/18
 */
public class RepeatSubmitInterceptor extends HandlerInterceptorAdapter {

    //创建controller指定的日志生成器
    protected Logger logger = Logger.getLogger("InterceptorLogger");

    //实现拦截器前驱方法，即进入controller之前做验证
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //验证当前的handler是否是方法
        if(handler instanceof HandlerMethod){
            //如果是，就强转为handlermethod
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();//通过handler得到method
            //通过method得到注解
            SubmitToken token = method.getAnnotation(SubmitToken.class);
            if(token != null){
                //得到注解的save方法的值，用于判断是否需要生成一个token放入session中
                boolean needSave = token.save();
                if(needSave){
                    //如果需要保存一个token，就调用uuid来生成一个token
                    SecurityUtils.getSubject().getSession().setAttribute("token", UUID.randomUUID().toString());
                }
                //得到注解的remove方法的值，用于判断是否需要删除现有的token
                boolean needRemove = token.remove();
                if(needRemove){
                    //如果此时needRemove为true，说明该方法需要验证重复提交，就调用下面的方法
                    if(this.isRepeatSubmit(request)){
                        //如果验证重复提交为true，则表示此时是重复提交，则返回fasle, 同时给用户返回响应信息
                        this.responseInfo(response);
                        return false;
                    }
                    //如果通过，则证明此时是第一次提交，则删除session的token，并且放行
                    SecurityUtils.getSubject().getSession().removeAttribute("token");
                }
            }
        }
        return super.preHandle(request, response, handler);
    }

    //验证是否重复提交方法
    private boolean isRepeatSubmit(HttpServletRequest request){
        //首先获取session的token
        String sessionToken = (String) SecurityUtils.getSubject().getSession().getAttribute("token");
        if(!StringUtils.isNotNull(sessionToken)){
            //说明此时session中的token已经被删除，则证明此时是重复提交
            return true;
        }
        //然后获取客户端的token
        String clientToken = request.getParameter("token");
        if(!StringUtils.isNotNull(clientToken)){
            //说明此时客户端的token为空，则此时客户端没有生成token,直接返回true
            return true;
        }
        //最后判断客户端的token是否和服务端的token相等
        if(!StringUtils.isEqual(sessionToken, clientToken)){
            //如果不相等，则证明数据不对，直接返回true
            return true;
        }
        //如果上面所有条件都不满足，则证明此时是第一次提交，则返回false
        return false;
    }

    //如果验证是重复提交，则返回一个流文本给客户端
    private void responseInfo(HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
        //得到response的writer对象
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.println("你已经提交过数据，请勿重复提交！");
        } catch (IOException e) {
            logger.error(" 防止重复提交拦截器发送异常，原因是： " + e);
        } finally {
            if(out != null)
                out.close();
        }
    }
}
