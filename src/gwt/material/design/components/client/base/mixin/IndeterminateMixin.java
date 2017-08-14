package gwt.material.design.components.client.base.mixin;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.UIObject;

import gwt.material.design.components.client.base.HasIndeterminate;

/**
 * @author Richeli Vargas
 */
public class IndeterminateMixin<T extends UIObject> extends AbstractMixin<T> implements HasIndeterminate {

	public IndeterminateMixin(final T uiObject) {
		super(uiObject);
	}

	@Override
	public void setIndeterminate(boolean indeterminate) {
		setIndeterminate(uiObject.getElement(), indeterminate);
	}

	@Override
	public boolean isIndeterminate() {
		return isIndeterminate(uiObject.getElement());
	}

	protected native Boolean isIndeterminate(Element element)/*-{
		return element.indeterminate;
	}-*/;

	protected native void setIndeterminate(Element element, boolean value)/*-{
		element.indeterminate = value;
	}-*/;

}
