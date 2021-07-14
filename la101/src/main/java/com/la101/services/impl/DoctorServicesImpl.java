package com.la101.services.impl;

import java.util.List;
import java.util.Scanner;

import com.la101.dao.DoctorDao;
import com.la101.dao.impl.DoctorDaoImpl;
import com.la101.entities.Doctor;
import com.la101.services.DoctorServices;

public class DoctorServicesImpl implements DoctorServices {

	public static Scanner scanner = new Scanner(System.in);

	DoctorDao<Doctor> doctorDao = new DoctorDaoImpl();

	public void addNewDoctor() {
		System.out.println("Enter the First Name");

		String firstName = scanner.nextLine();

		System.out.println("Enter the Last Name");

		String lastName = scanner.nextLine();

		Doctor doctor = new Doctor(firstName, lastName);

		if (doctorDao.save(doctor)) {
			System.out.println("Add doctor Success");
		} else {
			System.out.println("Error");
		}

	}

	public void showAllDoctor() {
		List<Doctor> doctors = doctorDao.getAll();

		if (doctors.size() == 0) {
			System.out.println("Doctor Empty");
		} else {
			for (Doctor doctor : doctors) {
				System.out.println(doctor);
			}
		}

	}

	public void deleteDoctor() {
		showAllDoctor();

		Doctor doctor = null;

		String numberDoctor = null;

		do {
			System.out.println("Enter the number doctor");

			numberDoctor = scanner.nextLine();

			doctor = doctorDao.getById(Integer.valueOf(numberDoctor));

			if (doctor != null) {
				break;
			} else {
				System.out.println("Please Enter the correct number of doctors");
				continue;
			}
		} while (true);

		try {
			doctorDao.delete(Integer.valueOf(numberDoctor));
			System.out.println("delete doctor success");
		} catch (Exception e) {
			System.out.println("error");
		}

	}

	public void updateDoctor() {
		showAllDoctor();

		Doctor doctor = null;

		String numberDoctor = null;

		do {
			System.out.println("Enter the number doctor");

			numberDoctor = scanner.nextLine();

			doctor = doctorDao.getById(Integer.valueOf(numberDoctor));

			if (doctor != null) {
				break;
			} else {
				System.out.println("Please Enter the correct number of doctors");
				continue;
			}
		} while (true);

		System.out.println("Enter the First Name");

		String firstName = scanner.nextLine();

		System.out.println("Enter the Last Name");

		String lastName = scanner.nextLine();

		doctor.setFirstName(firstName);

		doctor.setLastName(lastName);

		try {
			doctorDao.update(doctor);
			System.out.println("Update succes");
		} catch (Exception e) {
			System.out.println("error");
		}

	}

}
