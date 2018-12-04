package singletons;

import org.postgresql.jdbc2.optional.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.HashSet;

public final class ConnectionPoolSingleton {
    private static final String SERVER_NAME = "127.0.0.1";
    private static final String DB_NAME = "prototype";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "1234";
    private static final int PORT = 5432;

    private static ConnectionPool CONNECTION_POOL = getInstance();

    private static ArrayDeque<Connection> freeConnections = initFreeConnections();
    private static HashSet<Connection> occupiedConnections = new HashSet<>(3);
    private static ThreadLocal<Connection> threadLocalConnection = new ThreadLocal<>();
    private static final Object lock = new Object();

//    private static ArrayDeque<Thread> threads = new ArrayDeque<>();

    public static ConnectionPool getInstance() {
        if (CONNECTION_POOL == null) {
            connectionPoolInit();
        }

        return CONNECTION_POOL;
    }

    private static ArrayDeque<Connection> initFreeConnections() {
        try {
            ArrayDeque<Connection> connections = new ArrayDeque<>(3);
            connections.add(CONNECTION_POOL.getConnection());
            connections.add(CONNECTION_POOL.getConnection());
            connections.add(CONNECTION_POOL.getConnection());

            return connections;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Connection getConnection() throws InterruptedException {
        while (freeConnections.size() == 0) {
            //Thread thread = Thread.currentThread();
            synchronized (lock) {
                //threads.add(thread);
                lock.wait();
            }
        }

        Connection connection = freeConnections.poll();
        threadLocalConnection.set(connection);
        occupiedConnections.add(connection);
        return connection;
    }

    public static void retrieveConnection() {
        Connection connection = threadLocalConnection.get();
        synchronized (lock) {
            occupiedConnections.remove(connection);
            freeConnections.addLast(connection);
            lock.notify();
        }

    }

    private static void connectionPoolInit() {
        CONNECTION_POOL = new ConnectionPool();
        CONNECTION_POOL.setServerName(SERVER_NAME);
        CONNECTION_POOL.setDatabaseName(DB_NAME);
        CONNECTION_POOL.setUser(DB_USERNAME);
        CONNECTION_POOL.setPassword(DB_PASSWORD);
        CONNECTION_POOL.setPortNumber(PORT);
        CONNECTION_POOL.setDefaultAutoCommit(true);
    }
}