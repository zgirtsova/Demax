package com.sizenith.poc.transaction.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.sizenith.poc.transaction.ITransactionManager;

public class SimpleTransactionManager implements ITransactionManager {

	private final ThreadLocal<TransactionalConnection> connections = new ThreadLocal<>();
	private final DataSource dataSource;

	public SimpleTransactionManager(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	DataSource getDataSource() {
		return this.dataSource;
	}
	
	Connection getConnection() throws SQLException {
		Connection connection = this.connections.get();
		if (connection == null) {
			return this.dataSource.getConnection();
		}
		return connection;
	}

	@Override
	public void begin() throws SQLException {
		Connection connection = this.connections.get();
		if (connection == null) {
			connection = this.dataSource.getConnection();
			if (connection.getAutoCommit()) {
				connection.setAutoCommit(false);
			}
			connections.set(new TransactionalConnection(connection));
		}
	}

	@Override
	public void commit() throws SQLException {
		Connection connection = this.connections.get();
		this.connections.remove();
		if (connection == null) {
			throw new IllegalStateException("Transaction is not active");
		}
		try {
			connection.commit();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void rollback() throws SQLException {
		Connection connection = this.connections.get();
		this.connections.remove();
		if (connection == null) {
			throw new IllegalStateException("Transaction is not active");
		}
		this.connections.remove();
		try {
			connection.rollback();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//trxManager.begin();
//	Connection conn = ConnectionPoolSingleton.getInstance().getCurrConn();
//        if (conn.getAutoCommit()) {
//		conn.setAutoCommit(false);
//	}
//
//        try {
//		dashboardDTO.setNewProjectsCount(projectDao.getNewProjectsCount());
//		dashboardDTO.setFinishedProjectsCount(projectDao.getFinishedProjectsCount());
//		dashboardDTO.setProductsInProgressCount(productDao.getProductsInProgressCount());
//		dashboardDTO.setFinishedProductsCount(productDao.getFinishedProductsCount());
//		dashboardDTO.setLastFifteenActions(actionService.getActions(userId));
//
//		//trxManager.commit();
//		try {
//			conn.commit();
//			ConnectionPoolSingleton.getInstance().releaseCurrConn();
//		} finally { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
//	} catch (Exception e) {
//		e.printStackTrace(System.out);
//		try {
//			conn.rollback();
//			ConnectionPoolSingleton.getInstance().releaseCurrConn();
//		} finally { try { conn.close(); } catch (SQLException ex) { ex.printStackTrace(); } }
//		throw e;
//	}
//
//        return dashboardDTO;
//}
//}
}
