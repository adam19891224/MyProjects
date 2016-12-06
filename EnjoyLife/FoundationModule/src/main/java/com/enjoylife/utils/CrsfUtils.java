package com.enjoylife.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * ranmin-zhouyuhong
 * 2016/12/6
 */
public class CrsfUtils {

    public static void createToken(HttpServletRequest request){
        //如果不为空，则证明此时方法有注解，则需要生成token
        String token = UUID.randomUUID().toString();
        //生成前先清空
        cleanToken(request);
        request.getSession().setAttribute(SessionKeyUtils.SESSION_CRSF_TOKEN, token);
    }

    public static boolean checkToken(HttpServletRequest request) {
        //获取客户端发出的token
        String cToken = request.getParameter("ck");
        //获取服务器session的token
        String sToken = (String) request.getSession().getAttribute(SessionKeyUtils.SESSION_CRSF_TOKEN);
        //然后清空
        cleanToken(request);
        return StringUtils.isNotNull(cToken) && StringUtils.isNotNull(sToken) && cToken.equalsIgnoreCase(sToken);
    }

    private static void cleanToken(HttpServletRequest request){
        request.getSession().removeAttribute(SessionKeyUtils.SESSION_CRSF_TOKEN);
    }
}
