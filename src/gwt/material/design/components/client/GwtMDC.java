package gwt.material.design.components.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.ui.RootPanel;

import gwt.material.design.components.client.resources.MaterialResources;
import gwt.material.design.components.client.utils.helper.StyleHelper;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GwtMDC implements EntryPoint {

	public void onModuleLoad() {
		injectJs(MaterialResources.INSTANCE.jqueryJs());
		injectJs(MaterialResources.INSTANCE.googleAnalyticsJs());
		injectJs(MaterialResources.INSTANCE.prismJs());
		injectCss(MaterialResources.INSTANCE.prismCss());		
		injectJs(MaterialResources.INSTANCE.materialComponentsWebJs());
		injectCss(MaterialResources.INSTANCE.materialComponentsWebCss());
		injectCss(MaterialResources.INSTANCE.addinsCss());

		StyleHelper.setStyleProperty(RootPanel.getBodyElement(), "background-color", "var(--mdc-theme-background)");
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
