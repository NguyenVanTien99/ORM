package com.la101.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Pat_id")
	private int id;

	@Column(name = "Pat_Fistname")
	private String firstName;

	@Column(name = "Pat_Lastname")
	private String lastName;

	@Column(name = "Pat_Address")
	private String address;

	@Column(name = "Pat_City")
	private String city;

	@Column(name = "Pat_State")
	private String state;

	@OneToMany(mappedBy = "patient", fetch = FetchType.EAGER)
	private List<Appointment> appointments;

	@OneToMany(mappedBy = "patient")
	private List<Payment> payments;

	public Patient() {
		super();
	}

	public Patient(String firstName, String lastName, String address, String city, String state) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
	}

	public Patient(String firstName, String lastName, String address, String city, String state,
			List<Appointment> appointments, List<Payment> payments) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.appointments = appointments;
		this.payments = payments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", city=" + city + ", state=" + state + "]";
	}

}
