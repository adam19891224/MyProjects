package com.boss.web.base.configuration.shiro;

import com.boss.foundation.entity.UserInfo;
import com.boss.foundation.form.LoginForm;
import com.boss.foundation.utils.MD5Utils;
import com.boss.foundation.utils.SessionKeyUtils;
import com.boss.service.login.LoginService;
import com.boss.web.base.configuration.shiro.utils.LoginToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Adam
 * 2016/11/14
 */
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private LoginService loginService;

    /**
     * 用户权限校验方法
     *  此时用户已成功登陆，获取用户的角色，放入shiro的角色检测对象中
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //通过session获取用户的信息
        Session shiroSession = getShiroSession();
        //创建角色信息集合，用于存放当前用户的角色信息，给shiro校验
        List<String> memberRoles = new ArrayList<>();
        if(shiroSession != null){
            UserInfo info = (UserInfo) shiroSession.getAttribute(SessionKeyUtils.SESSION_USER_INFO);
            memberRoles.add(info.getUserRole());
        }
        //创建shrio的角色对象
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //将用户的角色赋给shiro角色对象
        authorizationInfo.addRoles(memberRoles);
        //返回角色
        return authorizationInfo;
    }


    /**
     * 登陆校验方法
     *  根据token中的用户名和密码查询数据库，校验是否有存在的用户，存在，返回shiro权限对象
     *  如果不存在，抛出异常
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //转换为易生活登陆token对象
        LoginToken token = (LoginToken) authenticationToken;
        String userName = token.getUsername();
        String password = String.valueOf(token.getPassword());
        LoginForm form = new LoginForm();
        form.setUserName(userName);
        form.setUserPassword(MD5Utils.getMD5(password));
        UserInfo users = loginService.selectUserInfoByForm(form);
        if(users != null){
            //验证成功，将info放入shiro的session
            this.setInfo2ShiroSession(SessionKeyUtils.SESSION_USER_INFO, users);
            return new SimpleAuthenticationInfo(users, token.getPassword(), users.getUserName());
        }

        throw new RuntimeException("用户名或者密码错误");
    }

    private <T> void setInfo2ShiroSession(String name, T t){
        Subject subject = SecurityUtils.getSubject();
        if(subject != null){
            Session session = subject.getSession();
            if(session != null){
                session.setAttribute(name, t);
            }
        }
    }

    private Session getShiroSession(){
        Subject subject = SecurityUtils.getSubject();
        if(subject != null){
            return subject.getSession();
        }
        return null;
    }
}
