package com.ssafy.apt.model.dto;

public class AptDto {

	private String aptCode;			// 아파트 코드
	private String aptName;			// 아파트 이름
	private String aptDealAmount;	// 아파트 거래금액
	private String aptArea;			// 아파트 면적
	private Integer aptBuildYear;	// 아파트 건축년도
	private Integer aptDealYear;		// 아파트 거래년도
	private Integer aptDealMonth;	// 아파트 거래월
	private Integer aptDealDay;		// 아파트 거래일
	private String aptSiGunguCode;	// 아파트 시군구코드
	private String aptRoadName;		// 아파트 도로명
	private String aptJibun;		// 아파트 지번
	private String aptSidoName; 	// 아파트 시도이름
	private String aptGunguName; 	// 아파트 구군이름
	private String aptDongCode;		// 아파트 동 코드
	private String aptDongName;		// 아파트 동 이름
	private String aptLat;			// 아파트 위도
	private String aptLng;			// 아파트 경도
	
	private String aptAddr; 		// 아파트 주소
	private String aptDealDate; 	// 아파트 거래일자
	
	public AptDto() {}

	public AptDto(String aptCode, String aptName, String aptDealAmount, String aptArea, Integer aptBuildYear,
			Integer aptDealYear, Integer aptDealMonth, Integer aptDealDay, String aptSiGunguCode, String aptRoadName,
			String aptJibun, String aptSidoName, String aptGunguName, String aptDongCode, String aptLat, String aptLng,
			String aptAddr, String aptDealDate) {
		super();
		this.aptCode = aptCode;
		this.aptName = aptName;
		this.aptDealAmount = aptDealAmount;
		this.aptArea = aptArea;
		this.aptBuildYear = aptBuildYear;
		this.aptDealYear = aptDealYear;
		this.aptDealMonth = aptDealMonth;
		this.aptDealDay = aptDealDay;
		this.aptSiGunguCode = aptSiGunguCode;
		this.aptRoadName = aptRoadName;
		this.aptJibun = aptJibun;
		this.aptSidoName = aptSidoName;
		this.aptGunguName = aptGunguName;
		this.aptDongCode = aptDongCode;
		this.aptLat = aptLat;
		this.aptLng = aptLng;
		this.aptAddr = aptAddr;
		this.aptDealDate = aptDealDate;
	}

	public String getAptCode() {
		return aptCode;
	}

	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public String getAptDealAmount() {
		return aptDealAmount;
	}

	public void setAptDealAmount(String aptDealAmount) {
		this.aptDealAmount = aptDealAmount;
	}

	public String getAptArea() {
		return aptArea;
	}

	public void setAptArea(String aptArea) {
		this.aptArea = aptArea;
	}

	public Integer getAptBuildYear() {
		return aptBuildYear;
	}

	public void setAptBuildYear(Integer aptBuildYear) {
		this.aptBuildYear = aptBuildYear;
	}

	public Integer getAptDealYear() {
		return aptDealYear;
	}

	public void setAptDealYear(Integer aptDealYear) {
		this.aptDealYear = aptDealYear;
	}

	public Integer getAptDealMonth() {
		return aptDealMonth;
	}

	public void setAptDealMonth(Integer aptDealMonth) {
		this.aptDealMonth = aptDealMonth;
	}

	public Integer getAptDealDay() {
		return aptDealDay;
	}

	public void setAptDealDay(Integer aptDealDay) {
		this.aptDealDay = aptDealDay;
	}

	public String getAptSiGunguCode() {
		return aptSiGunguCode;
	}

	public void setAptSiGunguCode(String aptSiGunguCode) {
		this.aptSiGunguCode = aptSiGunguCode;
	}

	public String getAptRoadName() {
		return aptRoadName;
	}

	public void setAptRoadName(String aptRoadName) {
		this.aptRoadName = aptRoadName;
	}

	public String getAptJibun() {
		return aptJibun;
	}

	public void setAptJibun(String aptJibun) {
		this.aptJibun = aptJibun;
	}

	public String getAptSidoName() {
		return aptSidoName;
	}

	public void setAptSidoName(String aptSidoName) {
		this.aptSidoName = aptSidoName;
	}

	public String getAptGunguName() {
		return aptGunguName;
	}

	public void setAptGunguName(String aptGunguName) {
		this.aptGunguName = aptGunguName;
	}

	public String getAptDongCode() {
		return aptDongCode;
	}

	public void setAptDongCode(String aptDongCode) {
		this.aptDongCode = aptDongCode;
	}

	public String getAptLat() {
		return aptLat;
	}

	public void setAptLat(String aptLat) {
		this.aptLat = aptLat;
	}

	public String getAptLng() {
		return aptLng;
	}

	public void setAptLng(String aptLng) {
		this.aptLng = aptLng;
	}

	public String getAptAddr() {
		return aptAddr;
	}

	public void setAptAddr() {
		StringBuilder sb = new StringBuilder();
		sb.append(aptSidoName + " ");
		sb.append(aptGunguName + " ");
		sb.append(aptDongName + " ");
		sb.append(aptJibun);
		
		this.aptAddr = sb.toString();
	}

	public String getAptDealDate() {
		return aptDealDate;
	}

	public void setAptDealDate(String aptDealDate) {
		this.aptDealDate = aptDealDate;
	}
	
	public String getAptDongName() {
		return aptDongName;
	}

	public void setAptDongName(String aptDongName) {
		this.aptDongName = aptDongName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AptDto [aptCode=");
		builder.append(aptCode);
		builder.append(", aptName=");
		builder.append(aptName);
		builder.append(", aptDealAmount=");
		builder.append(aptDealAmount);
		builder.append(", aptArea=");
		builder.append(aptArea);
		builder.append(", aptBuildYear=");
		builder.append(aptBuildYear);
		builder.append(", aptDealYear=");
		builder.append(aptDealYear);
		builder.append(", aptDealMonth=");
		builder.append(aptDealMonth);
		builder.append(", aptDealDay=");
		builder.append(aptDealDay);
		builder.append(", aptSiGunguCode=");
		builder.append(aptSiGunguCode);
		builder.append(", aptRoadName=");
		builder.append(aptRoadName);
		builder.append(", aptJibun=");
		builder.append(aptJibun);
		builder.append(", aptSidoName=");
		builder.append(aptSidoName);
		builder.append(", aptGunguName=");
		builder.append(aptGunguName);
		builder.append(", aptDongCode=");
		builder.append(aptDongCode);
		builder.append(", aptDongName=");
		builder.append(aptDongName);
		builder.append(", aptLat=");
		builder.append(aptLat);
		builder.append(", aptLng=");
		builder.append(aptLng);
		builder.append(", aptAddr=");
		builder.append(aptAddr);
		builder.append(", aptDealDate=");
		builder.append(aptDealDate);
		builder.append("]");
		return builder.toString();
	}

	
	
	
	
	
}
