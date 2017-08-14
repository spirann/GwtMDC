package gwt.material.design.components.client.ui.html;

import com.google.gwt.dom.client.Element;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.HtmlElements;

public class Textarea extends MaterialWidget {

	public Textarea() {
		super(HtmlElements.SPAN.createElement());
	}

	public Textarea(final String cssClass) {
		super(HtmlElements.TEXTAREA.createElement(), cssClass);
	}

	public void setRows(int rows) {
		setRows(getElement(), rows);
	}
	
	public native void setRows(Element element, int rows)/*-{
		element.rows = rows
	}-*/;

	public void setCols(int cols) {
		setCols(getElement(), cols);
	}
	
	public native void setCols(Element element, int cols)/*-{
		element.cols = cols;
	}-*/;
}
