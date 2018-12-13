package util;

import java.sql.Connection;
import java.sql.SQLException;

import org.postgresql.ds.PGConnectionPoolDataSource;

public class CurrentConnection {
	private static final String SERVER_NAME = "127.0.0.1";
	private static final String DB_NAME = "prototype";
	private static final String DB_USERNAME = "postgres";
	private static final String DB_PASSWORD = "1234";
	private static final int PORT = 5432;
	private static PGConnectionPoolDataSource CONNECTION_POOL;
	private static final ThreadLocal<Connection> CURRENT = new ThreadLocal<Connection>();
	private static final ThreadLocal<Integer> TRANSACTION_LEVEL = new ThreadLocal<Integer>();

	// ------------------------------------------------------------------------------
	public static Connection getCurrent() throws SQLException {
		if (CURRENT.get() == null || CURRENT.get().isClosed()) {
			CURRENT.set(getInstance().getConnection());
		}
		return CURRENT.get();
	}

	// ------------------------------------------------------------------------------
    private static PGConnectionPoolDataSource getInstance() 
        throws SQLException{
		if (CONNECTION_POOL == null) {
			connectionPoolInit();
		}

		return CONNECTION_POOL;
	}

    // ------------------------------------------------------------------------------
    public static void beginRequest() throws SQLException {
        if(TRANSACTION_LEVEL.get()==null){
            TRANSACTION_LEVEL.set(0);
        }
        if(TRANSACTION_LEVEL.get()==0){
            getCurrent().setAutoCommit(false);
        }
        TRANSACTION_LEVEL.set(TRANSACTION_LEVEL.get()+1);
    }
	// ------------------------------------------------------------------------------
    private static void connectionPoolInit() 
        throws SQLException{
		CONNECTION_POOL = new PGConnectionPoolDataSource();
		CONNECTION_POOL.setServerName(SERVER_NAME);
		CONNECTION_POOL.setDatabaseName(DB_NAME);
		CONNECTION_POOL.setUser(DB_USERNAME);
		CONNECTION_POOL.setPassword(DB_PASSWORD);
		CONNECTION_POOL.setPortNumber(PORT);
        CONNECTION_POOL.setPreparedStatementCacheQueries(100);
        CONNECTION_POOL.setPrepareThreshold(1);
	}

	// ------------------------------------------------------------------------------
	public static void rollback() throws SQLException {
		if (TRANSACTION_LEVEL.get() == 1) {
			getCurrent().rollback();
            getCurrent().close();
		}
		TRANSACTION_LEVEL.set(TRANSACTION_LEVEL.get() - 1);
	}

	// ------------------------------------------------------------------------------
	public static void commit() throws SQLException {
		if (TRANSACTION_LEVEL.get() == 1) {
			getCurrent().commit();
            getCurrent().close();
		}
		TRANSACTION_LEVEL.set(TRANSACTION_LEVEL.get() - 1);
	}
}