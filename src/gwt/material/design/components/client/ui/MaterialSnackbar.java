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
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.mixin.ApplyStyleMixin;
import gwt.material.design.components.client.base.mixin.TextMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.html.Button;
import gwt.material.design.components.client.ui.html.Div;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialSnackbar extends Div implements HasText {

	// /////////////////////////////////////////////////////////////
	// Initialize java script component
	// /////////////////////////////////////////////////////////////
	protected JavaScriptObject jsElement;

	protected native void jsInit()/*-{
		var element = this.@gwt.material.design.components.client.ui.MaterialSnackbar::getElement()();
		this.@gwt.material.design.components.client.ui.MaterialSnackbar::jsElement = $wnd.mdc.snackbar.MDCSnackbar
				.attachTo(element);
	}-*/;

	protected Div text = new Div(CssName.MDC_SNACKBAR_TEXT);
	protected Div actionWrapper = new Div(CssName.MDC_SNACKBAR_ACTION_WRAPPER);
	protected Button action = new Button(CssName.MDC_BUTTON, CssName.MDC_SNACKBAR_ACTION_BUTTON);

	protected final TextMixin<Div> textMixin = new TextMixin<>(text);
	protected final ApplyStyleMixin<MaterialSnackbar> atStartMixin = new ApplyStyleMixin<>(this,
			CssName.MDC_SNACKBAR_ALIGN_START);

	protected String actionText;
	protected boolean multiline = false;
	protected boolean actionOnBottom = false;
	protected int timeout = 2750;

	public MaterialSnackbar() {
		super(CssName.MDC_SNACKBAR);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		actionWrapper.add(action);

		add(text);
		add(actionWrapper);

		jsInit();
	}

	protected native void show(final String text, final String actionText, final boolean actionOnBottom,
			final boolean multiline, final int timeout)/*-{

		var _this = this;
		var snackbar = _this.@gwt.material.design.components.client.ui.MaterialSnackbar::jsElement;

		var _action = null;

		if (actionText) {
			_action = function() {
				_this.@gwt.material.design.components.client.ui.MaterialSnackbar::fireClickEvent()();
			};
		}

		var dataObj = {
			message : text,
			actionText : actionText,
			actionHandler : _action,
			actionOnBottom : actionOnBottom,
			multiline : multiline,
			timeout : timeout
		};

		snackbar.show(dataObj);

	}-*/;

	public native void setDismissesOnAction(final boolean dismissesOnAction) /*-{
		var snackbar = _this.@gwt.material.design.components.client.ui.MaterialSnackbar::jsElement;
		snackbar.dismissesOnAction = dismissesOnAction;
	}-*/;

	public native boolean isDismissesOnAction() /*-{
		var snackbar = _this.@gwt.material.design.components.client.ui.MaterialSnackbar::jsElement;
		snackbar.dismissesOnAction = dismissesOnAction;
	}-*/;

	protected void fireClickEvent() {
		ClickEvent.fireNativeEvent(Document.get().createClickEvent(0, 0, 0, 0, 0, false, false, false, false), this);
	}

	@Override
	public String getText() {
		return textMixin.getText();
	}

	@Override
	public void setText(String text) {
		textMixin.setText(text);
	}

	public void show() {
		show(getText(), actionText, actionOnBottom, multiline, timeout);
	}

	public void setActionText(final String text) {
		actionText = text;
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return action.addClickHandler(handler);
	}

	public void setAtStart(final boolean atStart) {
		atStartMixin.setApply(atStart);
	}

	public boolean isAtStart() {
		return atStartMixin.isApplied();
	}

	public boolean isMultiline() {
		return multiline;
	}

	public void setMultiline(boolean multiline) {
		this.multiline = multiline;
	}

	public boolean isActionOnBottom() {
		return actionOnBottom;
	}

	public void setActionOnBottom(boolean actionOnBottom) {
		this.actionOnBottom = actionOnBottom;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

}
