package management_system;

import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;
public class LoginSystem {

	
	public static void main(String[] args) throws SQLException, InterruptedException {
		BankUser bUser = new BankUser();
		if (bUser.auth = true) {
			bankMain(bUser.uid, bUser);
		}
		else {
			bUser = new BankUser();
		}
		
	}
	
	
	
// Bank Main Method.	
	public static void bankMain(String uid, BankUser bUser) throws InterruptedException
	{
		System.out.println("\nWelcome "+uid+" please select an option!");
		Scanner bankSc = new Scanner(System.in);
		while(!bankSc.equals("4")) {
		System.out.println("\n====================="
				+ "\n1 -- Deposit"
				+ "\n====================="
				+ "\n2 -- Withdraw"
				+ "\n====================="
				+ "\n3 -- Check Balance"
				+ "\n====================="
				+ "\n4 -- Exit"
				+ "\n=====================");
		String userRes = bankSc.nextLine();

		try {
			if (userRes.equals("1")) {
				bUser.deposit();
				Thread.sleep(3000);
			}
			else if (userRes.equals("2")) {
				bUser.withdraw();
				Thread.sleep(3000);
			}
			else if (userRes.equals("3")) {
				bUser.checkBalance();
				Thread.sleep(3000);
			}
			else if (userRes.equals("4")) {
				System.out.println("Have a good day!");
				System.exit(0);
			}
			
		}
		
		catch(Exception e)
			{
				System.out.println("Error 002\n"+e);
			}

		}
		bankSc.close();
		}
	
}
	

