package com.la101.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.hibernate.mapping.Value;

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
import com.la101.utils.Validator;

public class AppointmentServicesImpl implements AppointmentServices {

	Scanner scanner = new Scanner(System.in);

	DoctorServices doctorServices = new DoctorServicesImpl();

	PatientServices patientServices = new PatientServicesImpl();

	static DoctorDao<Doctor> doctorDao = new DoctorDaoImpl();

	PatientDao<Patient> patientDao = new PatientDaoImpl();

	AppointmentDao<Appointment> appointmentDao = new AppointmentDaoImpl();

	BillDao<Bill> billDao = new BillDaoImpl();

	public void addNewAppointment() {

		Appointment appointment = null;

		String date;

		do {
			System.out.println("Enter the Date:");

			date = scanner.nextLine();

			if (!Validator.isDate(date)) {
				System.out.println("Date is format MM/dd/yyy");
				continue;
			}

			break;
		} while (true);

		String time;

		do {
			System.out.println("Enter the time");

			time = scanner.nextLine();

			if (!Validator.isTime(time)) {
				System.out.println("Time is format HH:mm");
				continue;
			}

			break;
		} while (true);

		String duration;

		do {

			System.out.println("Enter duration");
			duration = scanner.nextLine();

			if (!Validator.isNumber(duration)) {
				System.out.println("Value is number");
				continue;
			}

			break;
		} while (true);

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

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

		if (!checkTimeconflict(doctor, date, time, duration)) {
			try {
				appointment = new Appointment();

				appointment.setDate(new SimpleDateFormat("MM/dd/yyyy").parse(date));

				appointment.setTime(LocalTime.parse(time, formatter));

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

				doctor.getAppointments().remove(appointmentExist);

				if (!checkTimeconflict(doctor, date, time, duration)) {
					appointment.setId(appointmentExist.getId());
					appointmentDao.update(appointment);
					System.out.println("Update success");
				} else {
					System.out.println("conflict Appointment");
				}

			} else {
				appointmentDao.save(appointment);
				System.out.println("Add success");
				try {
					Bill bill = new Bill(new SimpleDateFormat("MM/dd/yyyy").parse(date), "0");
					bill.setAppointment(appointment);
					billDao.save(bill);
				} catch (ParseException e) {
					System.out.println("Add Bill False");
					e.printStackTrace();
				}
			}

		} else {
			System.out.println("conflict Appointment");
		}

	}

	public void showAllAppointment() {
		List<Appointment> appointments = appointmentDao.getAll();

		if (appointments.size() == 0) {
			System.out.println("Bill Empty");
		} else {
			for (Appointment appointment : appointments) {
				System.out.println(appointment);
			}
		}

	}

	public boolean checkTimeconflict(Doctor doctor, String date, String hour, String duration) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

		boolean check = true;

		for (Appointment appointment : doctor.getAppointments()) {
			if (!appointment.getDate().equals(new Date(date))) {
				check = false;
			} else {

				LocalTime startAp = appointment.getTime();
				LocalTime endAp = startAp.plusMinutes(Integer.valueOf(appointment.getDuration()));
				LocalTime startAdd = LocalTime.parse(hour, formatter);
				LocalTime endAdd = startAdd.plusMinutes(Integer.valueOf(duration));

				check = true;

				if (startAdd.isBefore(startAp) && endAdd.isBefore(startAp)) {
					check = false;
				} else if (startAdd.isAfter(endAp) && endAdd.isAfter(endAp)) {
					check = false;
				}

				if (check == false) {
					continue;
				} else {
					break;
				}

			}
		}

		return check;

	}

	public static void main(String[] args) {

		Doctor doctor = doctorDao.getById(1);

		AppointmentServicesImpl appointmentServicesImpl = new AppointmentServicesImpl();

		appointmentServicesImpl.addNewAppointment();

//		Boolean checkBoolean = appointmentServicesImpl.checkTimeconflict(doctor, "11/11/2020", "11:00", "120");
//
//		System.out.println(checkBoolean);

	}

}
