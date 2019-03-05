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
package gwt.material.design.components.client.ui.misc.datePicker.selectors.month;

import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gwt.event.shared.HandlerRegistration;

import gwt.material.design.components.client.base.interfaces.HasSelection;
import gwt.material.design.components.client.base.mixin.HasSelectionMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.events.SelectionEvent.HasSelectionHandlers;
import gwt.material.design.components.client.events.SelectionEvent.SelectionHandler;
import gwt.material.design.components.client.lang.MdcDate;
import gwt.material.design.components.client.ui.html.Div;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class Months extends Div implements HasSelection<Integer>, HasSelectionHandlers<Integer> {

	protected final HasSelectionMixin<Months, Integer> selectionMixin = new HasSelectionMixin<>(this);
	protected final Map<Integer, MonthsItem> items = new LinkedHashMap<>();

	public Months() {
		super(CssName.MDC_DATEPICKER__MONTHS);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		drawMonths();
		if (getSelection() == null)
			setSelection((new MdcDate()).getMonth(), false);
	}

	public void drawMonths() {

		clear();
		items.clear();

		for (int month = 1; month <= 12; month++) {
			final MonthsItem item = new MonthsItem(month);			
			item.addClickHandler(event -> setSelection(item.getMonth()));
			items.put(month, item);
			add(item);
		}

	}

	protected void drawSelection(final Integer month) {
		unSelectAll();

		if (month == null)
			return;

		final MonthsItem item = items.getOrDefault(month, null);
		if (item != null)
			item.addStyleName(CssName.MDC_DATEPICKER__MONTHS__ACTIVE);

	}

	protected final native void unSelectAll()/*-{
		var itemClass = @gwt.material.design.components.client.constants.CssName::MDC_DATEPICKER__MONTHS__ITEM;
		var activeClass = @gwt.material.design.components.client.constants.CssName::MDC_DATEPICKER__MONTHS__ACTIVE;
		$wnd.jQuery('.' + itemClass).removeClass(activeClass);
	}-*/;

	@Override
	public HandlerRegistration addSelectionHandler(SelectionHandler<Integer> handler) {
		return selectionMixin.addSelectionHandler(handler);
	}

	@Override
	public void setSelection(Integer selected) {
		drawSelection(selected);
		selectionMixin.setSelection(selected);
	}

	@Override
	public void setSelection(Integer selected, boolean fireEvents) {
		drawSelection(selected);
		selectionMixin.setSelection(selected, fireEvents);
	}

	@Override
	public Integer getSelection() {
		return selectionMixin.getSelection();
	}
}
