package com.la101.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.la101.dao.DoctorDao;
import com.la101.entities.Doctor;
import com.la101.utils.HibernateUtils;

public class DoctorDaoImpl implements DoctorDao<Doctor> {
	
	private static final Logger logger = Logger.getLogger(DoctorDaoImpl.class);

	public boolean save(Doctor doctor) {
		Session session = null;
		Transaction transaction = null;

		try {
			logger.info("open session");
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			Serializable result = session.save(doctor);

			transaction.commit();

			return (result != null);

		} catch (Exception e) {
			logger.error(e.getMessage());
			System.out.println(e.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
			return false;
		} finally {
			logger.info("close session");
			if (session != null) {
				session.close();
			}
		}
	}

	public Doctor getById(int id) {
		Session session = null;
		Transaction transaction = null;
		Doctor room = null;

		try {

			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			room = session.get(Doctor.class, id);

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

		return room;
	}

	public List<Doctor> getAll() {
		List<Doctor> doctors = new ArrayList<Doctor>();

		Session session = null;
		Transaction transaction = null;

		try {

			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			String hql = "FROM Doctor";

			Query query = session.createQuery(hql);
			doctors = query.list();

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

		return doctors;

	}

	public void update(Doctor doctor) {
		Session session = null;
		Transaction transaction = null;

		try {

			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			session.update(doctor);

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
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
			e.printStackTrace();
			System.out.println(e.getMessage());
			transaction.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

}
