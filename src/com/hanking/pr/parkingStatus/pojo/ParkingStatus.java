package com.hanking.pr.parkingStatus.pojo;

import java.util.Date;

public class ParkingStatus {

	private int id;
	
	private String parkId;
	
	private String numberId;
	
	private String carStatus;
	
	private String terminalStatus;
	
	private Date startingDate;
	
	private Date endingDate;
	
	private String isDamaged;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getParkId() {
		return parkId;
	}

	public void setParkId(String parkId) {
		this.parkId = parkId;
	}

	public String getNumberId() {
		return numberId;
	}

	public void setNumberId(String numberId) {
		this.numberId = numberId;
	}

	public String getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(String carStatus) {
		this.carStatus = carStatus;
	}

	public String getTerminalStatus() {
		return terminalStatus;
	}

	public void setTerminalStatus(String terminalStatus) {
		this.terminalStatus = terminalStatus;
	}

	public Date getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}

	public Date getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}

	public String getIsDamaged() {
		return isDamaged;
	}

	public void setIsDamaged(String isDamaged) {
		this.isDamaged = isDamaged;
	}
	
	
}
