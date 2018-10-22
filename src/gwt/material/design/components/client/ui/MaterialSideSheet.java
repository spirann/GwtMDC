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

import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.interfaces.HasOpen;
import gwt.material.design.components.client.base.mixin.ApplyStyleMixin;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.html.Div;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialSideSheet extends Div implements HasOpen {

	protected final Div content = new Div(CssName.MDC_SIDE_SHEET__CONTENT);
	protected final Div scrim = new Div(CssName.MDC_SIDE_SHEET__SCRIM);

	protected final ApplyStyleMixin<MaterialSideSheet> openMixin = new ApplyStyleMixin<>(this,
			CssName.MDC_SIDE_SHEET__OPENED);

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
	public void setOpen(boolean open) {
		openMixin.setApply(open);
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
	public void setBackgroundColor(Color color) {
		content.setStyleProperty(CssMixin.MDC_SIDE_SHEET__FILL_COLOR, color.getCssName());
	}

}
