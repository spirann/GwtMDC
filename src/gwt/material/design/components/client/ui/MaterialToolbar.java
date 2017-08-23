package gwt.material.design.components.client.ui;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Element;

import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.ToolbarType;

public class MaterialToolbar extends MaterialWidget implements HasType<ToolbarType> {

	// /////////////////////////////////////////////////////////////
	// Initialize toolbar
	// /////////////////////////////////////////////////////////////
	protected JavaScriptObject toolbar;
	protected static native JavaScriptObject toolbarInit(final Element element)/*-{
		return $wnd.mdc.toolbar.MDCToolbar.attachTo(element);	
	}-*/;

	protected final TypeMixin<MaterialToolbar, ToolbarType> typeMixin = new TypeMixin<>(this);

	private boolean initialize = false;

	public MaterialToolbar() {
		super(HtmlElements.HEADER.createElement(), CssName.MDC_TOOLBAR);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialize) {			
			
			toolbar = toolbarInit(getElement());			
			
			initialize = true;
			
			Scheduler.get().scheduleFixedDelay(() -> {				
				fixedAdjustElement(toolbar);				
				return false;				
			}, 500);
		}
	}
	
	protected static native void fixedAdjustElement(final JavaScriptObject toolbar)/*-{
		toolbar.fixedAdjustElement = $doc.querySelector('.mdc-toolbar-fixed-adjust');
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
