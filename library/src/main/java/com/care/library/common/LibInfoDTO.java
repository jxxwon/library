package com.care.library.common;

public class LibInfoDTO {
	//도서관 일정 관련 DTO
	private String closedDay;
	private String operatingTime;
	
	public String getClosedDay() {
		return closedDay;
	}
	public void setClosedDay(String closedDay) {
		this.closedDay = closedDay;
	}
	public String getOperatingTime() {
		return operatingTime;
	}
	public void setOperatingTime(String operatingTime) {
		this.operatingTime = operatingTime;
	}
	
}
