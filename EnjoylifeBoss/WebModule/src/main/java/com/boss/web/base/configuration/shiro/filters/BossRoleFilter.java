package com.boss.web.base.configuration.shiro.filters;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * ranmin-zhouyuhong
 * 2016/11/15
 */
public class BossRoleFilter extends AuthorizationFilter {

    private String[] roles;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {
        Subject subject = getSubject(request, response);

        if (roles == null || roles.length == 0) {
            return false;
        }

        for (String role : roles) {
            if (subject.hasRole(role)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 通过string字符串数组注册角色对象
     * @param roleArr
     */
    public BossRoleFilter(String[] roleArr){
        this.roles = roleArr;
    }

    /**
     * 通过string字符串注册角色对象，用半角符号,做分割
     */
    public BossRoleFilter(String roleStr){
        roles = roleStr.split(",");
    }
}
