package gwt.material.design.components.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.HasName;

import gwt.material.design.components.client.base.HasCheked;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.InputType;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Input;

public class MaterialRadioButton extends MaterialWidget implements HasCheked, HasName {

	public static native void radioInit(Element element)/*-{
		$wnd.mdc.radio.MDCRadio.attachTo(element);
	}-*/;

	private Input input = new Input(InputType.RADIO, CssName.MDC_RADIO_NATIVE_CONTROL);
	private Div divBackground = new Div(CssName.MDC_RADIO_BACKGROUND);
	private Div divOuterCircle = new Div(CssName.MDC_RADIO_OUTER_CIRCLE);
	private Div divInnerCircle = new Div(CssName.MDC_RADIO_INNER_CIRCLE);

	public MaterialRadioButton() {
		super(HtmlElements.DIV.createElement(), CssName.MDC_RADIO);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		divBackground.add(divOuterCircle);
		divBackground.add(divInnerCircle);
		
		add(input);
		add(divBackground);
		
		radioInit(getElement());
	}
	
	@Override
	public void setId(String id) {
		input.setId(id);
	}

	@Override
	public void setChecked(boolean checked) {
		input.setChecked(checked);
	}

	@Override
	public boolean isChecked() {
		return input.isChecked();
	}

	@Override
	public void setName(String name) {
		input.setName(name);
	}

	@Override
	public String getName() {
		return input.getName();
	}

}
