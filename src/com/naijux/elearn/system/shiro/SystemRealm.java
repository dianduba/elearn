package com.naijux.elearn.system.shiro;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.naijux.elearn.system.Permission;
import com.naijux.elearn.system.Principal;
import com.naijux.elearn.system.Role;
import com.naijux.elearn.system.User;
import com.naijux.elearn.system.dao.UserDao;


//@Service
public class SystemRealm extends AuthorizingRealm {
	
	@Resource
	private UserDao userDao;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		Principal principal = (Principal)getAvailablePrincipal(principalCollection);
		String username = principal.getUsername();
		
		SimpleAuthorizationInfo authorizationInfo = null;  
		User user = userDao.getUserByUsername(username);
		if (user != null) {
			authorizationInfo = new SimpleAuthorizationInfo();  
			
			List<Role> roles = userDao.getRolesOfUser(user.getId());
			for (Role role : roles) {
				authorizationInfo.addRole(role.getName());
			}
			
			List<Permission> permissions = userDao.getPermissionsOfUser(user.getId());
			for (Permission permission : permissions) {
				authorizationInfo.addStringPermission(permission.getName());
			}
			
		}
		
		return authorizationInfo;
		
	}
	

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authToken) throws AuthenticationException {
		
		UsernamePasswordToken token = (UsernamePasswordToken) authToken;
		
		String username = token.getUsername();
		String password = new String(token.getPassword()); 
		
		User user = userDao.getUserByUsername(username);
		
		if(user == null) {  
			throw new UnknownAccountException(); //如果用户名错误  
		}  
		
		if (!user.getPassword().equals(password)) {
			throw new IncorrectCredentialsException(); 
		}

		
		return new SimpleAuthenticationInfo(new Principal(user), password, getName());
	} 


}
