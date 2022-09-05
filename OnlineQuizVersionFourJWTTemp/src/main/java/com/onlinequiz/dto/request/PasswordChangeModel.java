package com.onlinequiz.dto.request;

public class PasswordChangeModel {
 String oldPassword;
 String newPassword;
public String getOldPassword() {
	return oldPassword;
}

public String getNewPassword() {
	return newPassword;
}

public PasswordChangeModel(String oldPassword, String newPassword) {
	super();
	this.oldPassword = oldPassword;
	this.newPassword = newPassword;
}
 
}
