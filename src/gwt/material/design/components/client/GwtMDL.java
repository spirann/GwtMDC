package gwt.material.design.components.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.resources.client.TextResource;

import gwt.material.design.components.client.resources.MaterialResources;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GwtMDL implements EntryPoint {

	public void onModuleLoad() {
		//injectJs(MaterialResources.INSTANCE.jqueryJs());
		injectJs(MaterialResources.INSTANCE.materialJs());
		injectJs(MaterialResources.INSTANCE.prismJs());		
		injectCss(MaterialResources.INSTANCE.prismCss());
		injectCss(MaterialResources.INSTANCE.addinsCss());
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
	// Para pegar o nome do navegador
	// public static native String getUserAgent() /*-{
	// return navigator.userAgent.toLowerCase();
	// }-*/
}
