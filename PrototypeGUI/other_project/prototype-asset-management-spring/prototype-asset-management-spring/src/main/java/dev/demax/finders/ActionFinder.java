package dev.demax.finders;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.demax.entities.Action;

@Component
public class ActionFinder extends AbstractFinder {
	@Autowired
	public ActionFinder(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	public Set<Action> getLastFifteenActions(UUID id) {
		return new HashSet<Action>(super.createNativeQuery("SELECT * FROM actions WHERE user_id = :id",
						Action.class).setParameter("id", id).list());
	}
}
