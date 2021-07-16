package com.la101.dao.impl;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.la101.dao.PatientDao;
import com.la101.entities.Patient;
import com.la101.utils.HibernateUtils;

public class PatientDaoImpl implements PatientDao<Patient> {

	public boolean save(Patient patient) {
		Session session = null;
		Transaction transaction = null;

		try {

			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			Serializable result = session.save(patient);

			transaction.commit();

			return (result != null);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
			return false;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public Patient getById(int id) {
		Session session = null;
		Transaction transaction = null;
		Patient patient = null;

		try {

			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			patient = session.get(Patient.class, id);

			transaction.commit();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return patient;
	}

	public List<Patient> getAll() {
		List<Patient> patients = new ArrayList<Patient>();

		Session session = null;
		Transaction transaction = null;

		try {

			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			String hql = "FROM Patient";

			Query query = session.createQuery(hql);
			patients = query.list();

			transaction.commit();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return patients;
	}

	public void update(Patient patient) {
		Session session = null;
		Transaction transaction = null;

		try {

			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			session.update(patient);

			transaction.commit();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public void delete(int id) {
		Session session = null;
		Transaction transaction = null;

		try {

			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			session.delete(getById(id));

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

	public List<Patient> paging(String pageNumber, String row) {
		Session session = null;
		Transaction transaction = null;
		
		List<Patient> patients = null;

		try {

			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			Query<Patient> query = session.createSQLQuery("EXEC [PR_PAGE] :pageNumber, :row")
					.addEntity(Patient.class).setParameter("pageNumber", pageNumber).setParameter("row", row);

			patients = query.list();

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			transaction.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return patients;

	}

	public static void main(String[] args) {
		PatientDaoImpl daoImpl = new PatientDaoImpl();

		List<Patient> patients =  daoImpl.paging("0", "3");
		
		for (Patient patient : patients) {
			System.out.println(patient.getId());
		}
	}

}
