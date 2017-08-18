package gwt.material.design.components.client.base.mixin;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.HasAutoInitData;
import gwt.material.design.components.client.constants.AutoInitData;

/**
 * @author Richeli Vargas
 */
public class AutoInitDataMixin<T extends Widget & HasAutoInitData> extends AbstractMixin<T> implements HasAutoInitData {

	private AutoInitData autoInitData;

	private HandlerRegistration handler;

	public AutoInitDataMixin(final T uiObject) {
		super(uiObject);
	}

	@Override
	public void setUiObject(T uiObject) {
		super.setUiObject(uiObject);

		// Clean up previous handler
		if (handler != null) {
			handler.removeHandler();
			handler = null;
		}
	}

	@Override
	public void setAutoInitData(AutoInitData autoInitData) {
		this.autoInitData = autoInitData;

		uiObject.getElement().removeAttribute("data-mdc-auto-init");

		if (autoInitData != null) {

			uiObject.getElement().setAttribute("data-mdc-auto-init", autoInitData.getCssName());

			if (!uiObject.isAttached() && handler == null) {

				handler = uiObject.addAttachHandler(event -> {
					if (event.isAttached()) {
						init();
					} else if (handler != null) {
						handler.removeHandler();
						handler = null;
					}
				});

			} else {
				init();
			}
		}
	}

	private void init() {

		switch (autoInitData) {
		case MDC_RIPPLE:
			rippleInit(uiObject.getElement());
			break;
		case NONE:
		default:
			break;
		}

	}

	public static native void rippleInit(Element element)/*-{
		$wnd.mdc.ripple.MDCRipple.attachTo(element);
	}-*/;

	@Override
	public AutoInitData getAutoInitData() {
		return autoInitData;
	}

}
