package com.sizenith.poc.service;

import com.sizenith.poc.dao.LoginDAO;
import com.sizenith.poc.domain.User;

public class LoginService {
	
	private LoginDAO loginDAO;
	
	public LoginService(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}
	
	public User findUserByEmail(String email, String password) {
		return loginDAO.findUserByEmail(email, password);
	}
}
