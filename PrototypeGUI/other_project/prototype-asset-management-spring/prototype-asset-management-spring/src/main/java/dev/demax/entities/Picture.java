package dev.demax.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pictures")
public class Picture {
	@Id
	@Column(columnDefinition = "VARCHAR(64) NOT NULL UNIQUE", name = "id")
	private String id;
	
	@Column(columnDefinition = "BYTEA NOT NULL UNIQUE", name = "picture")
	private byte[] picture;
	
	public Picture() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
}
