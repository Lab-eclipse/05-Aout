package com.etudiants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

import javax.swing.JOptionPane;

public class ConnexionMysql {
	
	Connection conn = null;
	
	public static Connection conneexioDB() {
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn =DriverManager.getConnection("jdbc:mysql://mysql-crazy.alwaysdata.net/crazy_java", "crazy", "ibrahimsogore135@");
			return conn;
		} catch (Exception e)
		{
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
		
		
	}

}
