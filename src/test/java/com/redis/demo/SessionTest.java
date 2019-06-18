package com.redis.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class SessionTest {
	
	protected static final String DB_URL = null;
	private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>() {
		public Connection initialValue() {
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(DB_URL);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return conn;
		}
	};
	
	public static Connection getConnection() {
		return connectionHolder.get();
	}
	
	

	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		System.out.println(list.size());
	}

}
