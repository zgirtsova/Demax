package entities;

import java.time.LocalDateTime;
import java.util.Date;

import util.EntityFieldData;

public class Action {
	private Integer id;
	private LocalDateTime date;
	private Integer actionId;
	private String userEmail;
	
	public Action() {}
	
	@EntityFieldData(
		columnName = "get_id",
		type = Integer.class,
		sqlType = java.sql.Types.INTEGER
	)
	public Integer getId() {
		return id;
	}
	
	@EntityFieldData(
		columnName = "set_id",
		type = Integer.class,
		sqlType = java.sql.Types.INTEGER
	)
	public void setId(Integer id) {
		this.id = id;
	}
	
	@EntityFieldData(
		columnName = "get_occured_on",
		type = Date.class,
		sqlType = java.sql.Types.TIMESTAMP
	)
	public LocalDateTime getDate() {
		return date;
	}
	
	@EntityFieldData(
		columnName = "set_occured_on",
		type = Date.class,
		sqlType = java.sql.Types.TIMESTAMP
	)
	public void setDate(java.sql.Timestamp date) {
		this.date = date.toLocalDateTime();
	}

	@EntityFieldData(
		columnName = "get_action",
		type = String.class,
		sqlType = java.sql.Types.INTEGER
	)
	public Integer getActionId() {
		return actionId;
	}
	
	@EntityFieldData(
		columnName = "set_action",
		type = String.class,
		sqlType = java.sql.Types.INTEGER
	)
	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}
	
	@EntityFieldData(
		columnName = "get_user_email",
		type = String.class,
		sqlType = java.sql.Types.VARCHAR
	)
	public String getUserEmail() {
		return userEmail;
	}
	
	@EntityFieldData(
		columnName = "set_user_email",
		type = String.class,
		sqlType = java.sql.Types.VARCHAR
	)
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}	
}
