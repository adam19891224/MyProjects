package com.system.shiro.realm;

import com.tools.utils.ConUtils;
import com.tools.utils.StringUtils;
import com.web.manager.role.entity.Role;
import com.web.manager.role.service.RoleService;
import com.web.manager.user.entity.User;
import com.web.manager.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * shiro会从这个域中得到所有用户的安全信息,然后进行匹配判断
 * 我们继承了AuthorizingRealm这个shiro自带的角色，它是一个抽象类，需要我们自己实现两个方法
 */
public class ShiroRealm extends AuthorizingRealm {

	@Resource
	private RoleService roleService;
	@Resource
	private UserService userService;
	
	/**
	 * 这是赋予角色权限的方法
	 * 此时这个角色肯定是已经经过了登陆验证的，我们就直接取得它的role
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		
		//得到该用户传入的角色,这个值在登录验证方法中传入
		StringBuilder roleName = new StringBuilder();
//		principals.getRealmNames().forEach( e -> { roleName.append(e); });
		principals.getRealmNames().forEach(roleName::append);

		//创建shrio的角色对象
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		
		List<String> shiroList = Arrays.asList(roleName.toString().split(","));
		
		authorizationInfo.addRoles(shiroList);
		//赋予权限
		authorizationInfo.addStringPermission(roleName.toString());
		//返回角色
		return authorizationInfo;
	}

	
	/**
	 * 登陆验证方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		//获取基于用户名和密码的令牌  
        //实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的  
        //两个token的引用都是一样的
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        //根据传过来的token得到用户名和密码
        String username = token.getUsername();
        String password = String.valueOf(token.getPassword());
        String checkCode = token.getHost();
        if(!this.checkCode(checkCode)){
        	throw new UnknownAccountException("checkCode");//抛出验证码异常
        }
        //根据用户名和密码查询用户
        Map<String, Object> map = ConUtils.hashmap();
        map.put("username", username);
        map.put("password", password);
        List<User> userList = userService.selectByMap(map);
        if(ConUtils.isNotNull(userList) && userList.size() == 1){
        	AuthenticationInfo authcInfo;
        	//此时得到唯一用户
        	User user = userList.get(0);
        	//根据该用户得到该用户的角色
        	Role role = roleService.selectRoleByUserId(user.getUserId());
        	//创建shiro的用户对象，放入相关值
        	authcInfo = new SimpleAuthenticationInfo(user.getUserId(), user.getUserPassword().toCharArray(), role.getRoleShiro());
        	//调用setSession方法，把用户传入session
        	this.setInfoToSession("user", user);
        	//返回shiro对象
        	return authcInfo;
        }else{
        	throw new UnknownAccountException("password");//没找到帐号, 抛出shiro指定的异常
        }
	}
	
	
	/**
	 * 把信息放入session中,在其他地方，我们就可以通过httpsession.getattribute来得到这个session对象了
	 */
	private <T> void setInfoToSession(String name, T t){
		//得到当前登录对象
		Subject subject = SecurityUtils.getSubject();
		if(subject != null){
			Session session = subject.getSession();
			if(session != null){
				session.setAttribute(name, t);
			}
		}
	}
	
	private boolean checkCode(String code){
		String systemCode = (String) SecurityUtils.getSubject().getSession().getAttribute("code");
		return StringUtils.isEqual(code, systemCode);
	}
	
}
