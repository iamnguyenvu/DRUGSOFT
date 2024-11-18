/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connectDB;

/**
 *
 * @author HP
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class connectDB {
	public static Connection accessDataBase() {
		Connection con = null;
		try {
			String url = "jdbc:sqlserver://localhost:1433;databasename=DRUGSOFT;encryty=false;";
			String user = "sa";
			String password = "sapasssword";

			con =  DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return con;
	}
}

