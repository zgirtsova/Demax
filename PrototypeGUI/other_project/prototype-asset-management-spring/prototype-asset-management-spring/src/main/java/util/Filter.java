package util;

import java.sql.Timestamp;
import java.util.UUID;

public class Filter {
	private Timestamp from;
	private Timestamp to;
	private UUID statusId;
	private UUID projectId;
	private Byte sortRule;
	private Integer page;
	private Integer perPage;

	public Filter(Timestamp from, Timestamp to, UUID statusId, UUID projectId, Integer page, 
					Integer perPage, Boolean firstColumn, Boolean secondColumn, Boolean priority) {
		this.from = from;
		this.to = to;
		this.statusId = statusId;
		this.projectId = projectId;
		this.sortRule = setSortRule(firstColumn, secondColumn, priority);
		this.page = page;
		this.perPage = perPage;
	}	

	public Timestamp getFrom() {
		return from;
	}

	public Timestamp getTo() {
		return to;
	}

	public UUID getStatusId() {
		return statusId;
	}

	public UUID getProjectId() {
		return projectId;
	}
	
	public Byte getSortRule() {
		return sortRule;
	}
	
	// lookup the sortRule to use for first and second Columns true is ASC and false is DECS
	// for priority True is first and False is second
	private Byte setSortRule(Boolean firstColumn, Boolean secondColumn, Boolean priority) {
		if (firstColumn != null && secondColumn != null && priority != null) {
			if (firstColumn && secondColumn && priority) {
				return 0;
			} else if (firstColumn && !secondColumn && priority) {
				return 1;
			} else if (!firstColumn && secondColumn && priority) {
				return 2;
			} else if (!firstColumn && !secondColumn && priority) {
				return 3;
			} else if (firstColumn && secondColumn && !priority) {
				return 4;
			} else if (!firstColumn && secondColumn && !priority) {
				return 5;
			} else if (firstColumn && !secondColumn && !priority) {
				return 6;
			} else if (!firstColumn && !secondColumn && !priority) {
				return 7;
			} else {
				return 1;
			}
		} else {
			return 1;
		}
	}

	public Integer getPage() {
		return page;
	}

	public Integer getPerPage() {
		return perPage;
	}
}