package dev.demax.dtos;

import java.sql.Timestamp;
import java.util.UUID;

public class ActionDto {
	private UUID id;
	private Timestamp date;
	private String user;
	private String action;
	
	public ActionDto() {}
	 
	public UUID getId() {
		return id;
	}
	 
	public void setId(UUID id) {
		this.id = id;
	}
	 
	public Timestamp getDate() {
		return date;
	}
	 
	public void setDate(Timestamp date) {
		this.date = date;
	}
	 
	public String getUser() {
		return user;
	}
	 
	public void setUser(String user) {
		this.user = user;
	}
	 
	public String getAction() {
		return action;
	}
	 
	public void setAction(String action) {
		this.action = action;
	}
}
