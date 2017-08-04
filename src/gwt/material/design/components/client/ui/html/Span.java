package gwt.material.design.components.client.ui.html;



import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.HtmlElements;

public class Span extends MaterialWidget {

	public Span(){
		super(HtmlElements.SPAN.createElement());
	}
	
	public Span(final String cssClass) {
		super(HtmlElements.SPAN.createElement(), cssClass);
	}
	
}
