package com.demo.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;

	@Column(name = "Customer_name")
	private String name;

	@Column(name = "Phone")
	private String phone;

	@Column(name = "Birthday")
	@Temporal(TemporalType.DATE)
	private Date birthday;

	@Column(name = "Address")
	private String address;
	
	@OneToMany(mappedBy = "customer")
	private List<Bill> bills;
	
	

	public Customer() {
		super();
	}



	public Customer(String name, String phone, Date birthday, String address) {
		super();
		this.name = name;
		this.phone = phone;
		this.birthday = birthday;
		this.address = address;
	}



	public Customer(int id, String name, String phone, Date birthday, String address, List<Bill> bills) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.birthday = birthday;
		this.address = address;
		this.bills = bills;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public Date getBirthday() {
		return birthday;
	}



	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public List<Bill> getBills() {
		return bills;
	}



	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}



	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", phone=" + phone + ", birthday=" + birthday + ", address="
				+ address + "]";
	}
	
	
	
	
	
}
