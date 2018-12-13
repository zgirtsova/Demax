package entities;

import util.EntityFieldData;

public class User {
	private Integer id;
	private String email;
	private String password;
	private String first_name;
	private String last_name;
	private String role;
	
	public User() {}
	
	@EntityFieldData(
		columnName = "set_id",
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
		columnName = "get_email",
		type = String.class,
		sqlType = java.sql.Types.VARCHAR
	)
	public String getEmail() {
		return email;
	}
	
	@EntityFieldData(
		columnName = "set_email",
		type = String.class,
		sqlType = java.sql.Types.VARCHAR
	)
	public void setEmail(String email) {
		this.email = email;
	}
	
	@EntityFieldData(
		columnName = "get_password",
		type = String.class,
		sqlType = java.sql.Types.VARCHAR
	)
	public String getPassword() {
		return password;
	}
	
	@EntityFieldData(
		columnName = "set_password",
		type = String.class,
		sqlType = java.sql.Types.VARCHAR
	)
	public void setPassword(String password) {
		this.password = password;
	}
	
	@EntityFieldData(
		columnName = "get_first_name",
		type = String.class,
		sqlType = java.sql.Types.VARCHAR
	)
	public String getFirstName() {
		return first_name;
	}
	
	@EntityFieldData(
		columnName = "set_first_name",
		type = String.class,
		sqlType = java.sql.Types.VARCHAR
	)
	public void setFirstName(String firstName) {
		this.first_name = firstName;
	}
	
	@EntityFieldData(
		columnName = "get_last_name",
		type = String.class,
		sqlType = java.sql.Types.VARCHAR
	)
	public String getLastName() {
		return last_name;
	}
	
	@EntityFieldData(
		columnName = "set_last_name",
		type = String.class,
		sqlType = java.sql.Types.VARCHAR
	)
	public void setLastName(String lastName) {
		this.last_name = lastName;
	}
	
	@EntityFieldData(
		columnName = "get_role",
		type = String.class,
		sqlType = java.sql.Types.VARCHAR
	)
	public String getRole() {
		return role;
	}
	
	@EntityFieldData(
		columnName = "set_role",
		type = String.class,
		sqlType = java.sql.Types.VARCHAR
	)
	public void setRole(String role) {
		this.role = role;
	}
}
