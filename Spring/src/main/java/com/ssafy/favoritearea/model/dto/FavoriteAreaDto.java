package com.ssafy.favoritearea.model.dto;

public class FavoriteAreaDto {
	private String userId;
	private String dongCode;
	public FavoriteAreaDto() {
		super();
	}
	public FavoriteAreaDto(String userId, String dongCode) {
		super();
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FavoriteAreaDto [userId=");
		builder.append(userId);
		builder.append(", dongCode=");
		builder.append(dongCode);
		builder.append("]");
		return builder.toString();
	}	
}
