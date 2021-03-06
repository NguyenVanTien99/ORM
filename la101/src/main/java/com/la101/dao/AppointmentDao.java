package com.la101.dao;

import java.util.List;

import com.la101.entities.Patient;

public interface AppointmentDao<T> {
	boolean save(T t);

	T getById(int id);

	List<T> getAll();

	void update(T t);

	void delete(int id);
	
	T getByDateAndPatient(String date, Patient patient);
}
