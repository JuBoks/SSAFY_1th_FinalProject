package com.ssafy.yeonlipinfo.model.dto;

public class YeonlipInfoDto {
	
	private Integer yeonlipCode;
	private String yeonlipName;
	private String buildYear;
	private String roadName;
	private String bunji;
	private String sidoCode;
	private String gugunCode;
	private String dongCode;
	
	public YeonlipInfoDto() {}

	public YeonlipInfoDto(String yeonlipName, String buildYear, String roadName, String bunji, String dongCode) {
		this.yeonlipName = yeonlipName;
		this.buildYear = buildYear;
		this.roadName = roadName;
		this.bunji = bunji;
		this.dongCode = dongCode;
	}

	public YeonlipInfoDto(Integer yeonlipCode, String yeonlipName, String buildYear, String roadName, String bunji,
			String sidoCode, String gugunCode, String dongCode) {
		this(yeonlipName, buildYear, roadName, bunji, dongCode);
		this.yeonlipCode = yeonlipCode;
		this.sidoCode = sidoCode;
		this.gugunCode = gugunCode;
	}

	public Integer getYeonlipCode() {
		return yeonlipCode;
	}

	public void setYeonlipCode(Integer yeonlipCode) {
		this.yeonlipCode = yeonlipCode;
	}

	public String getYeonlipName() {
		return yeonlipName;
	}

	public void setYeonlipName(String yeonlipName) {
		this.yeonlipName = yeonlipName;
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
		builder.append("YeonlipInfoDto [yeonlipCode=");
		builder.append(yeonlipCode);
		builder.append(", yeonlipName=");
		builder.append(yeonlipName);
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
