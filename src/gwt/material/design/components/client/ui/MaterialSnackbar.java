package gwt.material.design.components.client.ui;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.HasOpen;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.TextMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.ui.html.Button;
import gwt.material.design.components.client.ui.html.Div;

public class MaterialSnackbar extends MaterialWidget implements HasText, HasOpen {

	protected JavaScriptObject javaScriptComponent;

	protected static native JavaScriptObject jsInit(final Element element)/*-{
		return $wnd.mdc.snackbar.MDCSnackbar.attachTo(element);
	}-*/;

	protected Div text = new Div(CssName.MDC_SNACKBAR_TEXT);
	protected Div actionWrapper = new Div(CssName.MDC_SNACKBAR_ACTION_WRAPPER);
	protected Button action = new Button(CssName.MDC_BUTTON, CssName.MDC_SNACKBAR_ACTION_BUTTON);

	protected final TextMixin<Div> textMixin = new TextMixin<>(text);

	private String actionText;
	
	private boolean initialized = false;

	public MaterialSnackbar() {
		super(HtmlElements.DIV.createElement(), CssName.MDC_SNACKBAR);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {

			actionWrapper.add(action);

			add(text);
			add(actionWrapper);

			javaScriptComponent = jsInit(getElement());

			initialized = true;
		}

	}

	protected native void open(final JavaScriptObject snackbar,
			final String text, final String actionText)/*-{

		var _this = this;
		
		var _action = null;
		
		if(actionText) {
			action = function() {
				_this.@gwt.material.design.components.client.ui.MaterialSnackbar::fireClickEvent()();
			};
		}
	
		var dataObj = {
			message : text,
			actionText : actionText,
			actionHandler : _action
		};

		snackbar.show(dataObj);

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

	@Override
	public void setOpen(boolean open) {
	}

	@Override
	public boolean isOpen() {
		return false;
	}

	@Override
	public void open() {
		open(javaScriptComponent, getText(), actionText);
	}

	@Override
	public void close() {
	}

	public void setActionText(final String text){
		actionText = text;
	}
	
	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return action.addClickHandler(handler);
	}
}
