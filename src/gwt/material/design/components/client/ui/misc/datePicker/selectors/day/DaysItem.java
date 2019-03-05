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
package gwt.material.design.components.client.ui.misc.datePicker.selectors.day;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.lang.MdcDate;
import gwt.material.design.components.client.ui.MaterialLabel;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.utils.helper.StyleHelper;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class DaysItem extends Div {

	protected final MdcDate date;

	protected final MaterialLabel label = new MaterialLabel(CssName.MDC_TYPOGRAPHY__CAPTION);
	protected final Div tooltipIndicator = new Div(CssName.MDC_DATEPICKER__DAYS__TOOLTIP_INDICATOR);

	protected final Collection<String> tooltips = new LinkedList<>();

	public DaysItem(final MdcDate date) {
		super(CssName.MDC_DATEPICKER__DAYS__ITEM);
		this.date = date;
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		drawTooltips();
	}

	@Override
	protected void onInitialize() {
		ripleMixin.initialize();
		super.onInitialize();
		label.setText(String.valueOf(date.getDay()));
		StyleHelper.setProperty(getElement(), "timestamp", date.getTimestamp());
		add(label);
		add(tooltipIndicator);
	}

	public MdcDate getDate() {
		return date;
	}

	public void addTooltip(final String... tooltips) {
		this.tooltips.addAll(Arrays.asList(tooltips));
		drawTooltips();
	}

	public void removeTooltip(final String tooltip) {
		this.tooltips.remove(tooltip);
		drawTooltips();
	}

	public void clearTooltips() {
		this.tooltips.clear();
		drawTooltips();
	}

	protected void drawTooltips() {
		if (initialized) {
			setAttribute("count_tooltips", String.valueOf(this.tooltips.size()));
			if (this.tooltips.isEmpty())
				setTooltip(null);
			else
				setTooltip("<div style=\"text-align: left;\">" + this.tooltips.stream()
						.map(tooltip -> "&#8226; " + tooltip).collect(Collectors.joining("<br/>")) + "</div>");
		}
	}
}
