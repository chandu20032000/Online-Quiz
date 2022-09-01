package com.onlinequiz.dto.response;

import java.util.List;

public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private String id;

  private String username;
  private String email;
  private String mobile;
  private String address;
  private List<String> roles;
  

  public JwtResponse(String token, String id, String username, String email, String mobile, String address,
		List<String> roles) {
	super();
	this.token = token;
	this.id = id;
	this.username = username;
	this.email = email;
	this.mobile = mobile;
	this.address = address;
	this.roles = roles;
}


  public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }

 
  public String getToken() {
	return token;
}


public void setToken(String token) {
	this.token = token;
}


public String getType() {
	return type;
}


public void setType(String type) {
	this.type = type;
}


public String getId() {
	return id;
}


public void setId(String id) {
	this.id = id;
}


public String getMobile() {
	return mobile;
}


public void setMobile(String mobile) {
	this.mobile = mobile;
}


public String getAddress() {
	return address;
}


public void setAddress(String address) {
	this.address = address;
}


public void setRoles(List<String> roles) {
	this.roles = roles;
}


public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<String> getRoles() {
    return roles;
  }
}
