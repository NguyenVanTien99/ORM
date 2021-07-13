package com.la101.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Doc_Number")
	private int number;

	@Column(name = "Doc_Firstname")
	private String firstName;

	@Column(name = "Doc_Lastname")
	private String lastName;
	
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
	private List<Appointment> appointments;

	public Doctor() {
		super();
	}

	public Doctor(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
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

	@Override
	public String toString() {
		return "Doctor [number=" + number + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	
	

}
