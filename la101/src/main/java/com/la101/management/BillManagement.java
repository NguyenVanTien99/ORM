package com.la101.management;

import java.util.Scanner;

import com.la101.services.BillServices;
import com.la101.services.impl.BillServicesImpl;

public class BillManagement {

	static BillServices billServices = new BillServicesImpl();

	public static void showMenu() {
		int choice;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.println("----------- Management ---------------");
			System.out.println("1.Add new Bills");
			System.out.println("2.Show all bills");
			System.out.println("3.Find Bill By Date");
			System.out.println("4.Back");
			System.out.println("0.Exit");
			System.out.println("------------------------------------------------------");
			System.out.print("please Select: ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				billServices.addNewBill();

				break;

			case 2:

				billServices.showAllbill();

				break;

			case 3:

				billServices.findByDate();

				break;

			case 4:
				Management.showMenu();
				break;

			default:
				choice = 0;
				break;
			}

		} while (!(choice == 0));
	}
}
