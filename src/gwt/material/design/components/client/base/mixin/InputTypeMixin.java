package gwt.material.design.components.client.base.mixin;

import com.google.gwt.user.client.ui.UIObject;

import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.constants.InputType;

/**
 * @author Ben Dol
 */
public class InputTypeMixin<T extends UIObject> extends AbstractMixin<T> implements HasType<InputType> {

	private InputType type;
	
    public InputTypeMixin(final T uiObject) {
        super(uiObject);
    }

	@Override
	public void setType(InputType type) {
		this.type = type;
		if (type != null) {
            uiObject.getElement().setAttribute("type", type.getCssName());
        } else {
            uiObject.getElement().removeAttribute("type");
        }		
	}

	@Override
	public InputType getType() {
		return type;
	}

}
