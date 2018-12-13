package orm.dto;

public class PictureDetailedDto {
	private String id;
	private String picture;
	
	public PictureDetailedDto() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
}
