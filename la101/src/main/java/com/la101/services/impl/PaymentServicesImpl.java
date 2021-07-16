package com.la101.services.impl;

import java.text.SimpleDateFormat;
import java.util.Iterator;
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
import com.la101.utils.Validator;

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

			String idPattent;

			do {

				System.out.println("Enter the id Of patient");

				idPattent = scanner.nextLine();

				if (!Validator.isNumber(idPattent)) {
					System.out.println("Value is number");
				}

				break;

			} while (true);

			patient = patientDao.getById(Integer.valueOf(idPattent));

			if (patient != null) {
				break;
			}

			continue;
		} while (true);

		Bill bill = new Bill();

		do {

			String billNumber;

			do {

				System.out.println("Enter the number of bill");

				billNumber = scanner.nextLine();

				if (!Validator.isNumber(billNumber)) {
					System.out.println("Value is number");
				}

				break;

			} while (true);

			bill = billDao.getById(Integer.valueOf(billNumber));

			if (bill != null) {
				break;
			}

			continue;
		} while (true);

		String date;

		do {

			System.out.println("Enter the date");

			date = scanner.nextLine();

			if (!Validator.isDate(date)) {
				System.out.println("Date is format MM/dd/yyyy");
				continue;
			}

			break;
		} while (true);

		System.out.println("Enter the method");

		String method = scanner.nextLine();

		String amount;

		do {
			System.out.println("Enter the amount");

			amount = scanner.nextLine();

			if (!Validator.isNumber(amount)) {
				System.out.println("Value is number");
				continue;
			}
			break;
		} while (true);

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

		Double totalAmountOfPayment = 0d;

		for (Payment payment2 : bill.getPayments()) {
			totalAmountOfPayment += payment2.getPayAmount();

		}

		if (totalAmountOfPayment < bill.getTotal()) {
			if (paymentDao.save(payment)) {
				System.out.println("Add payment sucess");
			} else {
				System.out.println("Error");
			}
			
			checkStatusBill(bill.getBillNumber());

		} else {

			System.out.println("Bill Paid Done");
		}

	}

	public void checkStatusBill(int id) {
		Bill bill = billDao.getById(id);

		Double totalAmountOfPayment = 0d;

		for (Payment payment : bill.getPayments()) {
			totalAmountOfPayment += payment.getPayAmount();

		}

		if (totalAmountOfPayment >= bill.getTotal()) {
			bill.setBillStatus("1");
			billDao.update(bill);
		}
	}

	public void findPaymentByBill() {

		billServices.showAllbill();
		String idBill;
		do {

			System.out.println("Enter the number of bill");
			idBill = scanner.nextLine();

			if (!Validator.isNumber(idBill)) {
				System.out.println("Value is number");
				continue;
			}

			break;
		} while (true);

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

		paymentServicesImpl.addNewPayment();
	}

}
