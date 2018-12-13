package dev.demax.entities;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {
	@Column(columnDefinition = "VARCHAR(32) NOT NULL UNIQUE", name = "email")
	private String email;
	
	@Column(columnDefinition = "VARCHAR(128) NOT NULL", name = "password")
	private String password;
	
	@Column(columnDefinition = "VARCHAR(32) NOT NULL", name = "first_name")
	private String firstName;
	
	@Column(columnDefinition = "VARCHAR(32)", name = "last_name")
	private String lastName;
	
	@Column(columnDefinition = "VARCHAR(32) NOT NULL", name = "position")
	private String position;
	
	@OneToMany(targetEntity = Action.class, 
		mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Action> actions;
	 
	public User() {}
 
	public String getEmail() {
		return email;
	}
 
	public void setEmail(String email) {
		this.email = email;
	}
 
	@Override
	public String getPassword() {
		return password;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}
 
	public String getFirstName() {
		return firstName;
	}
 
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
 
	public String getLastName() {
		return lastName;
	}
 
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	 
	public String getPosition() {
		return position;
	}
	 
	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}