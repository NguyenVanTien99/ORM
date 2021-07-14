package com.la101.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import com.la101.dao.AppointmentDao;
import com.la101.dao.BillDao;
import com.la101.dao.impl.AppointmentDaoImpl;
import com.la101.dao.impl.BillDaoImpl;
import com.la101.entities.Appointment;
import com.la101.entities.Bill;
import com.la101.services.AppointmentServices;
import com.la101.services.BillServices;
import com.la101.utils.Validator;

public class BillServicesImpl implements BillServices {

	Scanner scanner = new Scanner(System.in);

	BillDao<Bill> billDao = new BillDaoImpl();

	AppointmentDao<Appointment> appointmentDao = new AppointmentDaoImpl();

	AppointmentServices appointmentServices = new AppointmentServicesImpl();

	public void addNewBill() {

		appointmentServices.showAllAppointment();

		Appointment appointment = null;

		do {

			String idAppointment;

			do {

				System.out.println("Enter the id Appointment");
				idAppointment = scanner.nextLine();

				if (!Validator.isNumber(idAppointment)) {
					System.out.println("Value is number");
					continue;
				}

				break;
			} while (true);

			appointment = appointmentDao.getById(Integer.valueOf(idAppointment));

			if (appointment != null) {
				break;
			} else {
				System.out.println("Please Enter correct the id of Appointment");
			}

		} while (true);

		String billDate;

		do {
			System.out.println("Enter the bill date");
			billDate = scanner.nextLine();

			if (!Validator.isDate(billDate)) {
				System.out.println("Date is format MM/dd/yyy");
				continue;
			}

			break;
		} while (true);

		System.out.println("Enter the bill status");
		String billStatus = scanner.nextLine();

		Bill bill = new Bill();

		try {
			bill.setBillDate(new SimpleDateFormat("MM/dd/yyyy").parse(billDate));
			bill.setBillStatus(billStatus);
			bill.setAppointment(appointment);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Data found");
		}

		if (billDao.save(bill)) {
			System.out.println("Add Bill Success");
		} else {
			System.out.println("error");
		}

	}

	public void findByDate() {
		
		String billDate;

		do {
			System.out.println("Enter the bill date");
			billDate = scanner.nextLine();

			if (!Validator.isDate(billDate)) {
				System.out.println("Date is format MM/dd/yyy");
				continue;
			}

			break;
		} while (true);

		List<Bill> bills = null;

		bills = billDao.getBillByDate(billDate);

		if (bills.size() > 0) {
			for (Bill bill : bills) {
				System.out.println(bill);
			}
		} else {
			System.out.println("No Bill");
		}

	}

	public void showAllbill() {
		List<Bill> bills = billDao.getAll();

		if (bills.size() == 0) {
			System.out.println("Bill Empty");
		} else {
			for (Bill bill : bills) {
				System.out.println(bill);
			}
		}

	}

	public static void main(String[] args) {
		BillServicesImpl billServicesImpl = new BillServicesImpl();

		billServicesImpl.showAllbill();
		;
	}

}
