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
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.RootPanel;

import gwt.material.design.components.client.base.interfaces.FromString;
import gwt.material.design.components.client.base.mixin.TextMixin;
import gwt.material.design.components.client.base.mixin.ToggleStyleMixin;
import gwt.material.design.components.client.base.mixin.base.AttributeMixin;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssAttribute;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.html.Button;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.utils.helper.TimerHelper;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialSnackbar extends Div implements HasText {

	protected final Div text = new Div(CssName.MDC_SNACKBAR__TEXT);
	protected final Div actionWrapper = new Div(CssName.MDC_SNACKBAR__ACTION_WRAPPER);
	protected final Button action = new Button(CssName.MDC_SNACKBAR__ACTION_BUTTON);

	protected final TextMixin<Div> textMixin = new TextMixin<>(text);
	protected final ToggleStyleMixin<MaterialSnackbar> atStartMixin = new ToggleStyleMixin<>(this,
			CssName.MDC_SNACKBAR__ALIGN_START);
	protected final ToggleStyleMixin<MaterialSnackbar> multilineMixin = new ToggleStyleMixin<>(this,
			CssName.MDC_SNACKBAR__MULTILINE);
	protected final ToggleStyleMixin<MaterialSnackbar> actionOnButtonMixin = new ToggleStyleMixin<>(this,
			CssName.MDC_SNACKBAR__ACTION_ON_BUTTON);
	protected final AttributeMixin<MaterialSnackbar, String> ariaLiveMixin = new AttributeMixin<>(this,
			CssAttribute.ARIA_LIVE, "assertive", FromString.TO_STRING);
	protected final AttributeMixin<MaterialSnackbar, Boolean> ariaAtomicMixin = new AttributeMixin<>(this,
			CssAttribute.ARIA_ATOMIC, true, FromString.TO_BOOLEAN);
	protected final AttributeMixin<MaterialSnackbar, Boolean> ariaHiddenMixin = new AttributeMixin<>(this,
			CssAttribute.ARIA_HIDDEN, true, FromString.TO_BOOLEAN);

	protected String actionText;
	protected int timeout = 2750;

	public MaterialSnackbar() {
		super(CssName.MDC_SNACKBAR);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return new $wnd.mdc.snackbar.MDCSnackbar(element);
	}-*/;

	@Override
	protected void onInitialize() {
		actionWrapper.add(action);
		add(text);
		add(actionWrapper);
		super.onInitialize();
	}

	protected native void show(final String text, final String actionText, final boolean actionOnBottom,
			final boolean multiline, final int timeout)/*-{

		var _this = this;
		var snackbar = _this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;

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
		var snackbar = _this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		snackbar.dismissesOnAction = dismissesOnAction;
	}-*/;

	public native boolean isDismissesOnAction() /*-{
		var snackbar = _this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
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
		// ///////////////////////////////////////////////////////////////////////////
		// Turn visible
		// ///////////////////////////////////////////////////////////////////////////
		setVisible(true);
		// ///////////////////////////////////////////////////////////////////////////
		// Show snackbar
		// ///////////////////////////////////////////////////////////////////////////
		show(getText(), actionText, actionOnButtonMixin.isApplied(), multilineMixin.isApplied(), timeout);
		// ///////////////////////////////////////////////////////////////////////////
		// Adjust position
		// ///////////////////////////////////////////////////////////////////////////
		final Element fab = getFixedFab();
		final int time;
		if (fab == null) {
			time = timeout + 250;
		} else {
			time = timeout;
			final int thisPosition = getElement().getAbsoluteRight();
			final int fabPosition = fab.getAbsoluteLeft();
			final boolean isLargeScreen = Window.getClientWidth() >= 1024;
			final String adjustPosition = thisPosition >= fabPosition
					? "calc(56px + " + (isLargeScreen ? "1.5rem" : "1rem") + ")"
					: "0px";
			setCssProperty(CssMixin.MDC_SNACKBAR__POSITION_ADJUST, adjustPosition);
		}
		// ///////////////////////////////////////////////////////////////////////////
		// Turn invisible
		// ///////////////////////////////////////////////////////////////////////////
		TimerHelper.schedule(time, () -> setVisible(false));
	}

	public void setActionText(final String text) {
		actionText = text;
	}

	public void setActionColor(final Color color) {
		action.setTextColor(color);
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return action.addClickHandler(handler);
	}

	public void setAtStart(final boolean atStart) {
		atStartMixin.toggle(atStart);
	}

	public boolean isAtStart() {
		return atStartMixin.isApplied();
	}

	public boolean isMultiline() {
		return multilineMixin.isApplied();
	}

	public void setMultiline(boolean multiline) {
		multilineMixin.toggle(multiline);
	}

	public boolean isActionOnBottom() {
		return actionOnButtonMixin.isApplied();
	}

	public void setActionOnBottom(boolean actionOnBottom) {
		actionOnButtonMixin.toggle(actionOnBottom);
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public static void showSnackBar(final String text) {
		showSnackBar(text, null, null);
	}

	public static void showSnackBar(final String text, final String actionText, final ClickHandler actionHandler) {
		// ///////////////////////////////////////////////////////////////////////////
		// Create an snackbar
		// ///////////////////////////////////////////////////////////////////////////
		final MaterialSnackbar snackbar = new MaterialSnackbar();
		snackbar.setText(text);
		snackbar.setActionText(actionText);
		snackbar.addClickHandler(actionHandler);
		// ///////////////////////////////////////////////////////////////////////////
		// Add into the root
		// ///////////////////////////////////////////////////////////////////////////
		RootPanel.get().add(snackbar);
		snackbar.show();
		// ///////////////////////////////////////////////////////////////////////////
		// Remove after timeout
		// ///////////////////////////////////////////////////////////////////////////
		final int time = snackbar.getTimeout() + (containsFixedFab() ? 0 : 250);
		TimerHelper.schedule(time, () -> snackbar.removeFromParent());
	}

	protected static native boolean containsFixedFab() /*-{
		var className = "." + @gwt.material.design.components.client.constants.CssName::MDC_FAB__FIXED;
		var elements = $wnd.jQuery("body").find(className);
		return elements.length > 0;
	}-*/;

	protected static native Element getFixedFab() /*-{
		var className = "." + @gwt.material.design.components.client.constants.CssName::MDC_FAB__FIXED;
		var elements = $wnd.jQuery("body").find(className);

		if (elements.length == 0)
			return null;

		return elements[0];
	}-*/;
}
