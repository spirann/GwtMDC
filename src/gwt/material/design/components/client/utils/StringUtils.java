package gwt.material.design.components.client.utils;

public class StringUtils {

	public static String capitalize(final String text){
		
		
		final String[] words = text.split(" ");
		final StringBuilder builder = new StringBuilder();
		
		for(String word : words){
			builder.append(" ");
			builder.append(firstLetterToUpper(word));
		}
		
		return builder.toString().substring(1);
	}
	
	public static String firstLetterToUpper(final String text){		
		return text.substring(0,1).toUpperCase() + text.substring(1).toLowerCase();
	}
}
