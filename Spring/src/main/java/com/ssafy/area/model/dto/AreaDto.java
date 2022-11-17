package com.ssafy.area.model.dto;

public class AreaDto {

	private String dongCode;
	private String sidoName;
	private String gugunName;
	private String dongName;
	
	public AreaDto() {}
	
	public AreaDto(String dongCode, String sidoName, String gugunName, String dongName) {
		this.dongCode = dongCode;
		this.sidoName = sidoName;
		this.gugunName = gugunName;
		this.dongName = dongName;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	public String getSidoName() {
		return sidoName;
	}

	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	public String getGugunName() {
		return gugunName;
	}

	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}

	public String getDongName() {
		return dongName;
	}

	public void setDongName(String dongName) {
		this.dongName = dongName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AreaDto [dongCode=");
		builder.append(dongCode);
		builder.append(", sidoName=");
		builder.append(sidoName);
		builder.append(", gugunName=");
		builder.append(gugunName);
		builder.append(", dongName=");
		builder.append(dongName);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
