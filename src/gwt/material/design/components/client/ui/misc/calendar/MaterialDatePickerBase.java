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
package gwt.material.design.components.client.ui.misc.calendar;

import java.util.Date;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.widget.MaterialValuedField;
import gwt.material.design.components.client.constants.ChipType;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.Flex;
import gwt.material.design.components.client.resources.message.IMessages;
import gwt.material.design.components.client.ui.MaterialChip;
import gwt.material.design.components.client.ui.html.Div;

/**
 * 
 * @author Richeli Vargas
 *
 */
@SuppressWarnings("deprecation")
public abstract class MaterialDatePickerBase<T, H extends MaterialValuedField<T>, S extends MaterialDatePickerBaseDaySelector<T>>
		extends MaterialValuedField<T> {

	protected final H header;
	protected final S daySelector;
	protected final Div actions = new Div(CssName.MDC_DATEPICKER__ACTIONS);
	protected final MaterialDatePickerMonthSelector monthSelector = new MaterialDatePickerMonthSelector();
	protected final MaterialDatePickerYearSelector yearSelector = new MaterialDatePickerYearSelector();

	protected Widget clearAction;
	protected Widget visibleSelector;
	private boolean changeYear = true;

	public MaterialDatePickerBase(final H header, final S daySelector) {
		super(CssName.MDC_DATEPICKER);
		this.header = header;
		this.daySelector = daySelector;
		this.visibleSelector = daySelector;
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return element;
	}-*/;

	@Override
	protected void onInitialize() {
		super.onInitialize();

		final Date date;
		final T value = getValue();
		if (value == null)
			date = null;
		else if (value instanceof Date)
			date = (Date) value;
		else if (value instanceof Date[]) {
			final Date[] values = (Date[]) value;
			date = values.length == 0 ? null : values[values.length - 1];
		} else
			date = null;

		header.setValue(getValue());

		daySelector.addValueChangeHandler(event -> setValue(event.getValue(), true));
		daySelector.addMonthClickHandler(event -> {
			if (daySelector.isChangeMonth())
				toggleSelector(monthSelector);
		});
		daySelector.setValue(getValue());

		monthSelector.setValue(date == null ? null : date.getMonth() + 1);
		monthSelector.addValueChangeHandler(event -> {
			daySelector.setMonth(event.getValue() - 1);
			toggleSelector(daySelector);
		});

		yearSelector.setValue(date == null ? null : date.getYear() + 1900);
		yearSelector.addValueChangeHandler(event -> {
			daySelector.setYear(event.getValue() - 1900);
			toggleSelector(daySelector);
		});

		visibleSelector = daySelector;
		toggle(monthSelector.getElement());
		toggle(yearSelector.getElement());

		add(header);
		add(actions);
		add(daySelector);
		add(monthSelector);
		add(yearSelector);
	}

	protected Date today() {
		return daySelector.today;
	}

	protected native void toggle(final Element element)/*-{
		$wnd.jQuery(element).slideToggle(250, function(event) {
			if ($wnd.jQuery(element).css('display') != 'none')
				$wnd.jQuery(element).css('display', 'flex');
		});
	}-*/;

	protected void toggleSelector(final Widget selector) {
		if (selector == visibleSelector)
			return;
		toggle(visibleSelector.getElement());
		toggle(selector.getElement());
		visibleSelector = selector;
	}

	public Widget addAction(final String text, final ClickHandler handler) {
		final MaterialChip chip = new MaterialChip();
		chip.setFlex(Flex.NONE);
		chip.setType(ChipType.OUTLINE);
		chip.setText(text);
		chip.addClickHandler(handler);
		addAction(chip);
		return chip;
	}

	@UiChild(tagname = "action")
	public void addAction(final Widget action) {
		actions.add(action);
	}

	public boolean isChangeMonth() {
		return daySelector.isChangeMonth();
	}

	public void setChangeMonth(final boolean change) {
		daySelector.setChangeMonth(change);
	}

	public boolean isChangeYear() {
		return daySelector.isChangeYear();
	}

	public void setChangeYear(final boolean change) {
		daySelector.setChangeYear(change);
	}

	public void setShowClearAction(final boolean show) {
		if (show && (clearAction == null || clearAction.getParent() == null))
			clearAction = addAction(IMessages.INSTANCE.mdc_calendar_clear(), event -> setValue(null));
		else if (!show && clearAction != null && clearAction.getParent() != null)
			clearAction.removeFromParent();
	}

	public Date getMinDate() {
		return daySelector.getMinDate();
	}

	public void setMinDate(Date minDate) {
		daySelector.setMinDate(minDate);
	}

	public Date getMaxDate() {
		return daySelector.getMaxDate();
	}

	public void setMaxDate(Date maxDate) {
		daySelector.setMaxDate(maxDate);
	}

	@Override
	public void setBackgroundColor(Color color) {
		setStyleProperty(CssMixin.MDC_DATEPICKER__FILL, color.getCssName());
	}

	@Override
	public void setColor(Color color) {
		setStyleProperty(CssMixin.MDC_DATEPICKER__INK, color.getCssName());
	}

	public void setActiveColor(Color color) {
		setStyleProperty(CssMixin.MDC_DATEPICKER__ACTIVE_INK, color.getCssName());
	}

	public void setActiveBackgroundColor(Color color) {
		setStyleProperty(CssMixin.MDC_DATEPICKER__ACTIVE_FILL, color.getCssName());
	}

	public void setHeaderColor(Color color) {
		setStyleProperty(CssMixin.MDC_DATEPICKER__HEADER_INK, color.getCssName());
	}

	public void setHeaderBackgroundColor(Color color) {
		setStyleProperty(CssMixin.MDC_DATEPICKER__HEADER_FILL, color.getCssName());
	}

	public void setDateTooltip(final Date date, final String tooltip) {
		daySelector.setDateTooltip(date, tooltip);
	}

	public String getDateTooltip(final Date date) {
		return daySelector.getDateTooltip(date);
	}

	public String removeDateTooltip(final Date date) {
		return daySelector.removeDateTooltip(date);
	}
}
