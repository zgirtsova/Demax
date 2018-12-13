package com.sizenith.poc.transaction;

import java.sql.SQLException;

public interface ITransactionManager {

	public void begin() throws SQLException;

	public void commit() throws SQLException;

	public void rollback() throws SQLException;

}
