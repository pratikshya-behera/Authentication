package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    public static Connection connection = null;
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Authentication?allowPublicKeyRetrieval=true&useSSL=false", "root", "Pratikshya_3622");
        } catch (ClassNotFoundException  e) {
            e.printStackTrace();
        }
        catch( SQLException e) {
        	e.printStackTrace();
        }
        System.out.println("Connection established!!!");
        return connection;
    }
    public static void closeConnection() {
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }


}

//package db;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class MyConnection {
//	
//	public static Connection connection;
//	
//	public static Connection getConnection() {
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Authentication?useSSL=false", "root", "Pratikshya_3622");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println("Connection established!");
//		return connection;
//	}
//	
//	public static void closeConnection() {
//		if(connection!=null) {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
////	public static void main(String args[]) {
////		MyConnection.getConnection();
////	}
//
//}
