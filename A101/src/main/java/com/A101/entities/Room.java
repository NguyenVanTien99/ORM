package com.A101.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CINEMA_ROOM")
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CINEMA_ROOM_ID")
	private int roomId;
	
	@Column(name = "CINEMA_ROOM_NAME", columnDefinition = "varchar(255)")
	private String roomName;
	
	@Column(name = "SEAT_QUANTITY")
	private int seatQuantity;
	
	
	public Room() {
		super();
	}



	public Room(String roomName, int seatQuantity) {
		super();
		this.roomName = roomName;
		this.seatQuantity = seatQuantity;
	}

	



	public int getRoomId() {
		return roomId;
	}



	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}



	public String getRoomName() {
		return roomName;
	}



	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}



	public int getSeatQuantity() {
		return seatQuantity;
	}



	public void setSeatQuantity(int seatQuantity) {
		this.seatQuantity = seatQuantity;
	}
	
	
}
