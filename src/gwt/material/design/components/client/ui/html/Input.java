package gwt.material.design.components.client.ui.html;

import gwt.material.design.components.client.base.HasCheked;
import gwt.material.design.components.client.base.HasName;
import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.CheckedMixin;
import gwt.material.design.components.client.base.mixin.InputTypeMixin;
import gwt.material.design.components.client.base.mixin.NameMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.InputType;

public class Input extends MaterialWidget implements HasType<InputType>, HasCheked, HasName {

	private final InputTypeMixin<Input> inputTypeMixin = new InputTypeMixin<Input>(this);

	private final CheckedMixin<Input> checkedMixin = new CheckedMixin<Input>(this);

	private final NameMixin<Input> nameMixin = new NameMixin<Input>(this);

	public Input() {
		super(HtmlElements.INPUT.createElement(), CssName.MDC_TYPOGRAPHY);
	}

	public Input(final InputType type) {
		this();
		setType(type);
	}

	public Input(final InputType type, final String... cssClass) {
		super(HtmlElements.INPUT.createElement(), CssName.MDC_TYPOGRAPHY, cssClass);
		setType(type);
	}

	public Input(final String... cssClass) {
		super(HtmlElements.INPUT.createElement(), CssName.MDC_TYPOGRAPHY, cssClass);
	}

	@Override
	public void setType(InputType type) {
		inputTypeMixin.setType(type);
	}

	@Override
	public InputType getType() {
		return inputTypeMixin.getType();
	}

	@Override
	public void setChecked(boolean checked) {
		checkedMixin.setChecked(checked);
	}

	@Override
	public boolean isChecked() {
		return checkedMixin.isChecked();
	}

	@Override
	public void setName(String name) {
		nameMixin.setName(name);
	}

	@Override
	public String getName() {
		return nameMixin.getName();
	}

}
