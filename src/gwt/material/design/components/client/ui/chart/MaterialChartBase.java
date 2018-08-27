package gwt.material.design.components.client.ui.chart;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.constants.HtmlElements;

public class MaterialChartBase extends Widget {

	// /////////////////////////////////////////////////////////////
	// Initialize java script component
	// /////////////////////////////////////////////////////////////
	protected JavaScriptObject jsElement;

	public MaterialChartBase() {
		super();
		setElement(HtmlElements.DIV.createElement());
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		jsInit();
	}

	protected void jsInit() {
		jsElement = jsInit(getElement());
	}

	public JavaScriptObject asJavaScriptObject() {
		return jsElement;
	}

	protected JavaScriptObject jsInit(final Element element) {
		return element;
	}

}
