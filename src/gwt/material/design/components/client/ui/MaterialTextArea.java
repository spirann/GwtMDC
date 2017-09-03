package gwt.material.design.components.client.ui;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.HasDense;
import gwt.material.design.components.client.base.HasHelpText;
import gwt.material.design.components.client.base.HasLabel;
import gwt.material.design.components.client.base.HasPlaceholder;
import gwt.material.design.components.client.base.HasRequired;
import gwt.material.design.components.client.base.MaterialFormField;
import gwt.material.design.components.client.base.mixin.ApplyStyleMixin;
import gwt.material.design.components.client.base.mixin.PlaceholderMixin;
import gwt.material.design.components.client.base.mixin.RequiredMixin;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.FlexDirection;
import gwt.material.design.components.client.constants.Ripple;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Label;
import gwt.material.design.components.client.ui.html.Textarea;

public class MaterialTextArea extends MaterialFormField<String>
		implements HasText, HasLabel, HasDense, HasRequired, HasHelpText, HasPlaceholder {

	// /////////////////////////////////////////////////////////////
	// Initialize Checkbox
	// /////////////////////////////////////////////////////////////
	public static native JavaScriptObject jsInit(final Element element)/*-{
		return $wnd.mdc.textfield.MDCTextfield.attachTo(element);
	}-*/;

	// /////////////////////////////////////////////////////////////
	// Textarea
	// /////////////////////////////////////////////////////////////
	protected Div textField = new Div(CssName.MDC_TEXTFIELD + " " + CssName.MDC_TEXTFIELD_MULTILINE);
	protected Textarea textarea = new Textarea(CssName.MDC_TEXTFIELD_INPUT);

	// /////////////////////////////////////////////////////////////
	// Label
	// /////////////////////////////////////////////////////////////
	protected Label label = new Label();
	protected Label helper = new Label() {
		@Override
		public void setId(String id) {
			super.setId(id);
			textarea.getElement().setAttribute("aria-controls", id);
		}
	};

	// /////////////////////////////////////////////////////////////
	// Style mixin
	// /////////////////////////////////////////////////////////////
	protected final RequiredMixin<Textarea> requeridMixin = new RequiredMixin<>(textarea);
	protected final PlaceholderMixin<Textarea> placeholderMixin = new PlaceholderMixin<>(textarea);

	protected final ApplyStyleMixin<MaterialTextArea> denseMixin = new ApplyStyleMixin<>(this,
			CssName.MDC_TEXTFIELD_DENSE);
	protected final ApplyStyleMixin<Label> helpPersistentMixin = new ApplyStyleMixin<>(helper,
			CssName.MDC_TEXTFIELD_HELPTEXT_PERSISTENT);
	protected final ApplyStyleMixin<Label> helpValidationMixin = new ApplyStyleMixin<>(helper,
			CssName.MDC_TEXTFIELD_HELPTEXT_VALIDATION_MSG);

	private boolean initialized = false;

	public MaterialTextArea() {
		super();
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {

			label.addStyleName(CssName.MDC_TEXTFIELD_LABEL);
			label.setTextColor(Color.MDC_THEME_TEXT_PRIMARY_ON_BACKGROUND);

			setFlexDirection(FlexDirection.COLUMN);

			helper.addStyleName(CssName.MDC_TEXTFIELD_HELPTEXT);

			textField.setDisabledClass(CssName.MDC_TEXTFIELD_DISABLED);
			textField.add(textarea);
			textField.add(label);

			add(textField);
			add(helper);

			jsInit(textField.getElement());

			initialized = true;

		}
	}

	@Override
	public void setId(String id) {
		textarea.setId(id);
		label.setFor(textarea.getId());
	}

	@Override
	public String getId() {
		return textarea.getId();
	}

	@Override
	public void setValue(String value, boolean fireEvents) {
		setValue(textarea.getElement(), value);
		if (fireEvents) {
			fireChangeEvent();
		}
	}

	@Override
	public String getValue() {
		return getValue(textarea.getElement());
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
		textarea.setTextColor(color);
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
		return requeridMixin.isRequired();
	}

	@Override
	public void setRequired(boolean value) {
		requeridMixin.setRequired(value);
	};

	public void setHelpText(final String text) {
		helper.setText(text);
	}

	@Override
	public String getHelpText() {
		return helper.getText();
	}

	@Override
	public void setHelpTextPersistent(boolean persistent) {
		helpPersistentMixin.setApply(persistent);
	}

	@Override
	public boolean isHelpTextPersistent() {
		return helpPersistentMixin.isApplied();
	}

	@Override
	public void setHelpTextValidation(boolean validation) {
		helpValidationMixin.setApply(validation);
	}

	@Override
	public boolean isHelpTextValidation() {
		return helpValidationMixin.isApplied();
	}

	@Override
	public void setPlaceholder(String placeholder) {
		placeholderMixin.setPlaceholder(placeholder);
	}

	@Override
	public String getPlaceholder() {
		return placeholderMixin.getPlaceholder();
	}

	public void setRows(int rows) {
		textarea.setRows(rows);
	}

	public void setCols(int cols) {
		textarea.setCols(cols);
	}

}
