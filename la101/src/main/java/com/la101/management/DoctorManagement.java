package com.la101.management;

import java.util.Scanner;

import com.la101.services.DoctorServices;
import com.la101.services.impl.DoctorServicesImpl;

public class DoctorManagement {

	static DoctorServices doctorServices = new DoctorServicesImpl();

	public static void showMenu() {
		int choice;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.println("----------- Management ---------------");
			System.out.println("1.Add new Doctor");
			System.out.println("2.Show all Doctor");
			System.out.println("3.Update Doctor");
			System.out.println("4.Delete Doctor");
			System.out.println("5.Back");
			System.out.println("0.Exit");
			System.out.println("------------------------------------------------------");
			System.out.print("please Select: ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				doctorServices.addNewDoctor();
				break;

			case 2:
				doctorServices.showAllDoctor();
				break;

			case 3:
				doctorServices.updateDoctor();
				break;

			case 4:
				doctorServices.DeleteDoctor();
				break;

			case 5:
				Management.showMenu();
				break;

			default:
				choice = 0;
				break;
			}

		} while (!(choice == 0));
	}
}
