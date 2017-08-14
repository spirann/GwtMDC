package gwt.material.design.components.client.ui.html;



import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.HtmlElements;

public class Nav extends MaterialWidget {

	public Nav(){
		super(HtmlElements.NAV.createElement());
	}
	
	public Nav(final String cssClass) {
		super(HtmlElements.NAV.createElement(), cssClass);
	}
	
}
