package gwt.material.design.components.client.base.mixin;

import com.google.gwt.user.client.ui.UIObject;

import gwt.material.design.components.client.base.HasCheked;

/**
 * @author Ben Dol
 */
public class CheckedMixin<T extends UIObject> extends AbstractMixin<T> implements HasCheked {

	public CheckedMixin(final T uiObject) {
		super(uiObject);
	}

	@Override
	public void setChecked(boolean checked) {
		if (checked) {
			uiObject.getElement().setAttribute("checked", "true");
		} else {
			uiObject.getElement().removeAttribute("checked");
		}
	}

	@Override
	public boolean isChecked() {
		return uiObject.getElement().getAttribute("checked") != null;
	}

}
