package com.sizenith.poc.transaction.impl;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class TransactionalDataSource implements DataSource {

    private final SimpleTransactionManager trxManager;

    public TransactionalDataSource(SimpleTransactionManager trxManager) {
        this.trxManager = trxManager;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return trxManager.getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        throw new UnsupportedOperationException();
    }

    // delegate to DataSource
    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return trxManager.getDataSource().unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return trxManager.getDataSource().isWrapperFor(iface);
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return trxManager.getDataSource().getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
    	trxManager.getDataSource().setLogWriter(out);
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
    	trxManager.getDataSource().setLoginTimeout(seconds);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return trxManager.getDataSource().getLoginTimeout();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return trxManager.getDataSource().getParentLogger();
    }

}
