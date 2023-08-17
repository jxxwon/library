package com.care.library.reservation;

public class ReserveDTO {
	private String seatId;
	private String userId;
	private String reserveDate;
	private String room;
	public String getReserveDate() {
		return reserveDate;
	}
	public void setReserveDate(String reserveDate) {
		this.reserveDate = reserveDate;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}

	
}
