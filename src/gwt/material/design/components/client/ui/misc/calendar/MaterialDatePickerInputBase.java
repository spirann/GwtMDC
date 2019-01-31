package gwt.material.design.components.client.ui.misc.calendar;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.interfaces.Converter;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.IconPosition;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.events.TypingEvent.HasTypingHandlers;
import gwt.material.design.components.client.events.TypingEvent.TypingHandler;
import gwt.material.design.components.client.masker.Masker;
import gwt.material.design.components.client.ui.MaterialTextField;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.misc.input.MaterialInputBox;
import gwt.material.design.components.client.utils.helper.JsHelper;
import gwt.material.design.components.client.validation.ValidationForTextField;

public abstract class MaterialDatePickerInputBase<T, D extends MaterialDatePickerDialogBase<T, ?>> extends Div
		implements HasValue<T>, HasValueChangeHandlers<T>, HasTypingHandlers {

	protected final MaterialInputBox[] inputs = getInputs();
	protected final D dialog = getDialog();
	protected Converter<MaterialTextField, Date, String> converter = DatePickerHelper.getConverter();

	public MaterialDatePickerInputBase() {
		super(CssName.MDC_DATEPICKER__RANGE__INPUTS);
	}

	@Override
	protected void onInitialize() {

		for (Widget input : getInputs())
			add(input);
				
		dialog.addAcceptHandler(event -> setValue(dialog.getValue()));
		add(dialog);
		super.onInitialize();
	}

	protected abstract D getDialog();
	protected abstract MaterialInputBox[] getInputs();

	protected void fireChangeEvent() {
		ValueChangeEvent.fire(MaterialDatePickerInputBase.this, getValue());
	}

	public void openDatePicker() {
		dialog.setValue(getValue(), false);
		dialog.open();
	}

	@Override
	public void setValue(T value) {
		setValue(value, true);
	}

	@Override
	public void setValue(T value, boolean fireEvents) {

		if (dialog != null)
			dialog.setValue(value, false);

		if (fireEvents)
			fireChangeEvent();
	}

	@Override
	public T getValue() {
		return dialog.getValue();
	}

	public Converter<MaterialTextField, Date, String> getConverter() {
		return converter;
	}

	public void setConverter(Converter<MaterialTextField, Date, String> stringToDate) {
		this.converter = stringToDate;
	}
	
	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<T> handler) {
		final Collection<HandlerRegistration> registrations = Arrays.asList(inputs).stream()
				.map(input -> input.addValueChangeHandler(event -> fireChangeEvent()))
				.collect(Collectors.toList());
		return () -> registrations.forEach(registration -> registration.removeHandler());
	}

	@Override
	public HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
		final Collection<HandlerRegistration> registrations = Arrays.asList(inputs).stream()
				.map(input -> input.addKeyDownHandler(handler)).collect(Collectors.toList());
		return () -> registrations.forEach(registration -> registration.removeHandler());
	}

	@Override
	public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
		final Collection<HandlerRegistration> registrations = Arrays.asList(inputs).stream()
				.map(input -> input.addKeyUpHandler(handler)).collect(Collectors.toList());
		return () -> registrations.forEach(registration -> registration.removeHandler());
	}

	@Override
	public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
		final Collection<HandlerRegistration> registrations = Arrays.asList(inputs).stream()
				.map(input -> input.addKeyPressHandler(handler)).collect(Collectors.toList());
		return () -> registrations.forEach(registration -> registration.removeHandler());
	}

	@Override
	public HandlerRegistration addTypingHandler(TypingHandler handler) {
		final Collection<HandlerRegistration> registrations = Arrays.asList(inputs).stream()
				.map(input -> input.addTypingHandler(handler)).collect(Collectors.toList());
		return () -> registrations.forEach(registration -> registration.removeHandler());
	}

	@Override
	public void setTypeingDelay(int typingDelay) {
		Arrays.asList(inputs).forEach(input -> input.setTypeingDelay(typingDelay));
	}

	@Override
	public void setColor(Color color) {
		super.setColor(color);
		Arrays.asList(inputs).forEach(input -> input.setColor(color));
		dialog.setColor(color);
	}

	@Override
	public void setBackgroundColor(Color color) {
		Arrays.asList(inputs).forEach(input -> input.setBackgroundColor(color));
		dialog.setBackgroundColor(color);
	}

	public void setFocusedColor(Color color) {
		Arrays.asList(inputs).forEach(input -> input.setFocusedColor(color));
	}

	public void setHeaderBackgroundColor(Color color) {
		dialog.setHeaderBackgroundColor(color);
	}

	public void setHeaderTextColor(Color color) {
		dialog.setHeaderTextColor(color);
	}

	public void setActiveColor(Color color) {
		dialog.setActiveColor(color);
	}

	public void setActiveBackgroundColor(Color color) {
		dialog.setActiveBackgroundColor(color);
	}

	@UiChild(tagname = "action")
	public void addAction(final Widget action) {
		dialog.addAction(action);
	}

	public boolean isChangeMonth() {
		return dialog.isChangeMonth();
	}

	public void setChangeMonth(final boolean change) {
		dialog.setChangeMonth(change);
	}

	public boolean isChangeYear() {
		return dialog.isChangeYear();
	}

	public void setChangeYear(final boolean change) {
		dialog.setChangeYear(change);
	}

	public void setShowClearAction(final boolean show) {
		dialog.setShowClearAction(show);
	}

	public Date getMinDate() {
		return dialog.getMinDate();
	}

	public void setMinDate(Date minDate) {
		dialog.setMinDate(minDate);
	}

	public void setMinDate(String minDate) {
		dialog.setMinDate(minDate);
	}

	public Date getMaxDate() {
		return dialog.getMaxDate();
	}

	public void setMaxDate(Date maxDate) {
		dialog.setMaxDate(maxDate);
	}

	public void setMaxDate(String maxDate) {
		dialog.setMaxDate(maxDate);
	}

	public void setDateTooltip(final Date date, final String tooltip) {
		dialog.setDateTooltip(date, tooltip);
	}

	public String getDateTooltip(final Date date) {
		return dialog.getDateTooltip(date);
	}

	public String removeDateTooltip(final Date date) {
		return dialog.removeDateTooltip(date);
	}
	
	public void setDense(boolean dense) {
		Arrays.asList(inputs).forEach(input -> input.setDense(dense));
	}

	public boolean isDense() {
		return Arrays.asList(inputs).stream().filter(input -> input.isDense()).count() > 0;
	}
	
	public void setUnbordered(boolean unbordered) {
		Arrays.asList(inputs).forEach(input -> input.setUnbordered(unbordered));
	}

	public boolean isUnbordered() {
		return Arrays.asList(inputs).stream().filter(input -> input.isUnbordered()).count() > 0;
	}

	public void setPlaceholderColor(Color color) {
		Arrays.asList(inputs).forEach(input -> input.setPlaceholderColor(color));
	}

	public void setHelperTextValidation(boolean validation) {
		Arrays.asList(inputs).forEach(input -> input.setHelperTextValidation(validation));
	}

	public void setHelperTextPersistent(boolean persistent) {
		Arrays.asList(inputs).forEach(input -> input.setHelperTextPersistent(persistent));
	}
	
	protected MaterialTextField newInput() {
		return new MaterialTextField() {

			@Override
			protected void onLoad() {
				super.onLoad();
				addValidation(ValidationForTextField.date());
				setIcon(IconType.EVENT);
				setIconPosition(IconPosition.TRAILING);
				setMaxLength(10);
				addIconClickHandler(event -> openDatePicker());
			}

			@Override
			protected void onInitialize() {
				setInputMask(Masker.Defaults.INSTANCE.date__mask());
				super.onInitialize();
				setInputMask(Masker.Defaults.INSTANCE.date__mask());
				JsHelper.allowNumbersOnly(getInput().getElement());
			}

			@Override
			public void setInputMask(String inputMask) {
				super.setInputMask(inputMask);
				DatePickerHelper.formatPlaceholder(this);
			}

		};
	}
}
