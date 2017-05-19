package gwt.material.design.lite.client.resources;

public class ThemeManager {

	private static final String ssID = "-mdl--theme-";
	public static final String INDIGO_PINK 		= "https://code.getmdl.io/1.3.0/material.indigo-pink.min.css";
	public static final String BLUE_GREY_BLUE 	= "https://code.getmdl.io/1.3.0/material.blue_grey-blue.min.css";
	public static final String GREY_BLUE 	= "https://code.getmdl.io/1.3.0/material.grey-blue.min.css";
	
	public static void loadCss(String url){
		loadCss(url, ssID);
	}
	
	public static native void loadCss(String url, String ssID)/*-{
		var ss = $doc.getElementById(ssID);
		if (ss != null) {
			ss.parentNode.removeChild(ss);
		}
		var fileref = document.createElement("link");
		fileref.setAttribute("id", ssID);
		fileref.setAttribute("rel", "stylesheet");
		fileref.setAttribute("type", "text/css");
		fileref.setAttribute("href", url);
		$doc.getElementsByTagName("head")[0].appendChild(fileref);
	}-*/;

}
