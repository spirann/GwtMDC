package gwt.material.design.components.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.HasIndeterminate;
import gwt.material.design.components.client.base.MaterialFormField;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.InputType;
import gwt.material.design.components.client.constants.Ripple;
import gwt.material.design.components.client.resources.MaterialResources;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Input;
import gwt.material.design.components.client.ui.html.Label;

public class MaterialCheckbox extends MaterialFormField<Boolean> implements HasText, HasIndeterminate {

	// /////////////////////////////////////////////////////////////
	// Initialize Checkbox
	// /////////////////////////////////////////////////////////////
	public static native void checkboxInit(Element element)/*-{
		$wnd.mdc.checkbox.MDCCheckbox.attachTo(element);
	}-*/;

	// /////////////////////////////////////////////////////////////
	// Radio
	// /////////////////////////////////////////////////////////////
	protected Div checkbox = new Div(CssName.MDC_CHECKBOX);
	protected Input input = new Input(InputType.CHECKBOX, CssName.MDC_CHECKBOX_NATIVE_CONTROL);
	protected Div divBackground = new Div(CssName.MDC_CHECKBOX_BACKGROUND);
	protected MaterialSvg svgCheckmark = new MaterialSvg();
	protected Div divMixedmark = new Div(CssName.MDC_CHECKBOX_MIXEDMARK);

	// /////////////////////////////////////////////////////////////
	// Label
	// /////////////////////////////////////////////////////////////
	protected Label label = new Label();

	private boolean initialized = false;

	public MaterialCheckbox(){
		super();
		setRipple(Ripple.DEFAULT);
		setCircle(true);
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {

			svgCheckmark.setResource(MaterialResources.INSTANCE.mdcCheckboxCheckmark());

			divBackground.add(svgCheckmark);
			divBackground.add(divMixedmark);

			checkbox.setDisabledClass(CssName.MDC_CHECKBOX_DISABLED);
			checkbox.add(input);
			checkbox.add(divBackground);

			add(checkbox);
			add(label);

			checkboxInit(checkbox.getElement());
			svgCheckmark.setFillColor(Color.MDC_THEME_ACCENT);

			initialized = true;

		}
	}

	@Override
	public void setId(String id) {
		input.setId(id);
		label.setFor(input.getId());
	}

	@Override
	public String getId() {
		return input.getId();
	}

	@Override
	public void setValue(Boolean value, boolean fireEvents) {
		setValue(input.getElement(), value);
		if (fireEvents) {
			fireChangeEvent();
		}
	}

	@Override
	public Boolean getValue() {
		return getValue(input.getElement());
	}

	protected native Boolean getValue(Element element)/*-{
		return element.checked;
	}-*/;

	protected native void setValue(Element element, boolean value)/*-{
		element.checked = value;
	}-*/;

	@Override
	public boolean isIndeterminate() {
		return isIndeterminate(input.getElement());
	}

	@Override
	public void setIndeterminate(boolean value) {
		setIndeterminate(input.getElement(), value);
	};

	protected native Boolean isIndeterminate(Element element)/*-{
		return element.indeterminate;
	}-*/;

	protected native void setIndeterminate(Element element, boolean value)/*-{
		element.indeterminate = value;
	}-*/;

	@Override
	public String getText() {
		return label.getText();
	}

	@Override
	public void setText(String text) {
		label.setText(text);
	}

	@Override
	public void setRipple(Ripple ripple) {
		checkbox.setRipple(ripple);
	}

	@Override
	public Ripple getRipple() {
		return checkbox.getRipple();
	}

	@Override
	public void setCircle(boolean circle) {
		checkbox.setCircle(circle);
	}

	@Override
	public void setTextColor(Color color) {
		label.setTextColor(color);
	}
}
