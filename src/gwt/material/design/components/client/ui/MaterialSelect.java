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
package gwt.material.design.components.client.ui;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.interfaces.HasLabel;
import gwt.material.design.components.client.base.interfaces.HasType;
import gwt.material.design.components.client.base.interfaces.HasUnbordered;
import gwt.material.design.components.client.base.mixin.TextMixin;
import gwt.material.design.components.client.base.mixin.ToggleStyleMixin;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.base.widget.MaterialSelectedField;
import gwt.material.design.components.client.base.widget.MaterialValuedField;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.SelectMenuType;
import gwt.material.design.components.client.ui.html.Icon;
import gwt.material.design.components.client.ui.html.Select;
import gwt.material.design.components.client.ui.misc.input.MaterialFloatLabel;
import gwt.material.design.components.client.ui.misc.input.MaterialLineRipple;
import gwt.material.design.components.client.ui.misc.input.MaterialNotchedOutline;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialSelect<V> extends MaterialValuedField<V> implements HasLabel, HasType<SelectMenuType>, HasUnbordered {

	protected final Select select = new Select(CssName.MDC_SELECT__NATIVE_CONTROL);
	protected final MaterialFloatLabel label = new MaterialFloatLabel();
	protected final MaterialLineRipple lineRipple = new MaterialLineRipple();
	protected final MaterialNotchedOutline notchedOutline = new MaterialNotchedOutline();
	protected final Icon dropdownIcon = new Icon(CssName.MDC_SELECT__DROPDOWN_ICON);
	
	protected final ToggleStyleMixin<MaterialSelect<V>> unborderedMixin = new ToggleStyleMixin<>(this, CssName.MDC_SELECT__UNBORDERED);
	protected final TypeMixin<MaterialSelect<V>, SelectMenuType> typeMixin = new TypeMixin<>(this);

	public MaterialSelect() {
		super(CssName.MDC_SELECT);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return new $wnd.mdc.select.MDCSelect(element);
	}-*/;

	@Override
	protected void onInitialize() {
		notchedOutline.add(label);
		add(dropdownIcon);
		add(select);
		add(lineRipple);
		add(notchedOutline);
		super.onInitialize();
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		V value = getValue();
		if (value != null)
			setValue(value, false);
		else {
			final int index = getSelectedIndex();
			if (index == -1)
				setValue(value, false);
			else
				onSelect(index, false);
		}
		
		addResizeHandler(event -> layout());
	}

	protected native void layout()/*-{
		var jsElement = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		if (jsElement)
			jsElement.layout();
	}-*/;

	@Override
	protected void fireChangeEvent() {
		onSelect(getSelectedIndex(), false);
		super.fireChangeEvent();
	}

	public void setSelectedIndex(final int index) {
		setSelectedIndex(index, true);
	}

	public void setSelectedIndex(final int index, final boolean fireEvents) {
		selectedIndex(index);
		if (fireEvents)
			fireChangeEvent();
	}

	protected native void selectedIndex(final Integer index)/*-{
		var select = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		if (select)
			select.selectedIndex = (index ? index : -1);
	}-*/;

	public native int getSelectedIndex() /*-{
		var select = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		if (select)
			return select.selectedIndex;
		else
			return -1;
	}-*/;

	@Override
	public void setValue(V value) {
		setValue(value, true);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void setValue(V value, boolean fireEvents) {
		if (initialized) {
			final Widget item = select.getChildrenList().stream().filter(widget -> ((Option<V>) widget).getValue().equals(value)).findAny().orElse(null);
			if (item == null)
				selectedIndex(-1);
			else
				selectedIndex(select.getWidgetIndex(item));
		}
		super.setValue(value, fireEvents);
	}

	protected void onSelect(final int index) {
		onSelect(index, true);
	}

	@SuppressWarnings("unchecked")
	protected void onSelect(final int index, final boolean fireEvent) {
		if (index > -1 && index < select.getWidgetCount())
			super.setValue(((Option<V>) select.getWidget(index)).getValue(), fireEvent);
		else
			super.setValue(null, fireEvent);
	}

	@Override
	@SuppressWarnings("deprecation")
	protected void add(Widget child, com.google.gwt.user.client.Element container) {
		if (child instanceof Option)
			select.add(child);
		else
			super.add(child, container);
	}

	public void addOption(final String text, final V value) {
		final Option<V> option = new Option<>();
		option.setText(text);
		option.setValue(value);
		add(option);
	}

	@Override
	public void setLabel(String label) {
		this.label.setText(label);
	}

	@Override
	public String getLabel() {
		return this.label.getText();
	}

	@Override
	public void setType(SelectMenuType type) {
		typeMixin.setType(type);
		if(initialized)
			layout();
	}

	@Override
	public SelectMenuType getType() {
		return typeMixin.getType();
	}

	@Override
	public void setUnbordered(boolean unbordered) {
		unborderedMixin.toggle(unbordered);
	}

	@Override
	public boolean isUnbordered() {
		return unborderedMixin.isApplied();
	}

	@Override
	public void setBackgroundColor(Color color) {
		setCssProperty(CssMixin.MDC_INPUT__FILL_COLOR, color.getCssName());
	}

	@Override
	public void setColor(Color color) {
		setCssProperty(CssMixin.MDC_INPUT__INK_COLOR, color.getCssName());
	}

	public void setFocusedColor(Color color) {
		setCssProperty(CssMixin.MDC_INPUT__FOCUSED_COLOR, color.getCssName());
	}

	public static class Option<T> extends MaterialSelectedField implements HasText {

		protected final TextMixin<Option<T>> textMixin = new TextMixin<>(this);

		private boolean initialized = false;
		private T value;

		public Option() {
			super(HtmlElements.OPTION.createElement(), CssName.MDC_TYPOGRAPHY);
		}

		@Override
		protected native JavaScriptObject jsInit(final Element element)/*-{
			return element;
		}-*/;

		@Override
		public String getText() {
			return textMixin.getText();
		}

		@Override
		public void setText(String text) {
			textMixin.setText(text);
		}

		public T getValue() {
			return value;
		}

		private void check() {
			if (initialized)
				throw new IllegalArgumentException("Value cannot be modified");
			else
				initialized = true;
		}

		public void setValue(T value) {
			check();
			this.value = value;
		}

		/**
		 * Help to works with UiBinder
		 * 
		 * @param value
		 */
		@SuppressWarnings("unchecked")
		public void setValue(final String value) {
			check();
			this.value = (T) value;
		}

		/**
		 * Help to works with UiBinder
		 * 
		 * @param value
		 */
		@SuppressWarnings("unchecked")
		public void setValue(final Integer value) {
			check();
			this.value = (T) value;
		}

		/**
		 * Help to works with UiBinder
		 * 
		 * @param value
		 */
		@SuppressWarnings("unchecked")
		public void setValue(final Double value) {
			check();
			this.value = (T) value;
		}

		/**
		 * Help to works with UiBinder
		 * 
		 * @param value
		 */
		@SuppressWarnings("unchecked")
		public void setValue(final Long value) {
			check();
			this.value = (T) value;
		}

		/**
		 * Help to works with UiBinder
		 * 
		 * @param value
		 */
		@SuppressWarnings("unchecked")
		public void setValue(final Boolean value) {
			check();
			this.value = (T) value;
		}
	}
}
