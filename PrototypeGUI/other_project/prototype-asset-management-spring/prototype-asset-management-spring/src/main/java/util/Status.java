package util;

import java.util.UUID;

public final class Status {
	public static UUID getStatusIdByName(String status) {
		switch (status) {
			case "New": return UUID.fromString("f98ca653-650f-41c0-859c-4469823a1aed");
			case "In progress": return UUID.fromString("427e79ff-02d7-44ea-9b02-8f151be28964");
			case "Finished": return UUID.fromString("5a62cc2b-b335-4041-98ad-ed1f3f33c397");
			default: return null;
		}
	}
}
