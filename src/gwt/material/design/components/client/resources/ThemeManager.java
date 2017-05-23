package gwt.material.design.components.client.resources;

import com.google.gwt.dom.client.StyleElement;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.resources.client.TextResource;

public class ThemeManager {

	private static StyleElement currentTheme;
	
	public static void applyTheme(final TextResource resource){
		
		if(currentTheme != null){
			currentTheme.removeFromParent();
		}
		
		currentTheme = StyleInjector.injectStylesheet(resource.getText());
		
	}
}
