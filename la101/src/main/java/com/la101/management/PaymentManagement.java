package com.la101.management;

import java.util.Scanner;

import com.la101.services.PaymentServices;
import com.la101.services.impl.PaymentServicesImpl;

public class PaymentManagement {
	
	static PaymentServices paymentServices = new PaymentServicesImpl();
	
	public static void showMenu() {
		int choice;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.println("----------- Management ---------------");
			System.out.println("1.Add new payment");
			System.out.println("2.Show all payment");
			System.out.println("3.Back");
			System.out.println("0.Exit");
			System.out.println("------------------------------------------------------");
			System.out.print("please Select: ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				paymentServices.addNewPayment();
				break;

			case 2:
				break;

			case 3:
				Management.showMenu();
				break;

			default:
				choice = 0;
				break;
			}

		} while (!(choice == 0));
	}
}
