package gwt.material.design.components.client.base.mixin;

import com.google.gwt.user.client.ui.UIObject;

import gwt.material.design.components.client.base.HasHref;

public class HrefMixin<T extends UIObject> extends AbstractMixin<T> implements HasHref {

	private static final String HREF = "href";
	private static final String TARGET = "target";
	
	public HrefMixin(T uiObject) {
		super(uiObject);
	}

	@Override
	public void setHref(String href) {
		uiObject.getElement().setAttribute(HREF, href);
	}

	@Override
	public String getHref() {
		return uiObject.getElement().getAttribute(HREF);
	}

	@Override
	public void setTarget(String target) {
		uiObject.getElement().setAttribute(TARGET, target);
	}

	@Override
	public String getTarget() {
		return uiObject.getElement().getAttribute(TARGET);
	}

}
