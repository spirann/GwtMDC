package gwt.material.design.components.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.HasDense;
import gwt.material.design.components.client.base.HasLabel;
import gwt.material.design.components.client.base.HasRequired;
import gwt.material.design.components.client.base.MaterialFormField;
import gwt.material.design.components.client.base.mixin.ApplyStyleMixin;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.InputType;
import gwt.material.design.components.client.constants.Ripple;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Input;
import gwt.material.design.components.client.ui.html.Label;

public class MaterialTextfield extends MaterialFormField<String> implements HasText, HasLabel, HasDense, HasRequired {

	// /////////////////////////////////////////////////////////////
	// Initialize Checkbox
	// /////////////////////////////////////////////////////////////
	public static native void textfieldInit(Element element)/*-{
		$wnd.mdc.textfield.MDCTextfield.attachTo(element);
	}-*/;

	// /////////////////////////////////////////////////////////////
	// Style mixin
	// /////////////////////////////////////////////////////////////
	protected final ApplyStyleMixin<MaterialTextfield> denseMixin = new ApplyStyleMixin<>(this,
			CssName.MDC_TEXTFIELD_DENSE);

	// /////////////////////////////////////////////////////////////
	// Radio
	// /////////////////////////////////////////////////////////////
	protected Div textField = new Div(CssName.MDC_TEXTFIELD);
	protected Input input = new Input(InputType.TEXT, CssName.MDC_TEXTFIELD_INPUT);

	// /////////////////////////////////////////////////////////////
	// Label
	// /////////////////////////////////////////////////////////////
	protected Label label = new Label();

	private boolean initialized = false;

	public MaterialTextfield() {
		super();
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {

			label.addStyleName(CssName.MDC_TEXTFIELD_LABEL);
			label.setTextColor(Color.MDC_THEME_TEXT_PRIMARY_ON_BACKGROUND);

			textField.setDisabledClass(CssName.MDC_TEXTFIELD_DISABLED);
			textField.add(input);
			textField.add(label);

			add(textField);

			textfieldInit(textField.getElement());

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
	public void setValue(String value, boolean fireEvents) {
		setValue(input.getElement(), value);
		if (fireEvents) {
			fireChangeEvent();
		}
	}

	@Override
	public String getValue() {
		return getValue(input.getElement());
	}

	protected native String getValue(Element element)/*-{
		return element.value;
	}-*/;

	protected native void setValue(Element element, String value)/*-{
		element.value = value;
	}-*/;

	@Override
	public String getText() {
		return getValue();
	}

	@Override
	public void setText(String text) {
		setValue(text);
	}

	@Override
	public void setRipple(Ripple ripple) {
		textField.setRipple(ripple);
	}

	@Override
	public Ripple getRipple() {
		return textField.getRipple();
	}

	@Override
	public void setTextColor(Color color) {
		input.setTextColor(color);
	}

	@Override
	public void setLabel(String label) {
		this.label.setText(label);
	}

	@Override
	public String getLabel() {
		return label.getText();
	}

	@Override
	public void setDense(boolean dense) {
		denseMixin.setApply(dense);
	}

	@Override
	public boolean isDense() {
		return denseMixin.isApplied();
	}
	
	@Override
	public boolean isRequired() {
		return isRequired(input.getElement());
	}

	@Override
	public void setRequired(boolean value) {
		setRequired(input.getElement(), value);
	};

	protected native Boolean isRequired(Element element)/*-{
		return element.required;
	}-*/;

	protected native void setRequired(Element element, boolean value)/*-{
		element.required = value;
	}-*/;
}
