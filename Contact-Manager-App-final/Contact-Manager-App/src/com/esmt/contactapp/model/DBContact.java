package com.esmt.contactapp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContact {
	
	private static Connection connect = null;
	public static Connection getConnection () throws Exception { 
		try { 
			// Chargement du driver jdbc mysql 
			Class.forName ("com.mysql.cj.jdbc.Driver");
			// Ouverture de la connexion 
			connect = DriverManager.getConnection ("jdbc:mysql://localhost/contact_db?serverTimezone=UTC", "root", "");
			return connect; 
		}catch (ClassNotFoundException e){ 
			throw new Exception ("Driver Class not found : '" + e.getMessage () + "' !"); 
		}
		catch (SQLException e){ 
			
			throw new Exception ("Error : Unable to open connection with database !"+e.getMessage()); }
	}
	

}
