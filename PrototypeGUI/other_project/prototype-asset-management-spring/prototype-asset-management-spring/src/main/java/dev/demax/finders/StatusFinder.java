package dev.demax.finders;

import java.util.UUID;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.demax.entities.Status;

@Component
public class StatusFinder extends AbstractFinder {
	@Autowired
	public StatusFinder(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public Status getStatusById(UUID id) {
		return (Status) super.createNativeQuery("SELECT * FROM statuses WHERE id = :id", 
						Status.class).setParameter("id", id).getSingleResult();
	}
}
