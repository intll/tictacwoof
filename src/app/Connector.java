package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public final class Connector {
	
	private final String USERNAME = "root";
	private final String PASSWORD = "";
	private final String DATABASE = "tictacwoof";
	private final String HOST = "localhost:3306";
	private final String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);
	
	private Connection con;
	private Statement st;
	private static Connector connectorInstance;
	
	private Connector() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
			st = con.createStatement();
			System.out.println("[i] Database connected successfully");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[!] Fatal Error: Database Connection Failure");
			System.exit(1);
		}
	}
	
	public static synchronized Connector getConnection() {
		return connectorInstance = (connectorInstance == null)  ? new Connector() : connectorInstance;
	}
	
	public ResultSet executeQuery(String sqlQuery) {
		try {
			return st.executeQuery(sqlQuery);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void executeUpdate(String sqlQuery) {
		try {
			st.executeUpdate(sqlQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public PreparedStatement prepareStatement(String sqlQuery) {
		try {
			return con.prepareStatement(sqlQuery);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
