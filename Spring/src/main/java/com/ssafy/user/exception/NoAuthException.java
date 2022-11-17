package com.ssafy.user.exception;

public class NoAuthException extends Exception {
	
	private String message;
	private String url;
	
	public NoAuthException() {
		this.message = "접근 권한이 없습니다.";
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
