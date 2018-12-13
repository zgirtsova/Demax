package com.sizenith.poc.context;

import java.util.HashMap;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sizenith.poc.dao.LoginDAO;
import com.sizenith.poc.service.LoginService;
import com.sizenith.poc.transaction.ITransactionManager;
import com.sizenith.poc.transaction.impl.SimpleTransactionManager;
import com.sizenith.poc.transaction.impl.TransactionalDataSource;

public class BeanFactory {

	public static enum BeanName {
		TRANSACTION_MANAGER, DATA_SOURCE, LOGIN_DAO, LOGIN_SERVICE
	}

	private HashMap<BeanName, Object> beans = new HashMap<>();

	public BeanFactory() throws Exception {
		try {
			createTransactionManager();
			createTransactionalDataSource();
			createLoginDAO();
			createLoginService();
		} catch (Exception e) {
			throw new BeanFactoryException("Unable to initialize BeanFactory: " + e.getMessage(), e);
		}
	}
	
	public Object getBean(BeanName beanName) {
		return beans.get(beanName); 
	}

	private void createTransactionManager() throws Exception {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setDriverClass("org.postgresql.Driver");
		cpds.setJdbcUrl("jdbc:postgresql://localhost/postgres");
		cpds.setUser("postgres");
		cpds.setPassword("1234");
		// Optional Settings
		cpds.setInitialPoolSize(5);
		cpds.setMinPoolSize(5);
		cpds.setAcquireRetryAttempts(3);
		cpds.setAcquireIncrement(1);
		cpds.setMaxPoolSize(20);
		cpds.setMaxStatements(100);
		cpds.setIdleConnectionTestPeriod(100);
		cpds.setPreferredTestQuery("select 1");
		cpds.getConnection();
		
		ITransactionManager trxManager = new SimpleTransactionManager(cpds);
		beans.put(BeanName.TRANSACTION_MANAGER, trxManager);
	}
	
	private void createTransactionalDataSource() {
		SimpleTransactionManager trxManager = (SimpleTransactionManager) beans.get(BeanName.TRANSACTION_MANAGER);
		TransactionalDataSource dataSource = new TransactionalDataSource(trxManager);
		beans.put(BeanName.DATA_SOURCE, dataSource);
	}

	private void createLoginDAO() {
		beans.put(BeanName.LOGIN_DAO, new LoginDAO((DataSource) beans.get(BeanName.DATA_SOURCE)));
	}

	private void createLoginService() {
		LoginService loginService = new LoginService(
				(LoginDAO) beans.get(BeanName.LOGIN_DAO), 
				(SimpleTransactionManager) beans.get(BeanName.TRANSACTION_MANAGER));
		beans.put(BeanName.LOGIN_SERVICE, loginService);
				
	}
	
	

}
