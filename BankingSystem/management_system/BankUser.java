package management_system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BankUser {
	public String uid;
	private String pin;
	private int bal;
	public boolean auth = false;
	public boolean running = true;
	private Scanner input = new Scanner(System.in);
	private Connection con;
	public BankUser() throws SQLException {
		
		DbConnection usrDB = new DbConnection();
		this.con = DbConnection.connect();
		
		
		System.out.println("Please Enter your User ID: ");
		this.uid = input.nextLine();
		
		System.out.println("Please Enter your Pin: ");
		this.pin = input.nextLine();
		
		verifyLogin();
	}
	
	
	public void verifyLogin() throws SQLException {
//		DbConnection usrDB = new DbConnection();
//		Connection con = DbConnection.connect();
		ResultSet rs;
		PreparedStatement pst;

		try {
			String SQLString = "SELECT * FROM usrinfo WHERE uid=? AND pin=?";
			pst = con.prepareStatement(SQLString);
			pst.setString(1, uid);
			pst.setString(2, pin);
			rs = pst.executeQuery();

			if (rs.next()) {
				System.out.println("\n\n\n\n\n\n\n\n\n\n>> User Authenticated\n");
				auth = true;
			}
			else {
				System.out.println("\n\n\n\n\n\n\n\n\n\n>> Authentication Failed!");
				auth = false;
				new BankUser();
			}
//			con.close();
		}
		catch(Exception e) {
			System.out.println(e+"");
		}
	}
	
	public int getBalance() throws SQLException {
		ResultSet rs;
		PreparedStatement pst;
		
		try {
			String balSQL = "SELECT bal FROM usrinfo WHERE uid=? AND pin=?";
			pst = con.prepareStatement(balSQL);
			pst.setString(1, uid);
			pst.setString(2, pin);
			rs = pst.executeQuery();
			rs.next();
			bal = rs.getInt("bal");

		}
		catch(Exception e) {
			System.out.print(e+"");
		}
		return bal;
	}
	
	
	public int checkBalance() throws SQLException {
		
		try {
			getBalance();
			System.out.println("Your Current Balance is: $"+bal);
		}
		catch(Exception e) {
			System.out.print(e+"");
		}
		return bal;
	}
	
	
	public int deposit() throws SQLException {
		PreparedStatement pst;
		
		getBalance();
		System.out.println("\n>> Enter your Deposit Amount: ");
		int depAmt = input.nextInt();
		try {
			int newBal = depAmt + bal;
			String depSQL = "UPDATE usrinfo SET bal=? WHERE uid=? AND pin=?";
			pst = con.prepareStatement(depSQL);
			pst.setInt(1, newBal);
			pst.setString(2, uid);
			pst.setString(3, pin);
			pst.executeUpdate();
			System.out.println("You have deposited: $"+depAmt+"\nYour new balance is: $"+newBal);
		
		
		}
		catch(Exception e) {
			System.out.print(e+"");
		}
		
		return bal;
	}
	public int withdraw() throws SQLException {
		PreparedStatement pst;
		
		getBalance();
		System.out.println(">> Enter your Withdraw Amount: ");
		int wthAmt = input.nextInt();
		try {
			int newBal = bal - wthAmt;
			String wthSQL = "UPDATE usrinfo SET bal=? WHERE uid=? AND pin=?";
			pst = con.prepareStatement(wthSQL);
			pst.setInt(1, newBal);
			pst.setString(2, uid);
			pst.setString(3, pin);
			pst.executeUpdate();
			System.out.println("You have withdrawed: $"+wthAmt+"\nYour new balance is: $"+newBal);
		
		
		}
		catch(Exception e) {
			System.out.print(e+"");
		}
		
		return bal;
	}
}
