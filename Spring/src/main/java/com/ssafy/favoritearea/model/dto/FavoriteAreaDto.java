package com.ssafy.favoritearea.model.dto;

public class FavoriteAreaDto {
	private String userId;
	private String dongCode;
	private String addr;
	
	public FavoriteAreaDto() {}

	public FavoriteAreaDto(String userId, String dongCode) {
		this.userId = userId;
		this.dongCode = dongCode;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FavoriteAreaDto [userId=");
		builder.append(userId);
		builder.append(", dongCode=");
		builder.append(dongCode);
		builder.append(", addr=");
		builder.append(addr);
		builder.append("]");
		return builder.toString();
	}

}
