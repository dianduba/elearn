package cn.dianduba.elearn.system;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class UserUtils {
	
	public static String getUsername() {
		
		return getPrincipal().getUsername();
	}
	
	public static int getUserId() {
		
		return getPrincipal().getUserId();
	}
	
	public static boolean isAuthenticated() {
		Subject subject = SecurityUtils.getSubject();
		if (subject == null)
			return false;
		
		return subject.isAuthenticated();
	}
	
	public static Principal getPrincipal() {
		Subject subject = SecurityUtils.getSubject();
		if (subject == null)
			return null;
		
		return (Principal)subject.getPrincipal();
		
	}
	
	public static boolean isUser() {
		return getPrincipal() != null;
	}
}
