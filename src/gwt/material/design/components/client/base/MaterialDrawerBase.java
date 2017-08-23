package gwt.material.design.components.client.base;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.ui.MaterialListGroup;

public class MaterialDrawerBase extends MaterialWidget implements HasOpen {

	protected JavaScriptObject drawer;
	protected MaterialListGroup content = new MaterialListGroup();

	private HandlerRegistration handler;

	private boolean initialized = false;

	protected MaterialDrawerBase(final String cssClass) {
		super(HtmlElements.ASIDE.createElement(), cssClass);
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		if (!initialized) {
			
			drawer = getElement();
			
			initialized = true;
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
	public void add(Widget child) {

		if (content.getParent() == null) {
			super.add(content);
		}

		content.add(child);

	}

	@Override
	public void insert(Widget child, int beforeIndex) {
		content.insert(child, beforeIndex);
	}

	@Override
	public Widget getWidget(int index) {
		return content.getWidget(index);
	}

	@Override
	public int getWidgetCount() {
		return content.getWidgetCount();
	}

	@Override
	public void setOpen(boolean open) {

		if (!isAttached() && handler == null) {

			handler = addAttachHandler(event -> {

				if (event.isAttached()) {
					setOpen(drawer, open);
				} else if (handler != null) {
					handler.removeHandler();
					handler = null;
				}

			});

		} else {
			setOpen(drawer, open);
		}
	}

	@Override
	public boolean isOpen() {
		return isOpen(drawer);
	}

	protected native boolean isOpen(final JavaScriptObject drawer)/*-{
		return drawer.style.display != "none";
	}-*/;

	protected native void setOpen(final JavaScriptObject drawer, boolean open)/*-{
		if (open) {
			drawer.style.display = "flex";
		} else {
			drawer.style.display = "none";
		}
	}-*/;

	@Override
	public void open() {
		setOpen(true);
	}

	@Override
	public void close() {
		setOpen(false);
	}

	public static class Spacer extends MaterialWidget {

		protected Spacer(final String cssClass) {
			super(HtmlElements.DIV.createElement(), cssClass);
		}

	}

}
