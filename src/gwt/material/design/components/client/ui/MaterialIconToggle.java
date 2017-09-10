package gwt.material.design.components.client.ui;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;

import gwt.material.design.components.client.base.mixin.AttributeMixin;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.constants.Ripple;
import gwt.material.design.components.client.constants.Role;
import gwt.material.design.components.client.ui.html.Icon;

public class MaterialIconToggle extends Icon implements HasValue<Boolean> {

	// /////////////////////////////////////////////////////////////
	// Initialize java script component
	// /////////////////////////////////////////////////////////////
	protected JavaScriptObject jsElement;

	protected native void jsInit()/*-{
		var element = this.@gwt.material.design.components.client.ui.MaterialIconToggle::getElement()();
		this.@gwt.material.design.components.client.ui.MaterialIconToggle::jsElement = $wnd.mdc.iconToggle.MDCIconToggle.attachTo(element);
	}-*/;

	protected final AttributeMixin<MaterialIconToggle> toggleOnMixin = new AttributeMixin<>(this, "data-toggle-on");
	protected final AttributeMixin<MaterialIconToggle> toggleOffMixin = new AttributeMixin<>(this, "data-toggle-off");
	protected final AttributeMixin<MaterialIconToggle> ariaPressedMixin = new AttributeMixin<>(this, "aria-pressed");

	protected Color colorOn;

	protected Color colorOff;

	private boolean valueChangeHandlerInitialized;

	private boolean initialized = false;

	public MaterialIconToggle() {
		super(CssName.MDC_ICON_TOGGLE, CssName.MATERIAL_ICONS);
		setRole(Role.BUTTON);
		setDisabledClass(CssName.MDC_ICON_TOGGLE_DISABLED);
		setRipple(Ripple.DEFAULT);
		setCircle(true);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {
			jsInit();
			initializeChageEventListener();
			updateColor();
			initialized = true;
		}
	}

	public native void initializeChageEventListener()/*-{
		var _this = this;
		var element = this.@gwt.material.design.components.client.ui.MaterialIconToggle::getElement()();
		element.addEventListener('MDCIconToggle:change', function () {
			_this.@gwt.material.design.components.client.ui.MaterialIconToggle::updateColor()();
			_this.@gwt.material.design.components.client.ui.MaterialIconToggle::fireChangeEvent()();
		});
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

	protected void updateColor() {
		if (getValue() && colorOn != null) {
			setColor(colorOn);
		} else if (!getValue() && colorOff != null) {
			setColor(colorOff);
		} else {
			setColor(Color.MDC_THEME_TEXT_ICON_ON_BACKGROUND);
		}
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

	public Color getColorOn() {
		return colorOn;
	}

	public void setColorOn(Color colorOn) {
		this.colorOn = colorOn;
	}

	public Color getColorOff() {
		return colorOff;
	}

	public void setColorOff(Color colorOff) {
		this.colorOff = colorOff;
	}
}
