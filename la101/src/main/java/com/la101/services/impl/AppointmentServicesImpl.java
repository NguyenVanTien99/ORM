package com.la101.services.impl;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import org.hibernate.query.Query;

import com.la101.dao.AppointmentDao;
import com.la101.dao.BillDao;
import com.la101.dao.DoctorDao;
import com.la101.dao.PatientDao;
import com.la101.dao.impl.AppointmentDaoImpl;
import com.la101.dao.impl.BillDaoImpl;
import com.la101.dao.impl.DoctorDaoImpl;
import com.la101.dao.impl.PatientDaoImpl;
import com.la101.entities.Appointment;
import com.la101.entities.Bill;
import com.la101.entities.Doctor;
import com.la101.entities.Patient;
import com.la101.services.AppointmentServices;
import com.la101.services.DoctorServices;
import com.la101.services.PatientServices;

public class AppointmentServicesImpl implements AppointmentServices {

	Scanner scanner = new Scanner(System.in);

	DoctorServices doctorServices = new DoctorServicesImpl();

	PatientServices patientServices = new PatientServicesImpl();

	DoctorDao<Doctor> doctorDao = new DoctorDaoImpl();

	PatientDao<Patient> patientDao = new PatientDaoImpl();

	AppointmentDao<Appointment> appointmentDao = new AppointmentDaoImpl();
	
	BillDao<Bill> billDao = new BillDaoImpl();

	public void addNewAppointment() {

		Appointment appointment = null;

		System.out.println("Enter the Date:");

		String date = scanner.nextLine();

		System.out.println("Enter the time");

		String time = scanner.nextLine();

		System.out.println("Enter duration");

		String duration = scanner.nextLine();

		System.out.println("Enter reason");

		String reason = scanner.nextLine();

		doctorServices.showAllDoctor();

		Doctor doctor = null;

		do {
			System.out.println("Enter the number doctor");

			String numberDoctor = scanner.nextLine();

			doctor = doctorDao.getById(Integer.valueOf(numberDoctor));

			if (doctor != null) {
				break;
			} else {
				System.out.println("Please Enter the correct number of doctors");
				continue;
			}
		} while (true);

		patientServices.showAllPatient();

		Patient patient = null;

		do {
			System.out.println("Enter the id patient");

			String idPatient = scanner.nextLine();

			patient = patientDao.getById(Integer.valueOf(idPatient));

			if (patient != null) {
				break;
			} else {
				System.out.println("Please Enter the correct id of patient");
				continue;
			}
		} while (true);

		try {
			appointment = new Appointment();

			appointment.setDate(new SimpleDateFormat("MM/dd/yyyy").parse(date));

			appointment.setTime(new SimpleDateFormat("HH:mm").parse(time));

			appointment.setDuration(Integer.valueOf(duration));

			appointment.setReason(reason);

			appointment.setDoctor(doctor);

			appointment.setPatient(patient);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Data found");
		}

		Appointment appointmentExist = appointmentDao.getByDateAndPatient(date, patient);

		if (appointmentExist != null) {
			appointment.setId(appointmentExist.getId());
			appointmentDao.update(appointment);
			System.out.println("Update success");
		} else {
			appointmentDao.save(appointment);
			System.out.println("Add success");
		}
		
		try {
			Bill bill = new Bill(new SimpleDateFormat("MM/dd/yyyy").parse(date), "0");
			billDao.save(bill);
		} catch (ParseException e) {
			System.out.println("Add Bill False"); 
			e.printStackTrace();
		}
		

	}
	
	public void showAllAppointment() {
		List<Appointment> appointments = appointmentDao.getAll();

		if (appointments.size() == 0) {
			System.out.println("Doctor Empty");
		} else {
			for (Appointment appointment : appointments) {
				System.out.println(appointment);
			}
		}
		
	}

	public static void main(String[] args) {
		AppointmentServicesImpl appointmentServicesImpl = new AppointmentServicesImpl();

//		Date date = new Date("11/11/2002 17:30 ");
//
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
//		System.out.println(calendar);// c
//		calendar.add(Calendar.MINUTE, 60);
//
//		System.out.println(calendar.getTime());

		 appointmentServicesImpl.addNewAppointment();

	}

	

}
