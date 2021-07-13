package com.la101.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.la101.dao.AppointmentDao;
import com.la101.dao.BillDao;
import com.la101.dao.impl.AppointmentDaoImpl;
import com.la101.dao.impl.BillDaoImpl;
import com.la101.entities.Appointment;
import com.la101.entities.Bill;
import com.la101.services.AppointmentServices;
import com.la101.services.BillServices;

public class BillServicesImpl implements BillServices {

	Scanner scanner = new Scanner(System.in);

	BillDao<Bill> billDao = new BillDaoImpl();

	AppointmentDao<Appointment> appointmentDao = new AppointmentDaoImpl();

	AppointmentServices appointmentServices = new AppointmentServicesImpl();

	public void addNewBill() {

		appointmentServices.showAllAppointment();

		Appointment appointment = null;

		do {
			System.out.println("Enter the id Appointment");

			String idAppointment = scanner.nextLine();

			appointment = appointmentDao.getById(Integer.valueOf(idAppointment));

			if (appointment != null) {
				break;
			} else {
				System.out.println("Please Enter correct the id of Appointment");
			}

		} while (true);

		System.out.println("Enter the bill date");
		String billDate = scanner.nextLine();

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
	
	public static void main(String[] args) {
		BillServicesImpl billServicesImpl = new BillServicesImpl();
		
		billServicesImpl.addNewBill();
	}

}
