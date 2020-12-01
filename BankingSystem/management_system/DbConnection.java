package management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConnection {
	
	public Connection con;
	public ResultSet rs;
	public PreparedStatement pst;
	
	public static Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con =
					DriverManager.getConnection("jdbc:mysql://localhost:3306/bankinfo", "root", "1243");
//			System.out.println("Connected to DB!");
		}
		catch (ClassNotFoundException | SQLException e) {
			System.out.println(e+"");
		}
		
		return con;
	}

}