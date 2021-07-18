package com.demo.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "apartment_id")
	private Apartment apartment;

	@Column(name = "buy_date")
	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(name = "price")
	private int price;
	
	

	public Bill() {
		super();
	}



	public Bill(Employee employee, Customer customer, Apartment apartment, Date date, int price) {
		super();
		this.employee = employee;
		this.customer = customer;
		this.apartment = apartment;
		this.date = date;
		this.price = price;
	}



	public Bill(int id, Employee employee, Customer customer, Apartment apartment, Date date, int price) {
		super();
		this.id = id;
		this.employee = employee;
		this.customer = customer;
		this.apartment = apartment;
		this.date = date;
		this.price = price;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Apartment getApartment() {
		return apartment;
	}



	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	public Employee getEmployee() {
		return employee;
	}



	public void setEmployee(Employee employee) {
		this.employee = employee;
	}



	public Customer getCustomer() {
		return customer;
	}



	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	@Override
	public String toString() {
		return "Bill [id=" + id + ", employee=" + employee.getId() + ", customer=" + customer .getId()+ ", apartment=" + apartment.getId()
				+ ", date=" + date + ", price=" + price + "]";
	}

	
}
