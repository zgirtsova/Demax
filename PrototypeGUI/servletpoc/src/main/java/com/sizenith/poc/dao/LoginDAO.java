package com.sizenith.poc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import com.sizenith.poc.domain.User;

public class LoginDAO {
	
	private DataSource dataSource;
	
	public LoginDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public User findUserByEmail(String email, String password) {
		User user = null;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
		    stmt = con.prepareStatement("SELECT * FROM public.\"User\" where email=? and password=?");
		    stmt.setString(1, email);
		    stmt.setString(2, password);
		    rs = stmt.executeQuery();
		    if (rs.next()) {
		    	user = new User();
		    	user.setEmail(rs.getString("email"));
		    	user.setPassword(rs.getString("password"));
		    }
		} catch(Exception e) {
			e.printStackTrace(System.out);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();					
				}
				if (con != null) {
					con.close();
				}
			} catch(Exception e) {				
			}
		}
		return user;
	}
	
	
}
