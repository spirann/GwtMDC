package gwt.material.design.components.client.utils.debug;

public final class Console {

	public static void log(Object value){
		log(String.valueOf(value));
	}
	
	public static void log(Long value){
		log(String.valueOf(value));
	}
	
	public static void log(Integer value){
		log(String.valueOf(value));
	}
	
	public static void log(Boolean value){
		log(String.valueOf(value));
	}
	
	public static native void log(String text) /*-{
	    console.log(text);
	}-*/;
	
}
