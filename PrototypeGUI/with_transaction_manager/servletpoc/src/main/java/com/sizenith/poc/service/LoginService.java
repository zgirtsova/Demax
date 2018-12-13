package com.sizenith.poc.service;

import com.sizenith.poc.dao.LoginDAO;
import com.sizenith.poc.domain.User;
import com.sizenith.poc.transaction.ITransactionManager;

public class LoginService {

	private LoginDAO loginDAO;
	private ITransactionManager trxManager;

	public LoginService(LoginDAO loginDAO, ITransactionManager trxManager) {
		this.loginDAO = loginDAO;
		this.trxManager = trxManager;
	}

	public User findUserByEmail(String email, String password) throws Exception {
		User user = null;
		trxManager.begin();
		try {
			user = loginDAO.findUserByEmail(email, password);
			trxManager.commit();
		} catch (Exception e) {
			e.printStackTrace(System.out);
			trxManager.rollback();
			throw e;
		}
		return user;
	}
}
