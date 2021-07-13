package com.a100.dao;

import java.util.List;

import com.a100.entities.Employee;

public interface EmployeeDao {
	
	public boolean save(Employee employee);
	
	public Employee findById(int id);
	
	public List<Employee> getAll();
	
	public void update(Employee room);
	
	public void delete(int id);
}
