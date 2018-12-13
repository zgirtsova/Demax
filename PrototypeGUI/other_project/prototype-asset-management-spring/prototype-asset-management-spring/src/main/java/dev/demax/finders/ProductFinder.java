package dev.demax.finders;

import java.math.BigInteger;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.Filter;
import util.Status;

@Component
public class ProductFinder extends AbstractFinder {	
	@Autowired
	public ProductFinder(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public Integer getCount(Filter filter) {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT COUNT(*) FROM products ");
		
		if (filter.getFrom() != null || filter.getTo() != null || filter.getStatusId() != null || filter.getProjectId() != null) {
			filterCountQuery(sBuilder, filter);
		}
		
		return ((BigInteger) super.createNativeQuery(sBuilder.toString()).getSingleResult()).intValue();
	}

	public Integer getFinishedProductsCount() {
		return ((BigInteger) super.createNativeQuery("SELECT COUNT(*) FROM products WHERE status_id = :statusId")
						.setParameter("statusId", Status.getStatusIdByName("Finished")).getSingleResult()).intValue();
	}

	public Integer getProductsInProgressCount() {
		return ((BigInteger) super.createNativeQuery("SELECT COUNT(*) FROM products WHERE status_id = :statusId")
						.setParameter("statusId", Status.getStatusIdByName("In progress")).getSingleResult()).intValue();
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
			sBuilder.append("status_id = " + filter.getStatusId() + " ");
			counter++;
		}
		
		if (filter.getProjectId() != null) {
			if (counter > 0) {
				sBuilder.append("AND ");
			}
			sBuilder.append("project_id = " + filter.getProjectId() + " ");
		}
	}
}
