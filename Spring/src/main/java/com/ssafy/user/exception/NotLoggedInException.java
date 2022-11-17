package com.ssafy.user.exception;

public class NotLoggedInException extends Exception {

	private String message;
	private String url;
	
	public NotLoggedInException() {
		this.message = "로그인이 필요한 서비스입니다.";
		this.url = "/index.jsp";
	}

	@Override
	public String getMessage() {
		return this.message;
	}
	
	public String getUrl() {
		return url;
	}
	
}
