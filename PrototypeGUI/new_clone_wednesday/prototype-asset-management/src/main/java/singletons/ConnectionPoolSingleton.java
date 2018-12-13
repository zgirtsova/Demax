package singletons;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public final class ConnectionPoolSingleton{
    private ThreadLocal<Connection> currConn = new ThreadLocal<>();
    private static ThreadLocal<Integer> trxCounter = new ThreadLocal<>();


    //eager loading singleton
    private static ConnectionPoolSingleton _instance = null;

    private ComboPooledDataSource cpds = null;

    private ConnectionPoolSingleton() {

        cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass("org.postgresql.Driver");
            cpds.setJdbcUrl("jdbc:postgresql://localhost/prototype");
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getCurrConn() throws SQLException {

        if (this.currConn.get() == null) {
            this.currConn.set(this.cpds.getConnection());
        }
        return this.currConn.get();
    }

    public static ConnectionPoolSingleton getInstance() {
        if (_instance == null)
            _instance = new ConnectionPoolSingleton();
        return _instance;
    }

    public void releaseCurrConn() {
        this.currConn.remove();
    }


    public void begin() {
        Connection conn = this.currConn.get();
        if (conn == null) {
            try {
                conn = this.cpds.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            currConn.set(conn);
        }
        if(trxCounter.get() == null){
            trxCounter.set(0);
        }
        trxCounter.set(trxCounter.get()+1);
    }

    public void commit() {
        if (trxCounter.get() == 1) {
            Connection conn = this.currConn.get();
            this.currConn.remove();
            if (conn == null) {
                throw new IllegalStateException("Transaction is not active");
            }
            try {
                conn.commit();
            }catch (Exception exp) {
                exp.printStackTrace();
            }
            finally { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
        trxCounter.set(trxCounter.get() - 1);

    }

    public void rollback() {
        if (trxCounter.get() == 1) {
            Connection connection = this.currConn.get();
            this.currConn.remove();
            if (connection == null) {
                throw new IllegalStateException("Transaction is not active");
            }
            this.currConn.remove();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        trxCounter.set(trxCounter.get() - 1);
    }
}