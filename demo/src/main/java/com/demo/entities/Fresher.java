package com.demo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Fresher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	
	private String name;
	
	@OneToOne(mappedBy = "")
	private Address address;
	
	@OneToMany
	List<Courses> coursesrs = new ArrayList<>();
	
	public Fresher() {
		super();
	}


	public Fresher(String name) {
		super();
		this.name = name;
	}
	

	public Fresher(String name, Address address) {
		this.name = name;
		this.address = address;
	}
	
	


	public Fresher(String name, List<Courses> coursesrs) {
		super();
		this.name = name;
		this.coursesrs = coursesrs;
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


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public List<Courses> getCoursesrs() {
		return coursesrs;
	}


	public void setCoursesrs(List<Courses> coursesrs) {
		this.coursesrs = coursesrs;
	}
	
	
	
	
	
	
	
	

}
