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
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.interfaces.HasAcceptHandlers;
import gwt.material.design.components.client.base.interfaces.HasCancelHandlers;
import gwt.material.design.components.client.base.interfaces.HasCloseHandlers;
import gwt.material.design.components.client.base.interfaces.HasClosingHandlers;
import gwt.material.design.components.client.base.interfaces.HasOpen;
import gwt.material.design.components.client.base.interfaces.HasOpenHandlers;
import gwt.material.design.components.client.base.interfaces.HasOpeningHandlers;
import gwt.material.design.components.client.constants.CloseAction;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.Role;
import gwt.material.design.components.client.events.AcceptEvent;
import gwt.material.design.components.client.events.AcceptEvent.AcceptHandler;
import gwt.material.design.components.client.events.CancelEvent;
import gwt.material.design.components.client.events.CancelEvent.CancelHandler;
import gwt.material.design.components.client.events.CloseEvent;
import gwt.material.design.components.client.events.CloseEvent.CloseHandler;
import gwt.material.design.components.client.events.ClosingEvent;
import gwt.material.design.components.client.events.ClosingEvent.ClosingHandler;
import gwt.material.design.components.client.events.OpenEvent;
import gwt.material.design.components.client.events.OpenEvent.OpenHandler;
import gwt.material.design.components.client.events.OpeningEvent;
import gwt.material.design.components.client.events.OpeningEvent.OpeningHandler;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Footer;
import gwt.material.design.components.client.ui.html.H2;
import gwt.material.design.components.client.utils.helper.JsHelper;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialDialog extends Div implements  HasAcceptHandlers, HasCancelHandlers, HasOpenHandlers, HasOpeningHandlers, HasCloseHandlers, HasClosingHandlers,HasOpen {

	protected static final String NATIVE_ACTION_ACCEPT = "accept";
	protected static final String NATIVE_ACTION_CANCEL = "close";
	
	// /////////////////////////////////////////////////////////////
	// Dialog
	// /////////////////////////////////////////////////////////////
	protected final Div container = new Div(CssName.MDC_DIALOG__CONTAINER);
	protected final Div surface = new Div(CssName.MDC_DIALOG__SURFACE);
	protected final H2 title = new H2(CssName.MDC_DIALOG__TITLE);
	protected final Div content = new Div(CssName.MDC_DIALOG__CONTENT);
	protected final Footer footer = new Footer(CssName.MDC_DIALOG__ACTIONS);
	protected final MaterialButton accept = new MaterialButton();
	protected final MaterialButton cancel = new MaterialButton();
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

		cancel.addStyleName(CssName.MDC_DIALOG__BUTTON);
		cancel.getElement().setAttribute("data-mdc-dialog-action", NATIVE_ACTION_CANCEL);
		footer.add(cancel);
		
		accept.addStyleName(CssName.MDC_DIALOG__BUTTON);
		accept.getElement().setAttribute("data-mdc-dialog-action", NATIVE_ACTION_ACCEPT);
		footer.add(accept);
		
		surface.add(title);
		surface.add(content);
		surface.add(footer);

		container.add(surface);

		add(container);
		add(scrim);

		super.onInitialize();

		setAutoStackButtons(autoStackButtons);
		preventFooter();
		initEvents();
	}
	
	protected final void preventFooter() {
		JsHelper.hideEmpty(accept);
		JsHelper.hideEmpty(cancel);
		JsHelper.hideEmpty(footer);
	}

	protected native void initEvents()/*-{
		var _this = this; 
		var dialog = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;

		function getCloseAction(action){
			if(action == @gwt.material.design.components.client.ui.MaterialDialog::NATIVE_ACTION_ACCEPT)
				return @gwt.material.design.components.client.constants.CloseAction::ACCEPT;
			 else if(action == @gwt.material.design.components.client.ui.MaterialDialog::NATIVE_ACTION_CANCEL)
				return @gwt.material.design.components.client.constants.CloseAction::CANCEL;			
			 else 
				return @gwt.material.design.components.client.constants.CloseAction::NONE;			
		}

		dialog.listen('MDCDialog:opening', function(event) {
			_this.@gwt.material.design.components.client.ui.MaterialDialog::fireOpeningEvent()();
		});
		dialog.listen('MDCDialog:opened', function(event) { 
			_this.@gwt.material.design.components.client.ui.MaterialDialog::fireOpenEvent()();
		});
		dialog.listen('MDCDialog:closing', function(event) {
			var action = event.detail.action;
			var closeAction = getCloseAction(action);
			_this.@gwt.material.design.components.client.ui.MaterialDialog::fireClosingEvent(Lgwt/material/design/components/client/constants/CloseAction;)(closeAction);
		});
		dialog.listen('MDCDialog:closed', function(event) {
			var action = event.detail.action;
			var closeAction = getCloseAction(action);
			_this.@gwt.material.design.components.client.ui.MaterialDialog::fireCloseEvent(Lgwt/material/design/components/client/constants/CloseAction;)(closeAction);	
		});		
	}-*/;

	protected void fireAcceptEvent() {
		AcceptEvent.fire(MaterialDialog.this);
	}

	@Override
	public HandlerRegistration addAcceptHandler(final AcceptHandler handler) {
		return addHandler(handler, AcceptEvent.getType());
	}

	protected void fireCancelEvent() {
		CancelEvent.fire(MaterialDialog.this);
	}

	@Override
	public HandlerRegistration addCancelHandler(final CancelHandler handler) {
		return addHandler(handler, CancelEvent.getType());
	}
	
	protected void fireOpenEvent() {
		OpenEvent.fire(MaterialDialog.this);
	}

	@Override
	public HandlerRegistration addOpenHandler(final OpenHandler handler) {
		return addHandler(handler, OpenEvent.getType());
	}
	
	protected void fireOpeningEvent() {
		OpeningEvent.fire(MaterialDialog.this);
	}

	@Override
	public HandlerRegistration addOpeningHandler(final OpeningHandler handler) {
		return addHandler(handler, OpeningEvent.getType());
	}
	
	protected void fireCloseEvent(final CloseAction action) {
		CloseEvent.fire(MaterialDialog.this, action);
		switch (action) {
		case ACCEPT:
			fireAcceptEvent();
			break;
		case CANCEL:
			fireCancelEvent();
			break;
		default:
			break;
		}
	}

	@Override
	public HandlerRegistration addCloseHandler(final CloseHandler handler) {
		return addHandler(handler, CloseEvent.getType());
	}
	
	protected void fireClosingEvent(final CloseAction action) {
		ClosingEvent.fire(MaterialDialog.this, action);
	}

	@Override
	public HandlerRegistration addClosingHandler(final ClosingHandler handler) {
		return addHandler(handler, ClosingEvent.getType());
	}
	
	@UiChild(tagname = "content")
	public void addContent(final Widget child) {
		content.add(child);
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
	
	public void setAcceptText(final String text) {
		accept.setText(text);
		preventFooter();
	}

	public String getAcceptText() {
		return accept.getText();
	}
	
	public void setAcceptEnabled(final boolean enabled) {
		accept.setEnabled(enabled);
	}

	public void setCancelText(final String text) {
		cancel.setText(text);
		preventFooter();
	}

	public String getCancelText() {
		return cancel.getText();
	}
	
	public void setHeaderBackgroundColor(Color color) {
		setCssProperty(CssMixin.MDC_DIALOG__HEADER_FILL_COLOR, color.getCssName());
	}

	public void setHeaderTextColor(Color color) {
		setCssProperty(CssMixin.MDC_DIALOG__HEADER_INK_COLOR, color.getCssName());
	}

	@Override
	public void setBackgroundColor(Color color) {
		setCssProperty(CssMixin.MDC_DIALOG__BODY_FILL_COLOR, color.getCssName());
	}

	@Override
	public void setColor(Color color) {
		setCssProperty(CssMixin.MDC_DIALOG__BODY_INK_COLOR, color.getCssName());
	}

	public void setFooterBackgroundColor(Color color) {
		setCssProperty(CssMixin.MDC_DIALOG__ACTION_FILL_COLOR, color.getCssName());
		setCssProperty(CssMixin.MDC_DIALOG__ACTION_RAISED_INK_COLOR, color.getCssName());
	}

	public void setFooterTextColor(Color color) {
		setCssProperty(CssMixin.MDC_DIALOG__ACTION_INK_COLOR, color.getCssName());
	}
}
