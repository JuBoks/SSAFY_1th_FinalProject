package com.ssafy.aptdeal.model.dto;

public class AptDealDto {

	private Long no;
	private String dealAmount;
	private int dealYear;
	private int dealMonth;
	private int dealDay;
	private String area;
	private String floor;
	private String cancelDealDay;
	private int aptCode;
	
	private String dealAvg;
	
	public AptDealDto() {}
	
	public AptDealDto(String dealAmount, int dealYear, int dealMonth, int dealDay, String area, String floor,
			String cancelDealDay, int aptCode) {
		this.dealAmount = dealAmount;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.dealDay = dealDay;
		this.area = area;
		this.floor = floor;
		this.cancelDealDay = cancelDealDay;
		this.aptCode = aptCode;
	}

	public AptDealDto(Long no, String dealAmount, int dealYear, int dealMonth, int dealDay, String area, String floor,
			String cancelDealDay, int aptCode) {
		this(dealAmount, dealYear, dealMonth, dealDay, area, floor, cancelDealDay, aptCode);
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
	public int getAptCode() {
		return aptCode;
	}
	public void setAptCode(int aptCode) {
		this.aptCode = aptCode;
	}
	public String getDealAvg() {
		return dealAvg;
	}
	public void setDealAvg(String dealAvg) {
		this.dealAvg = dealAvg;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AptDealDto [no=");
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
		builder.append(", cancelDealDay=");
		builder.append(cancelDealDay);
		builder.append(", aptCode=");
		builder.append(aptCode);
		builder.append(", dealAvg=");
		builder.append(dealAvg);
		builder.append("]");
		return builder.toString();
	}

}
