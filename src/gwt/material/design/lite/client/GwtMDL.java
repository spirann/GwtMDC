package gwt.material.design.lite.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.resources.client.TextResource;

import gwt.material.design.lite.client.base.MaterialWidget;
import gwt.material.design.lite.client.resources.MaterialResources;
import gwt.material.design.lite.client.resources.ThemeManager;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GwtMDL implements EntryPoint {

	public void onModuleLoad() {
		injectJs(MaterialResources.INSTANCE.jqueryJs());
		injectJs(MaterialResources.INSTANCE.prismJs());
		injectCss(MaterialResources.INSTANCE.prismCss());
		injectJs(MaterialResources.INSTANCE.materialJs());

		ThemeManager.loadCss(ThemeManager.BLUE_GREY_BLUE);
		
		
		//RootPanel.get().add(new LabelsUi());
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
