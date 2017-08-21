package gwt.material.design.components.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.AttributeMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.constants.Ripple;
import gwt.material.design.components.client.constants.Role;

public class MaterialIconToggle extends MaterialWidget implements HasValue<Boolean> {

	// /////////////////////////////////////////////////////////////
	// Initialize Checkbox
	// /////////////////////////////////////////////////////////////
	public native void toggleInit(Element element)/*-{
		$wnd.mdc.iconToggle.MDCIconToggle.attachTo(element);
	}-*/;

	protected final AttributeMixin<MaterialIconToggle> toggleOnMixin = new AttributeMixin<>(this, "data-toggle-on");
	protected final AttributeMixin<MaterialIconToggle> toggleOffMixin = new AttributeMixin<>(this, "data-toggle-off");
	protected final AttributeMixin<MaterialIconToggle> ariaPressedMixin = new AttributeMixin<>(this, "aria-pressed");

	private IconType type;

	private boolean valueChangeHandlerInitialized;

	private boolean initialized = false;

	public MaterialIconToggle() {
		super(HtmlElements.I.createElement(), CssName.MDC_ICON_TOGGLE, CssName.MATERIAL_ICONS);
		setRole(Role.BUTTON);
		setDisabledClass(CssName.MDC_ICON_TOGGLE_DISABLED);
		setRipple(Ripple.DEFAULT);
		setCircle(true);	
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {
			toggleInit(getElement());
			addChageEvent(getElement());
			initialized = true;
		}
	}
	
	public native void addChageEvent(Element element)/*-{
		var _this = this;		
		element.addEventListener('MDCIconToggle:change', displayDate);		
		function displayDate() {
    		_this.@gwt.material.design.components.client.ui.MaterialIconToggle::fireChangeEvent()();
		}		
	}-*/;

	public void setToggleOn(final IconType icon) {
		toggleOnMixin.setAttribute("{\"content\": \"" + icon.getCssName() + "\"}");
	}

	public void setToggleOff(final IconType icon) {
		toggleOffMixin.setAttribute("{\"content\": \"" + icon.getCssName() + "\"}");
	}

	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		return addDomHandler(handler, ChangeEvent.getType());
	}

	protected void fireChangeEvent() {
		ValueChangeEvent.fire(MaterialIconToggle.this, getValue());
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Boolean> handler) {
		// Initialization code
		if (!valueChangeHandlerInitialized) {
			valueChangeHandlerInitialized = true;
			addChangeHandler(new ChangeHandler() {
				public void onChange(ChangeEvent event) {
					fireChangeEvent();
				}
			});
		}
		return addHandler(handler, ValueChangeEvent.getType());
	}

	@Override
	public void setValue(final Boolean value) {
		setValue(value, true);
	}

	@Override
	public Boolean getValue() {
		return ariaPressedMixin.getAttributeAsBoolean();
	}

	@Override
	public void setValue(final Boolean value, boolean fireEvents) {
		this.ariaPressedMixin.setAttribute(value);
		if (fireEvents) {
			fireChangeEvent();
		}
	}
}
