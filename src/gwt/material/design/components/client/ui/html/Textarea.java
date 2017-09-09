package gwt.material.design.components.client.ui.html;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.HtmlElements;

public class Textarea extends MaterialWidget {

	public Textarea() {
		super(HtmlElements.TEXTAREA.createElement());
	}

	public Textarea(final String primaryClass) {
		super(HtmlElements.TEXTAREA.createElement(), primaryClass);
	}
	
	public Textarea(final String primaryClass, final String ... initialClasses) {
		super(HtmlElements.TEXTAREA.createElement(), primaryClass, initialClasses);
	}

	public native void setRows(int rows)/*-{
		var element = this.@gwt.material.design.components.client.ui.html.Textarea::getElement()();
		element.rows = rows;
	}-*/;

	public native void setCols(int cols)/*-{
		var element = this.@gwt.material.design.components.client.ui.html.Textarea::getElement()();
		element.cols = cols;
	}-*/;
}
