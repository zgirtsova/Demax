package dev.demax.finders;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.demax.entities.Product;
import dev.demax.entities.Project;
import util.Filter;
import util.Status;

@Component
public class ProjectFinder extends AbstractFinder {
	@Autowired
	public ProjectFinder(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public Project getById(UUID id) {
		return (Project) super.createNativeQuery("SELECT * FROM projects WHERE id = CAST(:id AS uuid)", 
			Project.class).setParameter("id", id).getSingleResult();
	}
	
	public Integer getCount(Filter filter) {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT COUNT(*) FROM projects ");
		
		if (filter.getFrom() != null || filter.getTo() != null || filter.getStatusId() != null) {
			filterCountQuery(sBuilder, filter);
		}
		
		return ((BigInteger) super.createNativeQuery(sBuilder.toString()).getSingleResult()).intValue();
	}

	public Integer getFinishedProjectsCount() {
		return ((BigInteger) super.createNativeQuery("SELECT COUNT(*) FROM projects WHERE status_id = :statusId")
						.setParameter("statusId", Status.getStatusIdByName("Finished")).getSingleResult()).intValue();
	}

	public Integer getNewProjectsCount() {
		return ((BigInteger) super.createNativeQuery("SELECT COUNT(*) FROM projects WHERE status_id = :statusId")
						.setParameter("statusId", Status.getStatusIdByName("New")).getSingleResult()).intValue();
	}
	
	public Project getProjectByName(String projectName) {
		return (Project) super.createNativeQuery("SELECT * FROM projects WHERE project_name = :projectName")
						.setParameter("projectName", projectName);
	}
	
	@SuppressWarnings("unchecked")
	public Set<Product> getProductsByProjectName(UUID id) {		
		return new HashSet<Product>(super.createNativeQuery(
						"SELECT * FROM products INNER JOIN projects ON products.project_id = projects.id WHERE project_id = :id")
						.setParameter("id", id).list());
	}
	
	private void filterCountQuery(StringBuilder sBuilder, Filter filter) {
		sBuilder.append("WHERE ");
		int counter = 0;
		
		if (filter.getFrom() != null) {
			sBuilder.append("created_on >= \'" +  filter.getFrom() + "\' ");
			counter++;
		}
		
		if (filter.getTo() != null) {
			if (counter > 0) {
				sBuilder.append("AND ");
			}
			sBuilder.append("created_on <= \'" + filter.getTo() + "\' ");
			counter++;
		}
		
		if (filter.getStatusId() != null) {
			if (counter > 0) {
				sBuilder.append("AND ");
			}
			sBuilder.append("status_id = " + filter.getStatusId());
		}
	}
}
