package com.ssafy.aptinfo.model.dto;

public class AptInfoDto {
	private String apartmentName;
	private Long aptCode;
	private String bonbun;
	private String bubun;
	private Integer buildYear;
	private String dong;
	private String dongCode;
	private String eubmyundongCode;
	private String jibun;
	private String landCode;
	private String lat;
	private String lng;
	private String roadName;
	private String roadNameBasementCode;
	private String roadNameBonbun;
	private String roadNameBubun;
	private String roadNameCode;
	private String roadNameSeq;
	private String sigunguCode;
	
	public AptInfoDto() {}

	public AptInfoDto(String apartmentName, Long aptCode, String bonbun, String bubun, Integer buildYear, String dong,
			String dongCode, String eubmyundongCode, String jibun, String landCode, String lat, String lng,
			String roadName, String roadNameBasementCode, String roadNameBonbun, String roadNameBubun,
			String roadNameCode, String roadNameSeq, String sigunguCode) {
		super();
		this.apartmentName = apartmentName;
		this.aptCode = aptCode;
		this.bonbun = bonbun;
		this.bubun = bubun;
		this.buildYear = buildYear;
		this.dong = dong;
		this.dongCode = dongCode;
		this.eubmyundongCode = eubmyundongCode;
		this.jibun = jibun;
		this.landCode = landCode;
		this.lat = lat;
		this.lng = lng;
		this.roadName = roadName;
		this.roadNameBasementCode = roadNameBasementCode;
		this.roadNameBonbun = roadNameBonbun;
		this.roadNameBubun = roadNameBubun;
		this.roadNameCode = roadNameCode;
		this.roadNameSeq = roadNameSeq;
		this.sigunguCode = sigunguCode;
	}

	public String getApartmentName() {
		return apartmentName;
	}

	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}

	public Long getAptCode() {
		return aptCode;
	}

	public void setAptCode(Long aptCode) {
		this.aptCode = aptCode;
	}

	public String getBonbun() {
		return bonbun;
	}

	public void setBonbun(String bonbun) {
		this.bonbun = bonbun;
	}

	public String getBubun() {
		return bubun;
	}

	public void setBubun(String bubun) {
		this.bubun = bubun;
	}

	public Integer getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(Integer buildYear) {
		this.buildYear = buildYear;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	public String getEubmyundongCode() {
		return eubmyundongCode;
	}

	public void setEubmyundongCode(String eubmyundongCode) {
		this.eubmyundongCode = eubmyundongCode;
	}

	public String getJibun() {
		return jibun;
	}

	public void setJibun(String jibun) {
		this.jibun = jibun;
	}

	public String getLandCode() {
		return landCode;
	}

	public void setLandCode(String landCode) {
		this.landCode = landCode;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	public String getRoadNameBasementCode() {
		return roadNameBasementCode;
	}

	public void setRoadNameBasementCode(String roadNameBasementCode) {
		this.roadNameBasementCode = roadNameBasementCode;
	}

	public String getRoadNameBonbun() {
		return roadNameBonbun;
	}

	public void setRoadNameBonbun(String roadNameBonbun) {
		this.roadNameBonbun = roadNameBonbun;
	}

	public String getRoadNameBubun() {
		return roadNameBubun;
	}

	public void setRoadNameBubun(String roadNameBubun) {
		this.roadNameBubun = roadNameBubun;
	}

	public String getRoadNameCode() {
		return roadNameCode;
	}

	public void setRoadNameCode(String roadNameCode) {
		this.roadNameCode = roadNameCode;
	}

	public String getRoadNameSeq() {
		return roadNameSeq;
	}

	public void setRoadNameSeq(String roadNameSeq) {
		this.roadNameSeq = roadNameSeq;
	}

	public String getSigunguCode() {
		return sigunguCode;
	}

	public void setSigunguCode(String sigunguCode) {
		this.sigunguCode = sigunguCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HouseInfoDto [apartmentName=");
		builder.append(apartmentName);
		builder.append(", aptCode=");
		builder.append(aptCode);
		builder.append(", bonbun=");
		builder.append(bonbun);
		builder.append(", bubun=");
		builder.append(bubun);
		builder.append(", buildYear=");
		builder.append(buildYear);
		builder.append(", dong=");
		builder.append(dong);
		builder.append(", dongCode=");
		builder.append(dongCode);
		builder.append(", eubmyundongCode=");
		builder.append(eubmyundongCode);
		builder.append(", jibun=");
		builder.append(jibun);
		builder.append(", landCode=");
		builder.append(landCode);
		builder.append(", lat=");
		builder.append(lat);
		builder.append(", lng=");
		builder.append(lng);
		builder.append(", roadName=");
		builder.append(roadName);
		builder.append(", roadNameBasementCode=");
		builder.append(roadNameBasementCode);
		builder.append(", roadNameBonbun=");
		builder.append(roadNameBonbun);
		builder.append(", roadNameBubun=");
		builder.append(roadNameBubun);
		builder.append(", roadNameCode=");
		builder.append(roadNameCode);
		builder.append(", roadNameSeq=");
		builder.append(roadNameSeq);
		builder.append(", sigunguCode=");
		builder.append(sigunguCode);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}