package gwt.material.design.components.client.base.mixin;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.UIObject;

import gwt.material.design.components.client.base.HasChecked;

/**
 * @author Richeli Vargas
 */
public class CheckedMixin<T extends UIObject> extends AbstractMixin<T> implements HasChecked {

	public CheckedMixin(final T uiObject) {
		super(uiObject);
	}

	@Override
	public void setChecked(boolean checked) {
		setChecked(uiObject.getElement(), checked);
	}

	@Override
	public boolean isChecked() {
		return isChecked(uiObject.getElement());
	}

	protected native Boolean isChecked(Element element)/*-{
		return element.checked;
	}-*/;

	protected native void setChecked(Element element, boolean value)/*-{
		element.checked = value;
	}-*/;
	
}
