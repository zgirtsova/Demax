package dev.demax.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "actions")
public class Action extends BaseEntity {
	@Column(name = "occurred_on", nullable = false)
	private Timestamp occurredOn;
	
	@Column(columnDefinition = "VARCHAR(32) NOT NULL", name = "action")
	private String action;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	public Action() {}

	public Timestamp getOccurredOn() {
		return occurredOn;
	}

	public void setOccurredOn(Timestamp occurredOn) {
		this.occurredOn = occurredOn;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
