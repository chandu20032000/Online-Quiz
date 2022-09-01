package com.onlinequiz.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignupRequest {
	  @NotBlank
	  @Size(min = 3, max = 20)
	  private String username;

	  @NotBlank
	  @Size(max = 50)
	  @Email
	  private String email;
	  @NotBlank
	  @Size(max = 50)
	  private String role;

	  @NotBlank
	  @Size(min = 6, max = 40)
	  private String password;
	  
	  @NotBlank
	  @Size(min = 1, max = 40)
	  private String address;
	  
	  @NotBlank
	  @Size(min = 10, max = 10)
	  private String mobile;

	  public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUsername() {
	    return username;
	  }

	  public void setUsername(String username) {
	    this.username = username;
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

	  public String getRole() {
	    return this.role;
	  }

	  public void setRole(String role) {
	    this.role = role;
	  }

	public SignupRequest(@NotBlank @Size(min = 3, max = 20) String username,
			@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 50) String role,
			@NotBlank @Size(min = 6, max = 40) String password, @NotBlank @Size(min = 1, max = 40) String address,
			@NotBlank @Size(min = 10, max = 10) String mobile) {
		super();
		this.username = username;
		this.email = email;
		this.role = role;
		this.password = password;
		this.address = address;
		this.mobile = mobile;
	}
	  
	}
