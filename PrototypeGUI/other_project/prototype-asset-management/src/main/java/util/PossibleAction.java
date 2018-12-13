package util;

public final class PossibleAction {
	public static String getActionById(Integer actionId) {
		switch (actionId) {
		case 1:
			return "logged in.";
		case 2:
			return "logged out.";
		case 3:
			return "created new Product.";
		case 4:
			return "created new Project.";
		default:
			return null;
		}
	}
	
	public static Integer getActionByName(String action) {
		switch (action) {
		case "logged in.":
			return 1;
		case "logged out.":
			return 2;
		case "created new Product.":
			return 3;
		case "created new Project.":
			return 4;
		default:
			return null;
		}
	}
}
