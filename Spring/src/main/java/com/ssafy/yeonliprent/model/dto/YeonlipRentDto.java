package com.ssafy.yeonliprent.model.dto;

public class YeonlipRentDto {
	
	private Long no;
	private String type;
	private Integer deposit;
	private String monthlyRent;
	private Integer dealYear;
	private Integer dealMonth;
	private Integer dealDay;
	private String area;
	private String floor;
	private Integer yeonlipCode;
	
	public YeonlipRentDto() {}
	
	public YeonlipRentDto(String type, Integer deposit, String monthlyRent, Integer dealYear, Integer dealMonth,
			Integer dealDay, String area, String floor, Integer yeonlipCode) {
		this.type = type;
		this.deposit = deposit;
		this.monthlyRent = monthlyRent;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.dealDay = dealDay;
		this.area = area;
		this.floor = floor;
		this.yeonlipCode = yeonlipCode;
	}

	public YeonlipRentDto(Long no, String type, Integer deposit, String monthlyRent, Integer dealYear,
			Integer dealMonth, Integer dealDay, String area, String floor, Integer yeonlipCode) {
		this(type, deposit, monthlyRent, dealYear, dealMonth, dealDay, area, floor, yeonlipCode);
		this.no = no;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getDeposit() {
		return deposit;
	}

	public void setDeposit(Integer deposit) {
		this.deposit = deposit;
	}

	public String getMonthlyRent() {
		return monthlyRent;
	}

	public void setMonthlyRent(String monthlyRent) {
		this.monthlyRent = monthlyRent;
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

	public Integer getYeonlipCode() {
		return yeonlipCode;
	}

	public void setYeonlipCode(Integer yeonlipCode) {
		this.yeonlipCode = yeonlipCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("YeonlipRentDto [no=");
		builder.append(no);
		builder.append(", type=");
		builder.append(type);
		builder.append(", deposit=");
		builder.append(deposit);
		builder.append(", monthlyRent=");
		builder.append(monthlyRent);
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
		builder.append(", yeonlipCode=");
		builder.append(yeonlipCode);
		builder.append("]");
		return builder.toString();
	}
	
}
