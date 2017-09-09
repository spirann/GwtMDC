package gwt.material.design.components.client.ui.html;

import gwt.material.design.components.client.base.HasName;
import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.InputTypeMixin;
import gwt.material.design.components.client.base.mixin.NameMixin;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.InputType;

public class Input extends MaterialWidget implements HasType<InputType>, HasName {

	private final InputTypeMixin<Input> inputTypeMixin = new InputTypeMixin<Input>(this);

	private final NameMixin<Input> nameMixin = new NameMixin<Input>(this);

	public Input() {
		super(HtmlElements.INPUT.createElement());
	}

	public Input(final InputType type) {
		this();
		setType(type);
	}

	public Input(final InputType type, final String primaryClass) {
		super(HtmlElements.INPUT.createElement(), primaryClass);
		setType(type);
	}
	
	public Input(final InputType type, final String primaryClass, final String ... initialClasses) {
		super(HtmlElements.INPUT.createElement(), primaryClass, initialClasses);
		setType(type);
	}

	public Input(final String primaryClass) {
		super(HtmlElements.INPUT.createElement(), primaryClass);
	}
	
	public Input(final String primaryClass, final String ... initialClasses) {
		super(HtmlElements.INPUT.createElement(), primaryClass, initialClasses);
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
	public void setName(String name) {
		nameMixin.setName(name);
	}

	@Override
	public String getName() {
		return nameMixin.getName();
	}

}
