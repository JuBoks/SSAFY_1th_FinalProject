package com.ssafy.user.model.dto;

public class TmpNumDto {
	
	private String userId;
	private String tmpnum;
	
	public TmpNumDto() {}
	
	public TmpNumDto(String userId, String tmpnum) {
		this.userId = userId;
		this.tmpnum = tmpnum;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTmpnum() {
		return tmpnum;
	}
	public void setTmpnum(String tmpnum) {
		this.tmpnum = tmpnum;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TmpNumDto [userId=");
		builder.append(userId);
		builder.append(", tmpnum=");
		builder.append(tmpnum);
		builder.append("]");
		return builder.toString();
	}
	
}
