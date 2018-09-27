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
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.interfaces.HasAcceptHandlers;
import gwt.material.design.components.client.base.interfaces.HasCancelHandlers;
import gwt.material.design.components.client.base.interfaces.HasOpen;
import gwt.material.design.components.client.base.interfaces.HasScrollable;
import gwt.material.design.components.client.base.mixin.ApplyStyleMixin;
import gwt.material.design.components.client.base.mixin.AttributeMixin;
import gwt.material.design.components.client.constants.ButtonType;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.Display;
import gwt.material.design.components.client.constants.Role;
import gwt.material.design.components.client.events.AcceptEvent;
import gwt.material.design.components.client.events.AcceptEvent.AcceptHandler;
import gwt.material.design.components.client.events.CancelEvent;
import gwt.material.design.components.client.events.CancelEvent.CancelHandler;
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
public class MaterialDialog extends Aside implements HasAcceptHandlers, HasCancelHandlers, HasOpen, HasScrollable {

	// /////////////////////////////////////////////////////////////
	// Dialog
	// /////////////////////////////////////////////////////////////
	protected Div surface = new Div(CssName.MDC_DIALOG__SURFACE);
	protected Header header = new Header(CssName.MDC_DIALOG__HEADER);
	protected H2 headerTitle = new H2(CssName.MDC_DIALOG__HEADER_TITLE);
	protected Section body = new Section(CssName.MDC_DIALOG__BODY);
	protected Footer footer = new Footer(CssName.MDC_DIALOG__FOOTER);
	protected MaterialButton accept = new MaterialButton();
	protected MaterialButton cancel = new MaterialButton();
	protected Div backdrop = new Div(CssName.MDC_DIALOG__BACKDROP);

	// /////////////////////////////////////////////////////////////
	// Style mixin
	// /////////////////////////////////////////////////////////////
	protected final AttributeMixin<MaterialDialog> ariaHiddenMixin = new AttributeMixin<>(this, "aria-hidden", "true");
	protected final AttributeMixin<MaterialDialog> ariaLabelledbyMixin = new AttributeMixin<>(this, "aria-labelledby");
	protected final AttributeMixin<MaterialDialog> ariaDescribedbyMixin = new AttributeMixin<>(this,
			"aria-describedby");
	protected final ApplyStyleMixin<Section> scrollableMixin = new ApplyStyleMixin<>(body,
			CssName.MDC_DIALOG__BODY_SCROLLABLE);

	private HandlerRegistration handler;
	
	private boolean autoCloseOnCancel = true;
	private boolean autoCloseOnAccept = true;
	private final ClickHandler acceptHandler = event -> {
		event.stopPropagation();
		if (autoCloseOnCancel) {
			close();
		}
		fireAcceptEvent();
	};
	private final ClickHandler cancelHandler = event -> {
		event.stopPropagation();
		if (autoCloseOnAccept) {
			close();
		}
		fireCancelEvent();
	};

	public MaterialDialog() {
		super(CssName.MDC_DIALOG);
		setRole(Role.ALERT_DIALOG);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return new $wnd.mdc.dialog.MDCDialog(element);
	}-*/;

	@Override
	protected void onInitialize() {

		backdrop.addClickHandler(cancelHandler);

		header.add(headerTitle);

		cancel.addStyleName(CssName.MDC_DIALOG__FOOTER_BUTTON);
		cancel.addStyleName(CssName.MDC_DIALOG__FOOTER_BUTTON_CANCEL);
		cancel.addStyleName(CssName.MDC_DIALOG__ACTION);
		cancel.addClickHandler(cancelHandler);
		footer.add(cancel);

		accept.addStyleName(CssName.MDC_DIALOG__FOOTER_BUTTON);
		accept.addStyleName(CssName.MDC_DIALOG__FOOTER_BUTTON_ACCEPT);
		accept.addStyleName(CssName.MDC_DIALOG__ACTION);
		accept.addClickHandler(acceptHandler);
		footer.add(accept);

		surface.add(header);
		surface.add(body);
		surface.add(footer);
		super.add(surface);
		super.add(backdrop);

		ariaLabelledbyMixin.setAttribute(headerTitle.getId());
		ariaDescribedbyMixin.setAttribute(body.getId());

		initializeAcceptEventListener();
		initializeCancelEventListener();

		super.onInitialize();
	}

	@Override
	protected void onLoad() {
		super.onLoad();

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
		var dialog = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		return dialog && dialog.open;
	}-*/;

	@Override
	public native void open()/*-{
		var dialog = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		dialog.show();
	}-*/;

	public native void open(final Element target)/*-{
		var dialog = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		dialog.lastFocusedTarget = target;
		dialog.show();
	}-*/;

	public void open(final Widget widget) {
		open(widget.getElement());
	}

	@Override
	public native void close()/*-{
		var dialog = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		dialog.close();
	}-*/;

	protected native void initializeAcceptEventListener()/*-{
		var _this = this;
		var element = _this.@gwt.material.design.components.client.ui.MaterialDialog::getElement()();
		element.addEventListener('MDCDialog:accept', function() {
			_this.@gwt.material.design.components.client.ui.MaterialDialog::fireAcceptEvent()();
		});
	}-*/;

	protected native void initializeCancelEventListener()/*-{
		var _this = this;
		var element = _this.@gwt.material.design.components.client.ui.MaterialDialog::getElement()();
		element.addEventListener('MDCDialog:cancel', function() {
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
	
	public boolean isAutoCloseOnCancel() {
		return autoCloseOnCancel;
	}

	public void setAutoCloseOnCancel(boolean autoCloseOnCancel) {
		this.autoCloseOnCancel = autoCloseOnCancel;
	}

	public boolean isAutoCloseOnAccept() {
		return autoCloseOnAccept;
	}

	public void setAutoCloseOnAccept(boolean autoCloseOnAccept) {
		this.autoCloseOnAccept = autoCloseOnAccept;
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

	public void setAcceptType(final ButtonType type) {
		accept.setType(type);
	}

	public void setAcceptTextColor(final Color color) {
		accept.setTextColor(color);
	}

	public void setAcceptBackgroundColor(final Color color) {
		accept.setBackgroundColor(color);
	}

	public ButtonType getAcceptType() {
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

	public void setCancelTextColor(final Color color) {
		cancel.setTextColor(color);
	}

	public void setCancelBackgroundColor(final Color color) {
		cancel.setBackgroundColor(color);
	}

	public void setCancelType(final ButtonType type) {
		cancel.setType(type);
	}

	public ButtonType getCancelType() {
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

	@Override
	public void setWidth(String width) {
		surface.setWidth(width);
	}

	@Override
	public void setMaxWidth(String maxWidth) {
		surface.setMaxWidth(maxWidth);
	}

	@Override
	public void setMinWidth(String minWidth) {
		surface.setMinWidth(minWidth);
	}

	@Override
	public void setHeight(String height) {
		surface.setHeight(height);
	}

	@Override
	public void setMaxHeight(String maxHeight) {
		surface.setMaxHeight(maxHeight);
	}

	@Override
	public void setMinHeight(String minHeight) {
		surface.setMinHeight(minHeight);
	}
}
