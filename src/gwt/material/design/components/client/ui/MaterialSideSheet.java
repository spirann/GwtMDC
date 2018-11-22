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

import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.interfaces.HasOpen;
import gwt.material.design.components.client.base.interfaces.HasToggler;
import gwt.material.design.components.client.base.mixin.ToggleStyleMixin;
import gwt.material.design.components.client.base.mixin.TogglerMixin;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.html.Div;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialSideSheet extends Div implements HasOpen, HasToggler {

	protected final Div content = new Div(CssName.MDC_SIDE_SHEET__CONTENT);
	protected final Div scrim = new Div(CssName.MDC_SIDE_SHEET__SCRIM);

	protected final ToggleStyleMixin<MaterialSideSheet> openMixin = new ToggleStyleMixin<>(this,
			CssName.MDC_SIDE_SHEET__OPENED);
	protected final TogglerMixin<MaterialSideSheet> togglerMixin = new TogglerMixin<>(this);

	public MaterialSideSheet() {
		super(CssName.MDC_SIDE_SHEET);
	}

	@Override
	protected void onInitialize() {
		scrim.addClickHandler(event -> close());
		super.add(scrim, getElement());
		super.add(content, getElement());
		super.onInitialize();
	}

	@Override
	public void add(Widget child) {
		content.add(child);
	}
	
	@Override
	public void setToggler(String togglerId) {
		togglerMixin.setToggler(togglerId);		
	}

	@Override
	public String getToggler() {
		return togglerMixin.getToggler();
	}

	@Override
	public void setOpen(boolean open) {
		openMixin.toggle(open);
	}

	@Override
	public boolean isOpen() {
		return openMixin.isApplied();
	}

	@Override
	public void open() {
		setOpen(true);
	}

	@Override
	public void close() {
		setOpen(false);
	}
	
	@Override
	public void setWidth(String width) {
		setCssProperty(CssMixin.MDC_SIDE_SHEET__CONTENT_WIDTH, width);
	}
	
	@Override
	public void setBackgroundColor(Color color) {
		setCssProperty(CssMixin.MDC_SIDE_SHEET__FILL_COLOR, color.getCssName());
	}

	@Override
	public void setPadding(int padding) {
		content.setPadding(padding);
	}
	
	@Override
	public void setPaddingBottom(int paddingBottom) {
		content.setPaddingBottom(paddingBottom);
	}
	
	@Override
	public void setPaddingLeft(int paddingLeft) {
		content.setPaddingLeft(paddingLeft);
	}
	@Override
	public void setPaddingRight(int paddingRight) {
		content.setPaddingRight(paddingRight);
	}
	
	@Override
	public void setPaddingTop(int paddingTop) {
		content.setPaddingTop(paddingTop);
	}
	
	@Override
	public void setOverflow(Overflow overflow) {
		content.setOverflow(overflow);
	}
	
	@Override
	public void setOverflowX(Overflow overflow) {
		content.setOverflowX(overflow);
	}
	
	@Override
	public void setOverflowY(Overflow overflow) {
		content.setOverflowY(overflow);
	}
}
