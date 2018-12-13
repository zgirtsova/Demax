package dev.demax.finders;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.demax.entities.User;

@Component
public class UserFinder extends AbstractFinder {
	@Autowired
	public UserFinder(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public User getByEmail(String email) {
		User user = (User) super.createNativeQuery("SELECT * FROM users WHERE email = :email", User.class).setParameter("email", email)
						.getSingleResult();
		return user;
	}
}
