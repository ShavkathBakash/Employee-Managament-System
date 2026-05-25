package com.dynamic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Dynamic {
	Scanner sc = new Scanner(System.in);

	public void createTable() {
		String q = "create table employee(id int(6), name varchar(20), salary int(20), address varchar(20))";
		try {
			Connection con = ConnectionProvider.getConnection();

			Statement stmt = con.createStatement();
			stmt.execute(q);
			System.out.println("table created successfully");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void createEmployee() {
		while (true) {
			String q = "insert into employee values(?,?,?,?)";
			try {
				System.out.println("Enter id, name, salary, address respectively : ");
				Employee e = new Employee(sc.nextInt(), sc.next(), sc.nextLong(), sc.next());
				Connection con = ConnectionProvider.getConnection();
				PreparedStatement pstmt = con.prepareStatement(q);
				pstmt.setInt(1, e.getId());
				pstmt.setString(2, e.getName());
				pstmt.setLong(3, e.getSalary());
				pstmt.setString(4, e.getAddress());
				pstmt.execute();
				System.out.println("New row inserted successfully !!");
				con.close();

			} catch (Exception e1) {

				e1.printStackTrace();
			}
			System.exit(4);
		}
	}

	public void readEmployee() {
		String q = "select * from employee where id=?";
		System.out.println("Enter id you want to read particular employee : ");
		int id = sc.nextInt();
		Connection con = ConnectionProvider.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(q);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Employee e = new Employee();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setSalary(rs.getLong(3));
				e.setAddress(rs.getString(4));
				System.out.println(e);
			}
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void updateEmployee() {
		System.out.println("Enter id you to update address : ");
		int id = sc.nextInt();
		System.out.println("Enter new address : ");
		String address = sc.next();
		String q = "update employee set address=? where id=?";
		Connection con = ConnectionProvider.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(q);
			pstmt.setString(1, address);
			pstmt.setInt(2, id);

			pstmt.executeUpdate();
			System.out.println("Address updated successfully ");
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteEmployee() {
		String q = "delete from employee where id=?";
		System.out.println("Enter id you want to delete :");
		int id = sc.nextInt();
		try {
			PreparedStatement pstmt = ConnectionProvider.getConnection().prepareStatement(q);
			pstmt.setInt(1, id);
			pstmt.execute();
			System.out.println("Record deleted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
