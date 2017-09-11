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
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.HasAcceptHandlers;
import gwt.material.design.components.client.base.HasCancelHandlers;
import gwt.material.design.components.client.base.HasOpen;
import gwt.material.design.components.client.base.HasScrollable;
import gwt.material.design.components.client.base.mixin.ApplyStyleMixin;
import gwt.material.design.components.client.base.mixin.AttributeMixin;
import gwt.material.design.components.client.constants.ButtonType;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.Display;
import gwt.material.design.components.client.constants.Role;
import gwt.material.design.components.client.events.AcceptEvent;
import gwt.material.design.components.client.events.CancelEvent;
import gwt.material.design.components.client.handlers.AcceptHandler;
import gwt.material.design.components.client.handlers.CancelHandler;
import gwt.material.design.components.client.ui.html.Aside;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Footer;
import gwt.material.design.components.client.ui.html.H2;
import gwt.material.design.components.client.ui.html.Header;
import gwt.material.design.components.client.ui.html.Section;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialDialog extends Aside
		implements HasAcceptHandlers, HasCancelHandlers, HasOpen, HasScrollable {

	// /////////////////////////////////////////////////////////////
	// Initialize java script component
	// /////////////////////////////////////////////////////////////
	protected JavaScriptObject jsElement;

	protected native void jsInit()/*-{
		var element = this.@gwt.material.design.components.client.ui.MaterialDialog::getElement()();
		this.@gwt.material.design.components.client.ui.MaterialDialog::jsElement = $wnd.mdc.dialog.MDCDialog.attachTo(element);
	}-*/;

	// /////////////////////////////////////////////////////////////
	// Dialog
	// /////////////////////////////////////////////////////////////
	protected Div surface = new Div(CssName.MDC_DIALOG_SURFACE);
	protected Header header = new Header(CssName.MDC_DIALOG_HEADER);
	protected H2 headerTitle = new H2(CssName.MDC_DIALOG_HEADER_TITLE) {
		@Override
		public void setId(String id) {
			super.setId(id);
			ariaLabelledbyMixin.setAttribute(id);
		}
	};
	protected Section body = new Section(CssName.MDC_DIALOG_BODY) {
		@Override
		public void setId(String id) {
			super.setId(id);
			ariaDescribedbyMixin.setAttribute(id);
		}
	};
	protected Footer footer = new Footer(CssName.MDC_DIALOG_FOOTER);
	protected MaterialButton accept = new MaterialButton();
	protected MaterialButton cancel = new MaterialButton();
	protected Div backdrop = new Div(CssName.MDC_DIALOG_BACKDROP);

	// /////////////////////////////////////////////////////////////
	// Style mixin
	// /////////////////////////////////////////////////////////////
	protected final AttributeMixin<MaterialDialog> ariaHiddenMixin = new AttributeMixin<>(this, "aria-hidden", "true");
	protected final AttributeMixin<MaterialDialog> ariaLabelledbyMixin = new AttributeMixin<>(this, "aria-labelledby");
	protected final AttributeMixin<MaterialDialog> ariaDescribedbyMixin = new AttributeMixin<>(this,
			"aria-describedby");
	protected final ApplyStyleMixin<Section> scrollableMixin = new ApplyStyleMixin<>(body,
			CssName.MDC_DIALOG_BODY_SCROLLABLE);

	private HandlerRegistration handler;

	private boolean initialized = false;

	public MaterialDialog() {
		super(CssName.MDC_DIALOG);
		setRole(Role.ALERT_DIALOG);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {

			header.add(headerTitle);

			cancel.addStyleName(CssName.MDC_DIALOG_FOOTER_BUTTON);
			cancel.addStyleName(CssName.MDC_DIALOG_FOOTER_BUTTON_CANCEL);
			cancel.addStyleName(CssName.MDC_DIALOG_ACTION);
			footer.add(cancel);

			accept.addStyleName(CssName.MDC_DIALOG_FOOTER_BUTTON);
			accept.addStyleName(CssName.MDC_DIALOG_FOOTER_BUTTON_ACCEPT);
			accept.addStyleName(CssName.MDC_DIALOG_ACTION);
			footer.add(accept);

			surface.add(header);
			surface.add(body);
			surface.add(footer);
			super.add(surface);
			super.add(backdrop);

			initializeAcceptEventListener();
			initializeCancelEventListener();
			jsInit();

		}

		if (accept.getText().isEmpty()) {
			accept.setDisplay(Display.NONE);
		}

		if (cancel.getText().isEmpty()) {
			cancel.setDisplay(Display.NONE);
		}
	}

	@Override
	protected void onUnload() {
		super.onUnload();

		if (handler != null) {
			handler.removeHandler();
		}
	}

	@Override
	public void add(final Widget child) {
		body.add(child);
	}

	@Override
	public Widget getWidget(final int index) {
		return body.getWidget(index);
	}

	@Override
	public int getWidgetCount() {
		return body.getWidgetCount();
	}

	@Override
	public void setTitle(final String title) {
		super.setTitle(title);
		headerTitle.getElement().setInnerText(title);
	}

	@Override
	public void setOpen(boolean open) {

		if (!isAttached() && handler == null) {

			handler = addAttachHandler(event -> {

				if (event.isAttached()) {
					if (open) {
						open();
					} else {
						close();
					}
				} else if (handler != null) {
					handler.removeHandler();
					handler = null;
				}

			});

		} else if (open) {
			open();
		} else {
			close();
		}

	}

	@Override
	public native boolean isOpen()/*-{
		var dialog = this.@gwt.material.design.components.client.ui.MaterialDialog::jsElement;
		return dialog && dialog.open;
	}-*/;

	@Override
	public native void open()/*-{
		var dialog = this.@gwt.material.design.components.client.ui.MaterialDialog::jsElement;
		dialog.show();
	}-*/;

	@Override
	public native void close()/*-{
		var dialog = this.@gwt.material.design.components.client.ui.MaterialDialog::jsElement;
		dialog.close();
	}-*/;

	protected native void initializeAcceptEventListener()/*-{
		var _this = this;
		var element = _this.@gwt.material.design.components.client.ui.MaterialDialog::getElement()();
		element.addEventListener('MDCDialog:accept', function () {
			_this.@gwt.material.design.components.client.ui.MaterialDialog::fireAcceptEvent()();
		});
	}-*/;

	protected native void initializeCancelEventListener()/*-{
		var _this = this;
		var element = _this.@gwt.material.design.components.client.ui.MaterialDialog::getElement()();
		element.addEventListener('MDCDialog:cancel', function () {
			_this.@gwt.material.design.components.client.ui.MaterialDialog::fireCancelEvent()();
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

	@Override
	public void setScrollable(final boolean scrollable) {
		scrollableMixin.setApply(scrollable);
	}

	@Override
	public boolean isScrollable() {
		return scrollableMixin.isApplied();
	}

	public void setAcceptText(final String text) {
		accept.setText(text);

		if (text == null || text.isEmpty()) {
			accept.setDisplay(Display.NONE);
		} else {
			accept.setDisplay(Display.INITIAL);
		}
	}

	public String getAcceptText() {
		return accept.getText();
	}

	public void setAcceptEnabled(final boolean enabled) {
		accept.setEnabled(enabled);
	}

	public boolean isAcceptEnabled() {
		return accept.isEnabled();
	}
	
	public void setAcceptType(final ButtonType type){
		accept.setType(type);
	}
	
	public ButtonType getAcceptType(){
		return accept.getType();
	}

	public void setCancelText(final String text) {
		cancel.setText(text);

		if (text == null || text.isEmpty()) {
			cancel.setDisplay(Display.NONE);
		} else {
			cancel.setDisplay(Display.INITIAL);
		}
	}

	public String getCancelText() {
		return cancel.getText();
	}

	public void setCancelEnabled(final boolean enabled) {
		cancel.setEnabled(enabled);
	}

	public boolean isCancelEnabled() {
		return cancel.isEnabled();
	}
	
	public void setCancelType(final ButtonType type){
		cancel.setType(type);
	}
	
	public ButtonType getCancelType(){
		return cancel.getType();
	}

	@Override
	public void setPadding(int padding) {
		body.setPadding(padding);
	}

	@Override
	public void setPaddingBottom(int paddingBottom) {
		body.setPaddingBottom(paddingBottom);
	}

	@Override
	public void setPaddingLeft(int paddingLeft) {
		body.setPaddingLeft(paddingLeft);
	}

	@Override
	public void setPaddingRight(int paddingRight) {
		body.setPaddingRight(paddingRight);
	}

	@Override
	public void setPaddingTop(int paddingTop) {
		body.setPaddingTop(paddingTop);
	}
}
