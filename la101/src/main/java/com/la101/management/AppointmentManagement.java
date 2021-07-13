package com.la101.management;

import java.util.Scanner;

import com.la101.services.AppointmentServices;
import com.la101.services.impl.AppointmentServicesImpl;



public class AppointmentManagement {
	
	static AppointmentServices appointmentServices = new AppointmentServicesImpl();
	
	public static void showMenu() {
		int choice;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.println("----------- Management ---------------");
			System.out.println("1.Add new Appointment");
			System.out.println("2.Show all Appointment");
			System.out.println("3.Back");
			System.out.println("0.Exit");
			System.out.println("------------------------------------------------------");
			System.out.print("please Select: ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				appointmentServices.addNewAppointment();

				break;

			case 2:
				appointmentServices.showAllAppointment();
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
