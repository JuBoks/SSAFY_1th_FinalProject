package com.ssafy.user.model.dto;

public class UserDto {
	
	private String userId;
	private String userPwd;
	private String userName;
	private String userAddr;
	private String userPhone;
	private Integer userAuth;
	private String token;
	
	
	public UserDto() {}


	public UserDto(String userId, String userPwd, String userName, String userAddr, String userPhone, Integer userAuth,
			String token) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.userAddr = userAddr;
		this.userPhone = userPhone;
		this.userAuth = userAuth;
		this.token = token;
	}

	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserPwd() {
		return userPwd;
	}


	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserAddr() {
		return userAddr;
	}


	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}


	public String getUserPhone() {
		return userPhone;
	}


	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}


	public Integer getUserAuth() {
		return userAuth;
	}


	public void setUserAuth(Integer userAuth) {
		this.userAuth = userAuth;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserDto [userId=");
		builder.append(userId);
		builder.append(", userPwd=");
		builder.append(userPwd);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", userAddr=");
		builder.append(userAddr);
		builder.append(", userPhone=");
		builder.append(userPhone);
		builder.append(", userAuth=");
		builder.append(userAuth);
		builder.append(", token=");
		builder.append(token);
		builder.append("]");
		return builder.toString();
	}

	
	
	
}
