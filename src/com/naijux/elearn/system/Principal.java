package com.naijux.elearn.system;

import java.io.Serializable;

public class Principal implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8587772784712537917L;
	
	private int userId;
	private String username;
	private String phone;
	private String email;
	
	public Principal() {
		
	}
	
	public Principal(User user) {
		this.userId = user.getId();
		this.username = user.getUsername();
		
	}
	
	public Principal(int userId, String username) {
		this.userId = userId;
		this.username = username;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
