package com.ssafy.yeonlipdeal.model.dto;

public class YeonlipDealDto {
	
	private Long no;
	private String dealAmount;
	private int dealYear;
	private int dealMonth;
	private int dealDay;
	private String area;
	private String landArea;
	private String floor;
	private String cancelDealDay;
	private int yeonlipCode;
	
	public YeonlipDealDto() {}

	public YeonlipDealDto(String dealAmount, int dealYear, int dealMonth, int dealDay, String area, String landArea,
			String floor, String cancelDealDay, int yeonlipCode) {
		this.dealAmount = dealAmount;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.dealDay = dealDay;
		this.area = area;
		this.landArea = landArea;
		this.floor = floor;
		this.cancelDealDay = cancelDealDay;
		this.yeonlipCode = yeonlipCode;
	}

	public YeonlipDealDto(Long no, String dealAmount, int dealYear, int dealMonth, int dealDay, String area,
			String landArea, String floor, String cancelDealDay, int yeonlipCode) {
		this(dealAmount, dealYear, dealMonth, dealDay, area, landArea, floor, cancelDealDay, yeonlipCode);
		this.no = no;
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

	public int getDealYear() {
		return dealYear;
	}

	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}

	public int getDealMonth() {
		return dealMonth;
	}

	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}

	public int getDealDay() {
		return dealDay;
	}

	public void setDealDay(int dealDay) {
		this.dealDay = dealDay;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getLandArea() {
		return landArea;
	}

	public void setLandArea(String landArea) {
		this.landArea = landArea;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getCancelDealDay() {
		return cancelDealDay;
	}

	public void setCancelDealDay(String cancelDealDay) {
		this.cancelDealDay = cancelDealDay;
	}

	public int getYeonlipCode() {
		return yeonlipCode;
	}

	public void setYeonlipCode(int yeonlipCode) {
		this.yeonlipCode = yeonlipCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("YeonlipDealDto [no=");
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
		builder.append(", landArea=");
		builder.append(landArea);
		builder.append(", floor=");
		builder.append(floor);
		builder.append(", cancelDealDay=");
		builder.append(cancelDealDay);
		builder.append(", yeonlipCode=");
		builder.append(yeonlipCode);
		builder.append("]");
		return builder.toString();
	}
	
	
}
