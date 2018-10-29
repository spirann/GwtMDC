/*
 * #%L
 * Gwt Material Design Components
 * %%
 * Copyright (C) 2017 - 2017 Gwt Material Design Components
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package gwt.material.design.components.client.ui.misc.input;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.interfaces.FromString;
import gwt.material.design.components.client.base.interfaces.HasDense;
import gwt.material.design.components.client.base.interfaces.HasIcon;
import gwt.material.design.components.client.base.interfaces.HasIconClickHandlers;
import gwt.material.design.components.client.base.interfaces.HasInputMask;
import gwt.material.design.components.client.base.interfaces.HasLabel;
import gwt.material.design.components.client.base.interfaces.HasPlaceholder;
import gwt.material.design.components.client.base.interfaces.HasReadOnly;
import gwt.material.design.components.client.base.interfaces.HasRequired;
import gwt.material.design.components.client.base.interfaces.HasState;
import gwt.material.design.components.client.base.interfaces.HasTextFieldValidation;
import gwt.material.design.components.client.base.interfaces.HasType;
import gwt.material.design.components.client.base.interfaces.HasUnbordered;
import gwt.material.design.components.client.base.interfaces.HasValidationHandlers;
import gwt.material.design.components.client.base.mixin.InputMaskMixin;
import gwt.material.design.components.client.base.mixin.PlaceholderMixin;
import gwt.material.design.components.client.base.mixin.StateMixin;
import gwt.material.design.components.client.base.mixin.ToggleStyleMixin;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.base.mixin.base.AttributeMixin;
import gwt.material.design.components.client.base.widget.MaterialValuedField;
import gwt.material.design.components.client.base.widget.MaterialWidget;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssAttribute;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.constants.InputType;
import gwt.material.design.components.client.constants.State;
import gwt.material.design.components.client.constants.TextFieldIconPosition;
import gwt.material.design.components.client.constants.TextFieldType;
import gwt.material.design.components.client.events.IconClickEvent;
import gwt.material.design.components.client.events.IconClickEvent.IconClickHandler;
import gwt.material.design.components.client.events.ValidationEvent;
import gwt.material.design.components.client.events.ValidationEvent.ValidationHandler;
import gwt.material.design.components.client.ui.MaterialIcon;
import gwt.material.design.components.client.ui.html.Input;
import gwt.material.design.components.client.validation.TextFieldValidation;
import gwt.material.design.components.client.validation.Validation.Result;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialInput extends MaterialValuedField<String> implements HasText, HasLabel, HasDense, HasUnbordered,
		HasRequired, HasPlaceholder, HasType<TextFieldType>, HasInputMask, HasState, HasIcon, HasIconClickHandlers,
		HasTextFieldValidation, HasValidationHandlers<Result>, HasReadOnly {

	// /////////////////////////////////////////////////////////////
	// Textfield
	// /////////////////////////////////////////////////////////////
	protected final MaterialWidget input = constructInput();
	protected final MaterialFloatLabel label = new MaterialFloatLabel();
	protected final MaterialLineRipple lineRipple = new MaterialLineRipple();
	protected final MaterialNotchedOutline notchedOutline = new MaterialNotchedOutline();
	protected final MaterialIcon icon = new MaterialIcon(CssName.MDC_TEXT_FIELD__ICON);

	// /////////////////////////////////////////////////////////////
	// Style mixin TextFieldIconPosition
	// /////////////////////////////////////////////////////////////
	protected final PlaceholderMixin<MaterialWidget> placeholderMixin = new PlaceholderMixin<>(input);
	protected final InputMaskMixin<MaterialWidget> inputMaskMixin = new InputMaskMixin<>(input);
	protected final AttributeMixin<MaterialWidget, Boolean> requeridMixin = new AttributeMixin<>(input,
			CssAttribute.REQUIRED, FromString.TO_BOOLEAN);
	protected final AttributeMixin<MaterialWidget, Integer> minLengthMixin = new AttributeMixin<>(input,
			CssAttribute.MIN_LENGTH, 0, FromString.TO_INTEGER);
	protected final AttributeMixin<MaterialWidget, Integer> maxLengthMixin = new AttributeMixin<>(input,
			CssAttribute.MAX_LENGTH, Integer.MAX_VALUE, FromString.TO_INTEGER);
	protected final AttributeMixin<MaterialWidget, Boolean> readOnlyMixin = new AttributeMixin<>(input,
			CssAttribute.READONLY, false, FromString.TO_BOOLEAN);
	protected final ToggleStyleMixin<MaterialInput> denseMixin = new ToggleStyleMixin<>(this,
			CssName.MDC_TEXT_FIELD__DENSE);
	protected final ToggleStyleMixin<MaterialInput> unborderedMixin = new ToggleStyleMixin<>(this,
			CssName.MDC_TEXT_FIELD__UNBORDERED);
	protected final TypeMixin<MaterialInput, TextFieldType> typeMixin = new TypeMixin<>(this);
	protected final TypeMixin<MaterialInput, TextFieldIconPosition> iconPositionMixin = new TypeMixin<>(this);
	protected final StateMixin<MaterialInput> stateMixin = new StateMixin<>(this);

	// /////////////////////////////////////////////////////////////
	// Validation
	// /////////////////////////////////////////////////////////////
	protected TextFieldValidation validation;

	public MaterialInput() {
		super(CssName.MDC_TEXT_FIELD);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return new $wnd.mdc.textField.MDCTextField(element);
	}-*/;

	protected MaterialWidget constructInput() {
		return new Input(InputType.TEXT, CssName.MDC_TEXT_FIELD__INPUT);
	}

	@Override
	protected void onInitialize() {

		label.setFor(input.getId());

		icon.addClickHandler(event -> {
			event.preventDefault();
			event.stopPropagation();
			IconClickEvent.fire(this);
		});

		add(icon);
		add(input);
		add(label);
		add(lineRipple);
		add(notchedOutline);

		addKeyUpHandler(event -> fireValidation());

		super.onInitialize();
	}

	protected void fireValidation() {

		if (validation == null)
			return;

		final Result result = validation.validate(getValue(), getInputMask(), isRequired(), getMinLength(),
				getMaxLength());

		if (result == null)
			return;

		applyResultValidation(result);
		fireValidationEvent(result);
	}

	protected void applyResultValidation(final Result result) {
		setState(result.getState());
	}

	public TextFieldValidation getValidation() {
		return validation;
	}

	public void setValidation(TextFieldValidation validation) {
		this.validation = validation;
	}

	protected void fireValidationEvent(final Result result) {
		ValidationEvent.fire(this, result);
	}

	@SuppressWarnings("unchecked")
	@Override
	public HandlerRegistration addValidationHandler(ValidationHandler<Result> handler) {
		return addHandler(event -> {
			// if (isEnabled()) {
			handler.onValidate((ValidationEvent<Result>) (ValidationEvent<?>) event);
			// }
		}, ValidationEvent.getType());
	}

	@Override
	public String getValue() {
		return inputMaskMixin.getValue();
	}

	@Override
	public native void setValue(String value, boolean fireEvents)/*-{

		var textfield = this.@gwt.material.design.components.client.ui.misc.input.MaterialInput::input;
		var element = textfield.@gwt.material.design.components.client.ui.html.Div::getElement()();
		element.value = value;

		if (fireEvents) {
			this.@gwt.material.design.components.client.ui.misc.input.MaterialInput::fireChangeEvent()();
		}

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
	public void setTextColor(Color color) {
		input.setTextColor(color);
	}

	@Override
	public void setBackgroundColor(Color color) {
		setCssProperty(CssMixin.MDC_TEXTFIELD__FILL_COLOR, color.getCssName());
	}

	@Override
	public void setColor(Color color) {
		setCssProperty(CssMixin.MDC_TEXTFIELD__TEXT_COLOR, color.getCssName());
	}

	public void setFocusedColor(Color color) {
		setCssProperty(CssMixin.MDC_TEXTFIELD__FOCUSED_COLOR, color.getCssName());
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
		denseMixin.toggle(dense);
	}

	@Override
	public boolean isDense() {
		return denseMixin.isApplied();
	}

	@Override
	public void setPlaceholder(String placeholder) {
		placeholderMixin.setPlaceholder(placeholder);
	}

	@Override
	public String getPlaceholder() {
		return placeholderMixin.getPlaceholder();
	}

	@Override
	public void setPlaceholderColor(Color color) {
		placeholderMixin.setPlaceholderColor(color);
	}

	public void setInputType(InputType type) {
		if (input instanceof Input)
			((Input) input).setType(type);
	}

	public InputType getInputType() {
		if (input instanceof Input)
			return ((Input) input).getType();
		return null;
	}

	@Override
	public void setType(TextFieldType type) {
		typeMixin.setType(type);
	}

	@Override
	public TextFieldType getType() {
		return typeMixin.getType();
	}

	public void setMinLength(final int length) {
		minLengthMixin.setValue(length);
	}

	public int getMinLength() {
		return minLengthMixin.getValue();
	}

	public void setMaxLength(final int length) {
		maxLengthMixin.setValue(length);
	}

	public int getMaxLength() {
		return maxLengthMixin.getValue();
	}

	@Override
	public boolean isRequired() {
		return requeridMixin.getValue();
	}

	@Override
	public void setRequired(boolean value) {
		requeridMixin.setValue(value);
	};

	@Override
	public void setInputMask(String inputMask) {
		inputMaskMixin.setInputMask(inputMask);
		if (inputMask != null) {
			setMinLength(inputMask.length());
			setMaxLength(inputMask.length());
		}
	}

	@Override
	public String getInputMask() {
		return inputMaskMixin.getInputMask();
	}

	@Override
	public void setState(State state) {
		stateMixin.setState(state);
	}

	@Override
	public State getState() {
		return stateMixin.getState();
	}

	@Override
	public IconType getIcon() {
		return icon.getType();
	}

	@Override
	public void setIcon(IconType iconType) {
		setIcon(iconType, false);
	}

	@Override
	public void setIcon(IconType iconType, boolean animate) {
		icon.setType(iconType, animate);
		if (iconPositionMixin.getType() != null) {
			removeStyleName(iconPositionMixin.getType().getCssName());
			if (iconType != null)
				addStyleName(iconPositionMixin.getType().getCssName());
		}
	}

	@Override
	public void setIconColor(Color color) {
		icon.setColor(color);
	}

	public HandlerRegistration addIconClickHandler(IconClickHandler handler) {
		icon.setTabindex(0);
		return addHandler(event -> {
			if (isEnabled())
				handler.onClick(event);
		}, IconClickEvent.getType());
	}

	public TextFieldIconPosition getIconPosition() {
		return iconPositionMixin.getType();
	}

	public void setIconPosition(TextFieldIconPosition iconPosition) {
		iconPositionMixin.setType(iconPosition);
		if (icon.getType() == null && iconPosition != null)
			removeStyleName(iconPositionMixin.getType().getCssName());

	}

	@Override
	public void setWidth(String width) {
		super.setWidth(width);
		input.setWidth(width);
	}

	@Override
	public void setMaxWidth(String maxWidth) {
		super.setMaxWidth(maxWidth);
		input.setMaxWidth(maxWidth);
	}

	@Override
	public void setMinHeight(String minHeight) {
		super.setMinHeight(minHeight);
		input.setMinHeight(minHeight);
	}

	@Override
	public void setReadOnly(boolean readOnly) {
		readOnlyMixin.setValue(readOnly);
	}

	@Override
	public boolean isReadOnly() {
		return readOnlyMixin.getValue();
	}

	@Override
	public void setUnbordered(boolean unbordered) {
		unborderedMixin.toggle(unbordered);
	}

	@Override
	public boolean isUnbordered() {
		return unborderedMixin.isApplied();
	}
}
