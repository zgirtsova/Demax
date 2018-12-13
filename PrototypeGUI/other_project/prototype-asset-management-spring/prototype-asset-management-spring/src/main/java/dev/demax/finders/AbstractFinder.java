package dev.demax.finders;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
 
public abstract class AbstractFinder { 
	private SessionFactory sessionFactory;
	 
	public AbstractFinder(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	 
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	 
	@SuppressWarnings("rawtypes")
	protected NativeQuery createNativeQuery(String queryString) {
		return getSession().createNativeQuery(queryString);
	}
	 
	@SuppressWarnings("rawtypes")
	protected <T> NativeQuery createNativeQuery(String queryString, Class<T> clazz) {
		return getSession().createNativeQuery(queryString, clazz);
	}
	
	public <T> void insert(T entity) {
		getSession().save(entity);
	}
	
	public <T> void update(T entity) {
		getSession().update(entity);
	}
	
	public <T> void delete(T entity) {
		getSession().delete(entity);
	}
}