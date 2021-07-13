package com.la101.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.la101.dao.BillDao;
import com.la101.entities.Appointment;
import com.la101.entities.Bill;
import com.la101.utils.HibernateUtils;

public class BillDaoImpl implements BillDao<Bill> {

	public boolean save(Bill bill) {
		Session session = null;
		Transaction transaction = null;

		try {

			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			Serializable result = session.save(bill);

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

	public Bill getById(int id) {
		Session session = null;
		Transaction transaction = null;
		Bill bill = null;

		try {

			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			bill = session.get(Bill.class, id);

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

		return bill;
	}

	public List<Bill> getAll() {
		List<Bill> bills = new ArrayList<Bill>();

		Session session = null;
		Transaction transaction = null;

		try {

			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			String hql = "FROM Bill";

			Query query = session.createQuery(hql);
			bills = query.list();

			transaction.commit();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return bills;
	}

	public void update(Bill bill) {
		Session session = null;
		Transaction transaction = null;

		try {

			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			session.update(bill);

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
