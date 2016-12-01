package com.boss.web.base.configuration.shiro;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.servlet.ShiroHttpServletResponse;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

/**
 * 目的：移除shiro的url中自带的jsessoinid参数
 * ranmin-zhouyuhong
 * 2016/11/15
 */
class ShiroHttpResponse extends ShiroHttpServletResponse {

    //覆盖父类的toEncoded，将url中的jsessionid去掉
    @Override
    protected String toEncoded(String url, String sessionId) {
        if ((url == null) || (sessionId == null))
            return (url);
        String path = url;
        String query = "";
        String anchor = "";
        int question = url.indexOf('?');
        if (question >= 0) {
            path = url.substring(0, question);
            query = url.substring(question);
        }
        int pound = path.indexOf('#');
        if (pound >= 0) {
            anchor = path.substring(pound);
            path = path.substring(0, pound);
        }
        //重写toEncoded方法，shiro的response中，toEncode方法有下面的代码，所以导致
        //shiro的url上带了jsessionid参数，这里易生活子类将这段代码去掉，url中就不会有jsessionid了
//        if (sb.length() > 0) { // session id param can't be first.
//            sb.append(";");
//            sb.append(DEFAULT_SESSION_ID_PARAMETER_NAME);
//            sb.append("=");
//            sb.append(sessionId);
//        }
        return (path + anchor + query);
    }

    ShiroHttpResponse(HttpServletResponse wrapped, ServletContext context, ShiroHttpServletRequest request) {
        super(wrapped, context, request);
    }
}
