package listeners;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebAppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass("org.postgresql.Driver");
            cpds.setJdbcUrl("jdbc:postgresql://localhost:5432/prototype");
            cpds.setUser("postgres");
            cpds.setPassword("1234");
            // Optional Settings
            cpds.setInitialPoolSize(5);
            cpds.setMinPoolSize(5);
            cpds.setAcquireRetryAttempts(10);
            cpds.setAcquireIncrement(1);
            cpds.setMaxPoolSize(10);
            cpds.setMaxStatements(100);
            cpds.setIdleConnectionTestPeriod(100);
            cpds.setPreferredTestQuery("select 1");
            //cpds.getConnection();

            servletContextEvent.getServletContext().setAttribute("DataSource", cpds);

            //TODO init all services and daos
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ComboPooledDataSource cpds = (ComboPooledDataSource) servletContextEvent.getServletContext()
                .getAttribute("DataSource");
        if (cpds != null) {
            cpds.close();
        }

    }
}
