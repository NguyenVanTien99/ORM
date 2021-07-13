package com.a100.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.a100.HibernateUtils;
import com.a100.dao.EmployeeDao;
import com.a100.entities.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public boolean save(Employee employee) {
		Session session = null;
		Transaction transaction = null;

		try {

			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			Serializable result = session.save(employee);

			transaction.commit();

			return (result != null);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
			return false;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public Employee findById(int id) {
		Session session = null;
		Transaction transaction = null;
		Employee employee = null;

		try {

			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			employee = session.get(Employee.class, id);

			transaction.commit();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return employee;
	}

	@Override
	public List<Employee> getAll() {
		List<Employee> employees = new ArrayList<Employee>();

		Session session = null;
		Transaction transaction = null;

		try {

			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			String hql = "FROM Employee";

			Query query = session.createQuery(hql);
			employees = query.list();

			transaction.commit();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		for (Employee employee : employees) {
			System.out.println(employee.getFirstName());
		}

		return employees;

	}



	@Override
	public void update(Employee employee) {
		Session session = null;
		Transaction transaction = null;

		try {

			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			session.update(employee);

			transaction.commit();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public void delete(int id) {
		Session session = null;
		Transaction transaction = null;

		try {

			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			session.delete(findById(id));

			transaction.commit();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

}
