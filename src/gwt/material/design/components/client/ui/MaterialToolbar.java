package gwt.material.design.components.client.ui;

import com.google.gwt.dom.client.Document;

import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.ToolbarType;

public class MaterialToolbar extends MaterialWidget implements HasType<ToolbarType> {

	private final TypeMixin<MaterialToolbar, ToolbarType> typeMixin = new TypeMixin<>(this);

	private boolean initialize = false;

	public MaterialToolbar() {
		super(Document.get().createElement(HtmlElements.HEADER), CssName.MDC_TOOLBAR);
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		if (!initialize) {
			initialize(CssName.MDC_TOOLBAR, CssName.MDC_TOOLBAR_FIXED_ADJUST);
			initialize = true;
		}
		// 	getElement().getStyle().setProperty("--mdc-toolbar-ratio-to-extend-flexible","3");
	}

	protected native void initialize(final String mdcToolbarClass, final String mdcToolbarFixedAdjustClass)/*-{
		
		var toolbar = $wnd.mdc.toolbar.MDCToolbar.attachTo($doc.querySelector('.' + mdcToolbarClass));
				
		toolbar.fixedAdjustElement = $doc.querySelector('.' + mdcToolbarFixedAdjustClass);
				
	}-*/;

	@Override
	public void setType(ToolbarType type) {
		typeMixin.setType(type);
	}

	@Override
	public ToolbarType getType() {
		return typeMixin.getType();
	}
}
