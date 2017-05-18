package gwt.material.design.lite.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.ui.RootPanel;

import gwt.material.design.lite.client.demo.DemoUi;
import gwt.material.design.lite.client.resources.MaterialResources;
import gwt.material.design.lite.client.resources.ThemeManager;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GwtMDL implements EntryPoint {

	public void onModuleLoad() {
		// injectJs(MaterialResources.INSTANCE.jqueryJs());
		//injectJs(MaterialResources.INSTANCE.materialJs());
		injectJs(MaterialResources.INSTANCE.prismJs());
		injectCss(MaterialResources.INSTANCE.prismCss());

		ThemeManager.loadCss(ThemeManager.INDIGO_PINK);
		/*
		 * ScriptInjector.fromUrl("https://code.getmdl.io/1.3.0/material.min.js"
		 * ).setCallback(new Callback<Void, Exception>() { public void
		 * onFailure(Exception reason) { Window.alert("Script load failed."); }
		 * 
		 * public void onSuccess(Void result) { //Window.alert(
		 * "Script load success.");
		 * //injectCss(MaterialResources.INSTANCE.materialCss()); } }).inject();
		 */
		// injectDebugJs(MaterialResources.INSTANCE.materialJs());
		RootPanel.get().add(new DemoUi());
	}

	public static void injectCss(TextResource resource) {
		StyleInjector.inject(resource.getText());
	}

	public static void injectJs(TextResource resource) {
		injectJs(resource, true, false);
	}

	public static void injectDebugJs(TextResource resource) {
		injectJs(resource, false, true);
	}

	public static void injectJs(TextResource resource, boolean removeTag, boolean sourceUrl) {

		String text = resource.getText() + (sourceUrl ? "//# sourceURL=" + resource.getName() + ".js" : "");

		// Inject the script resource
		ScriptInjector.fromString(text).setWindow(ScriptInjector.TOP_WINDOW).setRemoveTag(removeTag).inject();

	}

	public static native void injectJs(String url)/*-{

		var s = document.createElement("script");
		s.type = "text/javascript";
		s.src = "http://code.getmdl.io/1.3.0/material.min.js";
		$("head").append(s);

	}-*/;

	// Para pegar o nome do navegador
	// public static native String getUserAgent() /*-{
	// return navigator.userAgent.toLowerCase();
	// }-*/
}
