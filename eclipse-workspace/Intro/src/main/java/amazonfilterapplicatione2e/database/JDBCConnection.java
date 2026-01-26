package main.java.amazonfilterapplicatione2e.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnection {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		String host = "localhost";
		String port = "3306";
		String dbName = "practice";

//		Connection connection = DriverManager.getConnection(
//		    "jdbc:mysql://" + host + ":" + port + "/" + dbName,
//		    "root",
//		    
//		);
		
//		Statement s= connection.createStatement();
//		
//		ResultSet  result=s.executeQuery("select * from test where id=3");
//		
//		while(result.next()) {
//		
//		result.getString("name");
//		
//		System.out.println(result.getString("name"));
//		}
//		
//		connection.close();
	}

}
