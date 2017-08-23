package gwt.material.design.components.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.StyleElement;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.ui.RootPanel;

import gwt.material.design.components.client.resources.MaterialResources;
import gwt.material.design.components.client.theme.MaterialThemes;
import gwt.material.design.components.client.theme.ThemeManager;
import gwt.material.design.components.client.utils.helper.StyleHelper;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GwtMDC implements EntryPoint {

	private static StyleElement mdcCss;
	private static StyleElement prismCss;
	private static StyleElement addinsCss;
	
	public void onModuleLoad() {
		
		// ///////////////////////////////////////////////////////////////
		// Load main resources
		// ///////////////////////////////////////////////////////////////
		loadJsResources();
		loadCssResources();
		
		// ///////////////////////////////////////////////////////////////
		// Configure background
		// ///////////////////////////////////////////////////////////////
		StyleHelper.setStyleProperty(RootPanel.getBodyElement(), 
				"background-color", "var(--mdc-theme-background)");

		// ///////////////////////////////////////////////////////////////
		// Apply default theme
		// ///////////////////////////////////////////////////////////////
		ThemeManager.applyTheme(MaterialThemes.INSTANCE.indigo_pink());
	}
	
	public static void loadJsResources() {
		injectJs(MaterialResources.INSTANCE.jqueryJs());
		//injectJs(MaterialResources.INSTANCE.googleAnalyticsJs());
		injectJs(MaterialResources.INSTANCE.materialComponentsWebJs());
		injectJs(MaterialResources.INSTANCE.prismJs());
	}
	
	public static void loadCssResources() {		
		
		if(mdcCss != null) {
			mdcCss.removeFromParent();
		}
		
		if(prismCss != null) {
			prismCss.removeFromParent();
		}
		
		if(addinsCss != null) {
			addinsCss.removeFromParent();
		}
		
		mdcCss = injectCss(MaterialResources.INSTANCE.materialComponentsWebCss());
		prismCss = injectCss(MaterialResources.INSTANCE.prismCss());
		addinsCss = injectCss(MaterialResources.INSTANCE.addinsCss());
	}

	public static StyleElement injectCss(TextResource resource) {
		return StyleInjector.injectStylesheetAtEnd(resource.getText());
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
}
