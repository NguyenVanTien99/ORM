package com.la101.management;

import java.util.Scanner;

import com.la101.services.DoctorServices;
import com.la101.services.impl.DoctorServicesImpl;

/**
 * Hello world!
 *
 */
public class Management {

	public static void showMenu() {
		int choice;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.println("----------- Management ---------------");
			System.out.println("1.Manage Doctor");
			System.out.println("2.Manage Patient");
			System.out.println("3.Manage Appointment");
			System.out.println("4.Manage Bill");
			System.out.println("5.Manage Payment");
			System.out.println("Press 0 to Exit");
			System.out.println("--------------------------------------");
			System.out.print("please Select: ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				DoctorManagement.showMenu();
				break;

			case 2:
				PatientManagement.showMenu();
				break;
				
			case 3:
				AppointmentManagement.showMenu();
				break;
				
			case 4:
				BillManagement.showMenu();
				break;
				
			case 5:
				PaymentManagement.showMenu();
				break;

			default:
				System.exit(0);
				break;
			}
		} while (!(choice == 0));
	}

	public static void main(String[] args) {
		showMenu();
	}
}
