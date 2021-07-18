package com.demo.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;
	
	@Column(name = "Employee_name")
	private String name;
	
	@Column(name = "Role_Name")
	private String role;
	
	@OneToMany(mappedBy = "employee")
	private List<Bill> bills;
	
	

	public Employee() {
		super();
	}



	public Employee(String name, String role) {
		super();
		this.name = name;
		this.role = role;
	}



	public Employee(int id, String name, String role, List<Bill> bills) {
		super();
		this.id = id;
		this.name = name;
		this.role = role;
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



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public List<Bill> getBills() {
		return bills;
	}



	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}



	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", role=" + role + "]";
	}
	
	
	
	
}
