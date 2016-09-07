package br.com.onpecas.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnect {


	public static Connection ConectarDb(){

		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager
					.getConnection("jdbc:mysql://db4free.net:3306/csoptcc", "csop", "csoptcc");

		} catch (Exception ex) {
			// TODO Auto-generated catch block
			System.out.println("SQLException: " + ex.getMessage());
		}

		return con;

	}
}
