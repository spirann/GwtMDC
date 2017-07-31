package gwt.material.design.components.client.utils.helper;

import java.util.Date;

public final class IdHelper {

	private static int uniqueId = 1;
	
	public static String createUniqueUiId() {
		return new Date().getTime() + "" + uniqueId++;
	}
	
}
