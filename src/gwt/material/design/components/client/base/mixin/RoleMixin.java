package gwt.material.design.components.client.base.mixin;

import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.HasRole;
import gwt.material.design.components.client.constants.Role;

/**
 * @author Richeli Vargas
 */
public class RoleMixin<T extends Widget & HasRole> extends AbstractMixin<T> implements HasRole {

	private Role role;
	
	public RoleMixin(final T uiObject) {
		super(uiObject);
	}
	
	@Override
	public void setRole(Role role) {
		this.role = role;

		uiObject.getElement().removeAttribute("role");

		if (role != null) {

			uiObject.getElement().setAttribute("role", role.getCssName());

		}
	}

	@Override
	public Role getRole() {
		return role;
	}

}
