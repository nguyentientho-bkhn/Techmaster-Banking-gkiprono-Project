package com.kiprono.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kiprono.daos.TransactionsDAImpl;

public class DatabaseConnection {
	private static final Logger LOG = LogManager.getLogger(TransactionsDAImpl.class);
	private static Connection conn;
	// 	jdbc:postgresql://hostname:port/databaseName
	private static String URL;
	private static String USERNAME;
	private static String PASSWORD;
	private static String DATABASE, NEW_DATABASE;
	private static String HOST;

	static {
		LOG.info(String.valueOf(System.currentTimeMillis()) + ": properties file accessed");
		try {
			InputStream stream = ClassLoader.getSystemResourceAsStream("database.properties");
			Properties properties = new Properties();
			properties.load(new FileInputStream("database.properties"));
			String user = properties.getProperty("USER");
			String pwd = properties.getProperty("PASSWORD");
			String url = properties.getProperty("DATABASE");
			
			// get them from sys variables
			USERNAME = System.getenv(user);
			PASSWORD = System.getenv(pwd);
			DATABASE = System.getenv(url);
			NEW_DATABASE = formatURL();
			
		} catch (IOException e) {
			LOG.error(String.valueOf(System.currentTimeMillis()) + e.getMessage());
		}
	}
	// format url to capitalize first letter of database name
	private static String formatURL() {
		String[] url = DATABASE.split("/");
		
		String bank = url[url.length - 1];
		// capitalize first letter in bank
		char b [] = bank.toCharArray();
		b[0] = Character.toUpperCase(b[0]);
		
		bank = new String(b);
		url[url.length - 1] = bank;
		
		return String.join("/", url);
	}

	//get connection
	public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			LOG.error(String.valueOf(System.currentTimeMillis()) + e.getMessage());
		}
		
		try {
			if(conn == null || conn.isClosed()) {
				try {
					conn = java.sql.DriverManager.getConnection(NEW_DATABASE, USERNAME, PASSWORD);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					LOG.error(String.valueOf(System.currentTimeMillis()) + e.getMessage());
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.error(String.valueOf(System.currentTimeMillis()) + e.getMessage());
		}
		return conn;
	}

	// read properties file
	

	// generate connection
	@SuppressWarnings("unused")
	private static Connection generateConnection() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			if(conn == null || conn.isClosed()) {
				try {
					conn = java.sql.DriverManager.getConnection(URL, USERNAME, PASSWORD);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					LOG.error(String.valueOf(System.currentTimeMillis()) + e.getMessage());
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.error(String.valueOf(System.currentTimeMillis()) + e.getMessage());
		}
		return conn;
	}

	

}
