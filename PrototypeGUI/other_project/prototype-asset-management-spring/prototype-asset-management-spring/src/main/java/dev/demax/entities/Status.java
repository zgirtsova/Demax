package dev.demax.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "statuses")
public class Status extends BaseEntity {
	@Column(columnDefinition = "VARCHAR(32) NOT NULL", name = "status")
	private String status;
	
	public Status() {}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
