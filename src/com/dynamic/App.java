package com.dynamic;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		Dynamic d = new Dynamic();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter 1 to create new employee record");
		System.out.println("Enter 2 to read employee record");
		System.out.println("Enter 3 to update employee record");
		System.out.println("Enter 4 to delete employee record");
		int btn = sc.nextInt();
		// d.createTable();
		switch (btn) {

		case 1:
			d.createEmployee();
		case 2:
			d.readEmployee();
		case 3:
			d.updateEmployee();
		case 4:
			d.deleteEmployee();
		}
	}
}