package dev.demax.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class HibernateConfiguration {
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("dev.demax.entities" );
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}
 
	@Bean
	public DataSource dataSource() {
		try {
			ComboPooledDataSource dataSource = new ComboPooledDataSource();
			dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/prototype_spring");
			dataSource.setUser("postgres");
			dataSource.setPassword("1234");
			dataSource.setDriverClass("org.postgresql.Driver");
			 
			return dataSource;
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
 
		return null;
	}
 
	@Bean
	public PlatformTransactionManager hibernateTransactionManager() {
		HibernateTransactionManager transactionManager
			= new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}
 
	private Properties hibernateProperties() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty(
			"hibernate.hbm2ddl.auto", "validate");
		hibernateProperties.setProperty(
			"hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		hibernateProperties.setProperty(
			"hibernate.format_sql", "true");
		hibernateProperties.setProperty(
			"hibernate.temp.use_jdbc_metadata_defaults", "false");
		 
		return hibernateProperties;
	}
}
