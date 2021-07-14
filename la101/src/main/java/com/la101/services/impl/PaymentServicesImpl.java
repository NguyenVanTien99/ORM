package com.la101.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.la101.dao.BillDao;
import com.la101.dao.PatientDao;
import com.la101.dao.PaymentDao;
import com.la101.dao.impl.BillDaoImpl;
import com.la101.dao.impl.PatientDaoImpl;
import com.la101.dao.impl.PaymentDaoImpl;
import com.la101.entities.Bill;
import com.la101.entities.Patient;
import com.la101.entities.Payment;
import com.la101.services.BillServices;
import com.la101.services.PatientServices;
import com.la101.services.PaymentServices;

public class PaymentServicesImpl implements PaymentServices {

	Scanner scanner = new Scanner(System.in);

	PatientServices patientServices = new PatientServicesImpl();

	PatientDao<Patient> patientDao = new PatientDaoImpl();

	BillDao<Bill> billDao = new BillDaoImpl();

	PaymentDao<Payment> paymentDao = new PaymentDaoImpl();

	BillServices billServices = new BillServicesImpl();

	public void addNewPayment() {
		patientServices.showAllPatientAndBill();

		Patient patient = new Patient();

		do {

			System.out.println("Enter the id Of patient");

			String idPattent = scanner.nextLine();

			patient = patientDao.getById(Integer.valueOf(idPattent));

			if (patient != null) {
				break;
			}

			continue;
		} while (true);

		Bill bill = new Bill();

		do {

			System.out.println("Enter the number of bill");

			String billNumber = scanner.nextLine();

			bill = billDao.getById(Integer.valueOf(billNumber));

			if (bill != null) {
				break;
			}

			continue;
		} while (true);

		System.out.println("Enter the date");

		String date = scanner.nextLine();

		System.out.println("Enter the method");

		String method = scanner.nextLine();

		System.out.println("Enter the amount");

		String amount = scanner.nextLine();

		Payment payment = new Payment();

		try {
			payment.setPayDate(new SimpleDateFormat("MM/dd/yyyy").parse(date));
			payment.setPayMethod(amount);
			payment.setPayAmount(Double.valueOf(amount));
			payment.setBill(bill);
			payment.setPatient(patient);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());

		}

		if (paymentDao.save(payment)) {
			System.out.println("Add payment sucess");
		} else {
			System.out.println("Error");
		}

	}

	public void findPaymentByBill() {

		billServices.showAllbill();

		System.out.println("Enter the number of bill");
		String idBill = scanner.nextLine();

		Bill bill = billDao.getById(Integer.valueOf(idBill));

		if (bill != null) {

			for (Payment payment : bill.getPayments()) {
				System.out.println(payment);
			}

		} else {
			System.out.println("No Found Bill");
		}

	}

	public static void main(String[] args) {
		PaymentServicesImpl paymentServicesImpl = new PaymentServicesImpl();

		paymentServicesImpl.findPaymentByBill();
	}

}
