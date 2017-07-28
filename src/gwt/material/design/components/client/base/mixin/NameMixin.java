package gwt.material.design.components.client.base.mixin;

import com.google.gwt.user.client.ui.UIObject;

import gwt.material.design.components.client.base.HasName;

/**
 * @author Ben Dol
 */
public class NameMixin<T extends UIObject> extends AbstractMixin<T> implements HasName {

	public NameMixin(final T uiObject) {
		super(uiObject);
	}
	
	@Override
	public void setName(String name) {
		uiObject.getElement().setAttribute("name", name);		
	}

	@Override
	public String getName() {
		return uiObject.getElement().getAttribute("name");
	}

	
}
