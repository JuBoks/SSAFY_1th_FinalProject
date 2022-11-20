package com.ssafy.aptinfo.model.dto;

public class AptInfoDto {
	
	private Integer aptCode;
	private String apartmentName;
	private String buildYear;
	private String roadName;
	private String bunji;
	private String sidoCode;
	private String gugunCode;
	private String dongCode;
	
	public AptInfoDto() { }
	

	public AptInfoDto(String apartmentName, String buildYear, String roadName, String bunji, String dongCode) {
		this.apartmentName = apartmentName;
		this.buildYear = buildYear;
		this.roadName = roadName;
		this.bunji = bunji;
		this.dongCode = dongCode;
	}

	public AptInfoDto(Integer aptCode, String apartmentName, String buildYear, String roadName, String bunji,
			String sidoCode, String gugunCode, String dongCode) {
		this(apartmentName, buildYear, roadName, bunji, dongCode);
		this.aptCode = aptCode;
		this.sidoCode = sidoCode;
		this.gugunCode = gugunCode;
	}
	
	public Integer getAptCode() {
		return aptCode;
	}
	public void setAptCode(Integer aptCode) {
		this.aptCode = aptCode;
	}
	public String getApartmentName() {
		return apartmentName;
	}
	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}
	public String getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	public String getBunji() {
		return bunji;
	}
	public void setBunji(String bunji) {
		this.bunji = bunji;
	}
	public String getSidoCode() {
		return sidoCode;
	}
	public void setSidoCode(String sidoCode) {
		this.sidoCode = sidoCode;
	}
	public String getGugunCode() {
		return gugunCode;
	}
	public void setGugunCode(String gugunCode) {
		this.gugunCode = gugunCode;
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
		builder.append("AptInfoDto [aptCode=");
		builder.append(aptCode);
		builder.append(", apartmentName=");
		builder.append(apartmentName);
		builder.append(", buildYear=");
		builder.append(buildYear);
		builder.append(", roadName=");
		builder.append(roadName);
		builder.append(", bunji=");
		builder.append(bunji);
		builder.append(", sidoCode=");
		builder.append(sidoCode);
		builder.append(", gugunCode=");
		builder.append(gugunCode);
		builder.append(", dongCode=");
		builder.append(dongCode);
		builder.append("]");
		return builder.toString();
	}
	
	
}