package gwt.material.design.components.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.HasAcceptHandlers;
import gwt.material.design.components.client.base.HasCancelHandlers;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.AttributeMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.Role;
import gwt.material.design.components.client.events.AcceptEvent;
import gwt.material.design.components.client.events.CancelEvent;
import gwt.material.design.components.client.handlers.AcceptHandler;
import gwt.material.design.components.client.handlers.CancelHandler;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Footer;
import gwt.material.design.components.client.ui.html.H2;
import gwt.material.design.components.client.ui.html.Header;
import gwt.material.design.components.client.ui.html.Section;

public class MaterialDialog extends MaterialWidget implements HasAcceptHandlers<Boolean>, HasCancelHandlers<Boolean> {

	private boolean acceptHandlerInitialized;
	private boolean cancelHandlerInitialized;
	
	// /////////////////////////////////////////////////////////////
	// Initialize DIALOG
	// /////////////////////////////////////////////////////////////
	public static native void dialogInit(Element element)/*-{
		$wnd.mdc.dialog.MDCDialog.attachTo(element);
	}-*/;

	protected final AttributeMixin<MaterialDialog> ariaHiddenMixin = new AttributeMixin<>(this, "aria-hidden", "true");
	protected final AttributeMixin<MaterialDialog> ariaLabelledbyMixin = new AttributeMixin<>(this, "aria-labelledby");
	protected final AttributeMixin<MaterialDialog> ariaDescribedbyMixin = new AttributeMixin<>(this, "aria-describedby");

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

	private boolean initialized = false;

	public MaterialDialog() {
		super(HtmlElements.ASIDE.createElement(), CssName.MDC_DIALOG);
		setRole(Role.ALERT_DIALOG);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {

			header.add(headerTitle);
			
			surface.add(header);
			surface.add(body);
			surface.add(footer);
			super.add(surface);

			//addAcceptEvent(getElement());
			//addCancelEvent(getElement());			
			dialogInit(getElement());

		}
	}

	@Override
	public void add(Widget child) {
		body.add(child);
	}
	
	@Override
	public Widget getWidget(int index) {
		return body.getWidget(index);
	}
	
	@Override
	public int getWidgetCount() {
		return body.getWidgetCount();
	}
	
	@Override
	public void setTitle(String title) {
		super.setTitle(title);
		headerTitle.getElement().setInnerText(title);
	}
	
	public void show() {
		show(getElement());
	}

	public void close() {
		close(getElement());
	}
	
	protected native void show(Element element)/*-{
		var dialog = new $wnd.mdc.dialog.MDCDialog(element);
		dialog.show();
	}-*/;

	protected native void close(Element element)/*-{
		var dialog = new $wnd.mdc.dialog.MDCDialog(element);
		dialog.close();
	}-*/;
	
	protected native void addAcceptEvent(Element element)/*-{
		var _this = this;
		element.addEventListener('MDCDialog:accept', fireEvent);
		function fireEvent() {
			_this.@gwt.material.design.components.client.ui.MaterialDialog::fireAcceptEvent()();
		}
	}-*/;
	
	protected native void addCancelEvent(Element element)/*-{
		var _this = this;
		element.addEventListener('MDCDialog:cancel', fireEvent);
		function fireEvent() {
			_this.@gwt.material.design.components.client.ui.MaterialDialog::fireCancelEvent()();
		}
	}-*/;

	protected void fireAcceptEvent() {
		AcceptEvent.fire(MaterialDialog.this, Boolean.TRUE);
	}
	
	@Override
	public HandlerRegistration addAcceptHandler(AcceptHandler<Boolean> handler) {

		if (!acceptHandlerInitialized) {
			acceptHandlerInitialized = true;
			addHandler(new AcceptHandler<Boolean>() {
				public void onAccept(AcceptEvent<Boolean> event) {
					AcceptEvent.fire(MaterialDialog.this, Boolean.TRUE);
				}
			}, AcceptEvent.getType());
		}

		return addHandler(handler, AcceptEvent.getType());

	}
	
	protected void fireCancelEvent() {
		CancelEvent.fire(MaterialDialog.this, Boolean.TRUE);
	}

	@Override
	public HandlerRegistration addCancelHandler(CancelHandler<Boolean> handler) {

		if (!acceptHandlerInitialized) {
			acceptHandlerInitialized = true;
			addHandler(new CancelHandler<Boolean>() {
				public void onCancel(CancelEvent<Boolean> event) {
					CancelEvent.fire(MaterialDialog.this, Boolean.TRUE);
				}
			}, CancelEvent.getType());
		}

		return addHandler(handler, CancelEvent.getType());

	}
}
