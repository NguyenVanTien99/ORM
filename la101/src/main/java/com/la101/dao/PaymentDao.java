package com.la101.dao;

import java.util.List;

public interface PaymentDao <T> {
	boolean save(T t);

	T getById(int id);

	List<T> getAll();

	void update(T t);

	void delete(int id);
}
