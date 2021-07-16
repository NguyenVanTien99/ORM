package com.la101.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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

	public List<Bill> getBillByDate(String date) {
		List<Bill> bills = new ArrayList<Bill>();

		Session session = null;
		Transaction transaction = null;

		try {

			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			String hql = "FROM Bill b WHERE b.billDate = :date ";

			Query query = session.createQuery(hql);

			query.setParameter("date", new Date(date));

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

	public List<Bill> paging(int pageNumber, int pageSize) {
		List<Bill> bills = null;

		Session session = null;
		Transaction transaction = null;

		try {

			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

			CriteriaQuery<Bill> criteriaQuery = criteriaBuilder.createQuery(Bill.class);

			Root<Bill> root = criteriaQuery.from(Bill.class);

			criteriaQuery.select(root);

			Query<Bill> query = session.createQuery(criteriaQuery);

			// ignore
			query.setFirstResult((pageNumber - 1) * pageSize);
			query.setMaxResults(pageSize);

			bills = query.getResultList();

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

	public static void main(String[] args) {
		BillDaoImpl billDaoImpl = new BillDaoImpl();

		List<Bill> bills = billDaoImpl.paging(1, 3);

		for (Bill bill : bills) {
			System.out.println(bill.getBillNumber());
		}
	}

}
