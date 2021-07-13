package com.la101.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.la101.dao.PaymentDao;
import com.la101.entities.Appointment;
import com.la101.entities.Payment;
import com.la101.utils.HibernateUtils;

public class PaymentDaoImpl implements PaymentDao<Payment> {

	public boolean save(Payment payment) {
		Session session = null;
		Transaction transaction = null;

		try {

			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			Serializable result = session.save(payment);

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

	public Payment getById(int id) {
		Session session = null;
		Transaction transaction = null;
		Payment payment = null;

		try {

			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			payment = session.get(Payment.class, id);

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

		return payment;
	}

	public List<Payment> getAll() {
		List<Payment> payments = new ArrayList<Payment>();

		Session session = null;
		Transaction transaction = null;

		try {

			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			String hql = "FROM Payment";

			Query query = session.createQuery(hql);
			payments = query.list();

			transaction.commit();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return payments;
	}

	public void update(Payment payment) {
		Session session = null;
		Transaction transaction = null;

		try {

			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			session.update(payment);

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

}
