package edu.uta.database;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Command {
	abstract LoadObject execute();

	/**
	 *
	 * @return Connection
	 */
	public Connection getDBConnection() {
		Connection con = null;
		/*try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// System.out.println("Registered DB driver!");

			// Connection is established
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/domainmodel", "root", "");
			// System.out.println("Established DB connection\n");

		} catch (Exception e) {
			System.out.println("Cannot connect to database");
			e.printStackTrace();

		}*/
		return con;
	}

	/**
	 * @param query
	 * @return result
	 */
	public int queryExecute(String query) {
		int result = 0;
		try {
			Connection connection = getDBConnection();
			Statement statement = connection.createStatement();
			result = statement.executeUpdate(query);
			connection.close();
		} catch (Exception e) {
			System.out.println("Error executing query");
		}
		return result;

	}

	/**
	 * @param query
	 * @return rs
	 */
	public ResultSet queryFetch(String query) {
		ResultSet rs = null;
		// int returnValue=0;
		try {
			Connection connection = getDBConnection();
			Statement statement = connection.createStatement();
			rs = statement.executeQuery(query);
			// connection.close();
		} catch (Exception e) {
			System.out.println("Error executing query");
		}
		return rs;

	}

	/**
	 * @param con
	 * @return
	 */
	public void closeDBConnection(Connection con) {
		try {
			con.close();
			System.out.println("Closed DB Connection\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param tablename
	 * @return
	 */
	public void clearTables(String tablename) {
		String delClass = "delete from domainmodel." + tablename;
		queryExecute(delClass);
	}
}
