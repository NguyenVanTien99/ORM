package com.la101.services.impl;

import java.util.List;
import java.util.Scanner;

import com.la101.dao.PatientDao;
import com.la101.dao.impl.PatientDaoImpl;
import com.la101.entities.Appointment;
import com.la101.entities.Patient;
import com.la101.services.PatientServices;
import com.la101.utils.Validator;

public class PatientServicesImpl implements PatientServices {

	public static Scanner scanner = new Scanner(System.in);

	public static PatientDao<Patient> patientDao = new PatientDaoImpl();

	public void addNewPatient() {
		System.out.println("Enter the First Name");

		String firstName = scanner.nextLine();

		System.out.println("Enter the Last Name");

		String lastName = scanner.nextLine();

		System.out.println("Enter the Address");

		String address = scanner.nextLine();

		System.out.println("Enter the city");

		String city = scanner.nextLine();

		System.out.println("Enter the state");

		String state = scanner.nextLine();

		Patient patient = new Patient(firstName, lastName, address, city, state);

		if (patientDao.save(patient)) {
			System.out.println("Success");
		} else {
			System.out.println("Error");
		}
	}

	public void showAllPatient() {
		List<Patient> patients = patientDao.getAll();

		if (patients.size() == 0) {
			System.out.println("Doctor Empty");
		} else {
			for (Patient patient : patients) {
				System.out.println(patient);
			}
		}

	}

	public void showAllPatientAndBill() {
		List<Patient> patients = patientDao.getAll();

		for (Patient patient : patients) {
			for (Appointment appointment : patient.getAppointments()) {
				System.out.println("ID of patient: " + appointment.getPatient().getId() + " - BillNumber: "
						+ appointment.getBill().getBillNumber());
			}
		}

	}

	public void paging() {
		String pageNumber;

		String size;

		do {
			System.out.println("Enter the page number");

			pageNumber = scanner.nextLine();

			if (!Validator.isNumber(pageNumber)) {
				System.out.println("Number is value");
				continue;
			}

			break;
		} while (true);

		do {
			System.out.println("Enter the size");

			size = scanner.nextLine();

			if (!Validator.isNumber(size)) {
				System.out.println("Number is value");
				continue;
			}

			break;
		} while (true);

		int pageParam = Integer.parseInt(pageNumber) - 1;

		List<Patient> patients = patientDao.paging(String.valueOf(pageParam), size);
		
		for (Patient patient : patients) {
			System.out.println(patient);
		}

	}

	public static void main(String[] args) {
		PatientServicesImpl patientServicesImpl = new PatientServicesImpl();

		patientServicesImpl.paging();
	}

}
