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
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.interfaces.HasOpen;
import gwt.material.design.components.client.base.widget.MaterialWidget;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.Role;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Footer;
import gwt.material.design.components.client.ui.html.H2;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialDialog extends Div implements HasOpen {

	// /////////////////////////////////////////////////////////////
	// Dialog
	// /////////////////////////////////////////////////////////////
	protected final Div container = new Div(CssName.MDC_DIALOG__CONTAINER);
	protected final Div surface = new Div(CssName.MDC_DIALOG__SURFACE);
	protected final H2 title = new H2(CssName.MDC_DIALOG__TITLE);
	protected final Div content = new Div(CssName.MDC_DIALOG__CONTENT);
	protected final Footer footer = new Footer(CssName.MDC_DIALOG__ACTIONS);
	protected final Div scrim = new Div(CssName.MDC_DIALOG__SCRIM);

	private boolean autoStackButtons = false;

	public MaterialDialog() {
		super(CssName.MDC_DIALOG);
		setRole(Role.ALERT_DIALOG);
		setAriaModal(true);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return new $wnd.mdc.dialog.MDCDialog(element);
	}-*/;

	@Override
	protected void onInitialize() {

		surface.add(title);
		surface.add(content);
		surface.add(footer);

		container.add(surface);

		add(container);
		add(scrim);

		super.onInitialize();

		setAutoStackButtons(autoStackButtons);
		initEvents();
	}

	protected native void initEvents()/*-{
		var dialog = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;

		dialog.listen('MDCDialog:opening', function() {
			// fire opening event
			console.log('fire opening event');
		});
		dialog.listen('MDCDialog:opened', function() {
			// fire opened event
			console.log('fire opened event');
		});
		dialog.listen('MDCDialog:closing', function(event) {
			var action = event.detail.action;
			// fire closing event
			console.log('fire closing event: ' + action);
		});
		dialog.listen('MDCDialog:closed', function(event) {
			var action = event.detail.action;
			// fire closed event
			console.log('fire closed event: ' + action);
		});
	}-*/;

	@UiChild(tagname = "content")
	public void addContent(final Widget child) {
		content.add(child);
	}

	@UiChild(tagname = "action")
	public void addFooter(final Widget child, final boolean closeOnClick) {
		child.addStyleName(CssName.MDC_DIALOG__BUTTON);
		if (closeOnClick)
			child.getElement().setAttribute("data-mdc-dialog-action", ((MaterialWidget) child).getId());
		footer.add(child);
	}

	public boolean isAutoStackButtons() {
		return autoStackButtons;
	}

	@Override
	public void setTitle(String title) {
		this.title.setText(title);
	}

	public native void setAutoStackButtons(final boolean autoStackButtons)/*-{
		this.@gwt.material.design.components.client.ui.MaterialDialog::autoStackButtons = autoStackButtons;
		var dialog = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		if (dialog)
			dialog.autoStackButtons = autoStackButtons;
	}-*/;

	@Override
	public void setOpen(boolean open) {
		if (open)
			open();
		else
			close();
	}

	@Override
	public native boolean isOpen() /*-{
		var dialog = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		return dialog && dialog.isOpen;
	}-*/;

	@Override
	public native void open()/*-{
		var dialog = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		if (dialog)
			dialog.open();
	}-*/;

	@Override
	public native void close() /*-{
		var dialog = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		if (dialog)
			dialog.close();
	}-*/;

	public void setHeaderBackgroundColor(Color color) {
		setStyleProperty(CssMixin.MDC_DIALOG__HEADER_FILL_COLOR, color.getCssName());
	}

	public void setHeaderTextColor(Color color) {
		setStyleProperty(CssMixin.MDC_DIALOG__HEADER_INK_COLOR, color.getCssName());
	}

	@Override
	public void setBackgroundColor(Color color) {
		setStyleProperty(CssMixin.MDC_DIALOG__BODY_FILL_COLOR, color.getCssName());
	}

	@Override
	public void setTextColor(Color color) {
		setStyleProperty(CssMixin.MDC_DIALOG__BODY_INK_COLOR, color.getCssName());
	}

	public void setFooterBackgroundColor(Color color) {
		setStyleProperty(CssMixin.MDC_DIALOG__ACTION_FILL_COLOR, color.getCssName());
		setStyleProperty(CssMixin.MDC_DIALOG__ACTION_RAISED_INK_COLOR, color.getCssName());
	}

	public void setFooterTextColor(Color color) {
		setStyleProperty(CssMixin.MDC_DIALOG__ACTION_INK_COLOR, color.getCssName());
	}
}
