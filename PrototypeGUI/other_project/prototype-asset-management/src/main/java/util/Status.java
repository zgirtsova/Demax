package util;

public final class Status {
	public static String getStatusNameById(Integer id) {
		switch(id) {
			case 1: return "New";
			case 2: return "In Progress";
			case 3: return "Finished";
			default: return "No such status";
		}		
	}

	public static Integer getStatusIdByName(String status) {
		switch(status){
			case "New":return 1;
			case "In Progress":return 2;
			case "Finished":return 3;
			default: return null;
		}
	}
}
