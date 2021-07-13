package com.la101.dao.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.la101.dao.AppointmentDao;
import com.la101.dao.PatientDao;
import com.la101.entities.Appointment;
import com.la101.entities.Patient;
import com.la101.utils.HibernateUtils;

public class AppointmentDaoImpl implements AppointmentDao<Appointment> {

	public boolean save(Appointment appointment) {
		Session session = null;
		Transaction transaction = null;

		try {

			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			Serializable result = session.save(appointment);

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

	public Appointment getById(int id) {
		Session session = null;
		Transaction transaction = null;
		Appointment appointment = null;

		try {

			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			appointment = session.get(Appointment.class, id);

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

		return appointment;
	}

	public List<Appointment> getAll() {
		List<Appointment> appointments = new ArrayList<Appointment>();

		Session session = null;
		Transaction transaction = null;

		try {

			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			String hql = "FROM Appointment";

			Query query = session.createQuery(hql);
			appointments = query.list();

			transaction.commit();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return appointments;
	}

	public void update(Appointment appointment) {
		Session session = null;
		Transaction transaction = null;

		try {

			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			session.update(appointment);

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

	public Appointment getByDateAndPatient(String date, Patient patient) {
		Session session = null;
		Transaction transaction = null;
		Appointment appointment = null;

		try {

			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			String hqlString = "FROM Appointment WHERE date = :date And patient = :patient";
			Query query = session.createQuery(hqlString);

			query.setParameter("date", new SimpleDateFormat("MM/dd/yyyy").parse(date));

			query.setParameter("patient", patient);

			appointment = (Appointment) query.getSingleResult();

			transaction.commit();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return appointment;
	}

	public static void main(String[] args) {
		PatientDao<Patient> patientDao = new PatientDaoImpl();
		AppointmentDaoImpl appointmentDao = new AppointmentDaoImpl();
		appointmentDao.getByDateAndPatient("11/11/2020", patientDao.getById(2));
	}

}
