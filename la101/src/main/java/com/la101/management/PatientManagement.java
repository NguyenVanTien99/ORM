package com.la101.management;

import java.util.Scanner;

import com.la101.services.DoctorServices;
import com.la101.services.PatientServices;
import com.la101.services.impl.DoctorServicesImpl;
import com.la101.services.impl.PatientServicesImpl;

public class PatientManagement {
	static PatientServices patientServices = new PatientServicesImpl() ;
	
	public static void showMenu() {
		int choice;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.println("----------- Management ---------------");
			System.out.println("1.Add new Patient");
			System.out.println("2.Show all Patient");
			System.out.println("3.Back");
			System.out.println("0.Exit");
			System.out.println("------------------------------------------------------");
			System.out.print("please Select: ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				patientServices.addNewPatient();
				break;

			case 2:
				patientServices.showAllPatient();
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
