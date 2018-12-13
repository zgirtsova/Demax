package entities;

import java.io.FileInputStream;
import java.io.IOException;

import util.EntityFieldData;

public class Picture{
	private String id;
	private byte[] picture;
	private Integer productId;
	
	public Picture() {}	
	
	@EntityFieldData(
		columnName = "get_id",
		type = String.class,
		sqlType = java.sql.Types.VARCHAR
	)
	public String getId() {
		return id;
	}
	
	@EntityFieldData(
		columnName = "set_id",
		type = String.class,
		sqlType = java.sql.Types.VARCHAR
	)
	public void setId(String id) {
		this.id = id;
	}

	@EntityFieldData(
		columnName = "get_picture",
		type = byte[].class,
		sqlType = java.sql.Types.BINARY
	)
	public byte[] getPicture() {
		return this.picture;
	}

	@EntityFieldData(
		columnName = "set_picture",
		type = FileInputStream.class,
		sqlType = java.sql.Types.BINARY
	)
	public void setPicture(byte[] picture) throws IOException {
	    this.picture = picture;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}	
}
