package com.onlinequiz.dto.request;

public class UserUpdate {
	String mobile;
	String address;
	String password;
	String email;
	
	public UserUpdate(String mobile, String address, String password, String email) {
		super();
		this.mobile = mobile;
		this.address = address;
		this.password = password;
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	
	public String getAddress() {
		return address;
	}
	
}
