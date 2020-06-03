package com.hal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectionFactory {
	private static final String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private static final String DB_USER = "student";
	private static final String DB_PASSWORD = "1234";
	
	static Connection getConn() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	}

}
