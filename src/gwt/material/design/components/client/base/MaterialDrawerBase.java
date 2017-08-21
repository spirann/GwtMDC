package gwt.material.design.components.client.base;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.ui.MaterialListGroup;

public class MaterialDrawerBase extends MaterialWidget implements HasOpen {

	protected MaterialListGroup content = new MaterialListGroup();

	private HandlerRegistration handler;
	
	protected MaterialDrawerBase(final String cssClass) {
		super(HtmlElements.ASIDE.createElement(), cssClass);
	}

	@Override
	protected void onUnload() {
		super.onUnload();
		
		if(handler != null) {
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
					setOpen(getElement(), open);
				} else if (handler != null) {
					handler.removeHandler();
					handler = null;
				}

			});

		} else {
			setOpen(getElement(), open);
		}
	}

	@Override
	public boolean isOpen() {
		return isOpen(getElement());
	}

	protected native boolean isOpen(Element element)/*-{
		return element.style.display != "none";
	}-*/;

	protected native void setOpen(Element element, boolean open)/*-{
		if(open){
			element.style.display = "flex";	
		} else {
			element.style.display = "none";
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
