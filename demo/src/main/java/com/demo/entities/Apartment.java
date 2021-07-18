package com.demo.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Apartment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;

	@Column(name = "num_bedroom")
	private String bedroom;

	@Column(name = "door_direction")
	private String door;

	@Column(name = "price")
	private String price;

	@Column(name = "status")
	private String status;

	@OneToMany(mappedBy = "apartment")
	private List<Bill> bills;

	public Apartment() {
		super();
	}

	public Apartment(String bedroom, String door, String price, String status) {
		super();
		this.bedroom = bedroom;
		this.door = door;
		this.price = price;
		this.status = status;
	}

	public Apartment(int id, String bedroom, String door, String price, String status, List<Bill> bills) {
		super();
		this.id = id;
		this.bedroom = bedroom;
		this.door = door;
		this.price = price;
		this.status = status;
		this.bills = bills;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBedroom() {
		return bedroom;
	}

	public void setBedroom(String bedroom) {
		this.bedroom = bedroom;
	}

	public String getDoor() {
		return door;
	}

	public void setDoor(String door) {
		this.door = door;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Bill> getBills() {
		return bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

	@Override
	public String toString() {
		return "Apartment [id=" + id + ", bedroom=" + bedroom + ", door=" + door + ", price=" + price + ", status="
				+ status + "]";
	}

}
