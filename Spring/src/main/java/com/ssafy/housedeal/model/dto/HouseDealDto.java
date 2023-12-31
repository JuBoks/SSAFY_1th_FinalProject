package com.ssafy.housedeal.model.dto;

public class HouseDealDto {

	private Long no;
	private String dealAmount;
	private Integer dealYear;
	private Integer dealMonth;
	private Integer dealDay;
	private String area;
	private String floor;
	private String cancelDealType;
	private Long aptCode;
	
	public HouseDealDto() {}
	
	public HouseDealDto(Long no, String dealAmount, Integer dealYear, Integer dealMonth, Integer dealDay, String area,
			String floor, String cancelDealType, Long aptCode) {
		this.no = no;
		this.dealAmount = dealAmount;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.dealDay = dealDay;
		this.area = area;
		this.floor = floor;
		this.cancelDealType = cancelDealType;
		this.aptCode = aptCode;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public Integer getDealYear() {
		return dealYear;
	}

	public void setDealYear(Integer dealYear) {
		this.dealYear = dealYear;
	}

	public Integer getDealMonth() {
		return dealMonth;
	}

	public void setDealMonth(Integer dealMonth) {
		this.dealMonth = dealMonth;
	}

	public Integer getDealDay() {
		return dealDay;
	}

	public void setDealDay(Integer dealDay) {
		this.dealDay = dealDay;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getCancelDealType() {
		return cancelDealType;
	}

	public void setCancelDealType(String cancelDealType) {
		this.cancelDealType = cancelDealType;
	}

	public Long getAptCode() {
		return aptCode;
	}

	public void setAptCode(Long aptCode) {
		this.aptCode = aptCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HouseDealDto [no=");
		builder.append(no);
		builder.append(", dealAmount=");
		builder.append(dealAmount);
		builder.append(", dealYear=");
		builder.append(dealYear);
		builder.append(", dealMonth=");
		builder.append(dealMonth);
		builder.append(", dealDay=");
		builder.append(dealDay);
		builder.append(", area=");
		builder.append(area);
		builder.append(", floor=");
		builder.append(floor);
		builder.append(", cancelDealType=");
		builder.append(cancelDealType);
		builder.append(", aptCode=");
		builder.append(aptCode);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
