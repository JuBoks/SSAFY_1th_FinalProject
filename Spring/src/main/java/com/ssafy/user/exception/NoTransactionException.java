package com.ssafy.user.exception;

public class NoTransactionException extends Exception {
	
	private String message;
	private String url;
	
	public NoTransactionException(String url) {
		this.message = "Transaction 결과가 0으로 새로운 업데이트가 없습니다.";
		this.url = url;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
	
	public String getUrl() {
		return url;
	}
}
