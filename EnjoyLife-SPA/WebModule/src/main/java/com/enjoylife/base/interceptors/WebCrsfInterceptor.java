package com.enjoylife.base.interceptors;

import com.enjoylife.base.annotations.DoCrsf;
import com.enjoylife.base.annotations.ToCrsf;
import com.enjoylife.utils.CrsfUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

import static com.enjoylife.utils.CrsfUtils.createToken;

/**
 * ranmin-zhouyuhong
 * 2016/12/6
 */
@Component
public class WebCrsfInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //强转为handlermethon对象
        HandlerMethod handlerMethod = (HandlerMethod) o;
        //得到当前执行的方法
        Method method = handlerMethod.getMethod();
        //获取当前方法是否有tocrsf注解
        ToCrsf toCrsf = method.getAnnotation(ToCrsf.class);
        if (toCrsf != null) {
            createToken(httpServletRequest);
            return true;
        }
        //获取当前方法是否有docrsf注解
        DoCrsf doCrsf = method.getAnnotation(DoCrsf.class);
        //如果没通过，则返回错误
        return doCrsf == null || CrsfUtils.checkToken(httpServletRequest);
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
