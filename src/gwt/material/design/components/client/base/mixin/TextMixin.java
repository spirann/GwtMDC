package gwt.material.design.components.client.base.mixin;

import com.google.gwt.user.client.ui.UIObject;

/**
 * @author Ben Dol
 */
public class TextMixin<T extends UIObject> extends AbstractMixin<T> {

    public TextMixin(final T uiObject) {
        super(uiObject);
    }

    public String getText() {
        return uiObject.getElement().getInnerText();
    }

    public void setText(final String text) {
        uiObject.getElement().setInnerText(text);
    }
}
