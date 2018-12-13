package com.sizenith.poc.context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sizenith.poc.context.BeanFactory.BeanName;

@WebListener
public class WebAppContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		try {
			BeanFactory beanFactory = new BeanFactory();
			servletContextEvent.getServletContext().setAttribute("BeanFactory", beanFactory);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		BeanFactory beanFactory = (BeanFactory) servletContextEvent.getServletContext().getAttribute("BeanFactory");
		if (beanFactory != null) {
			ComboPooledDataSource ds = (ComboPooledDataSource) beanFactory.getBean(BeanName.DATA_SOURCE);
			if (ds != null) {
				ds.close();
			}
		}
	}
}
