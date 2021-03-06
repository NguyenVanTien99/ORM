package com.A101.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CINEMA_ROOM_DETAIL")
public class RoomDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CINEMA_ROOM_DETAIL_ID")
	private int roomDetailId;
	
	@Column(name = "CINEMA_RATE")
	private int roomRate;
	
	@Column(name = "ACTIVE_DATE")
	private Date activeDate;
	
	@Column(name = "ROOM_DECRIPTION")
	private String decription;
	

	public int getRoomDetailId() {
		return roomDetailId;
	}

	public void setRoomDetailId(int roomDetailId) {
		this.roomDetailId = roomDetailId;
	}

	public int getRoomRate() {
		return roomRate;
	}

	public void setRoomRate(int roomRate) {
		this.roomRate = roomRate;
	}

	public Date getActiveDate() {
		return activeDate;
	}

	public void setActiveDate(Date activeDate) {
		this.activeDate = activeDate;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}


	
	
}
