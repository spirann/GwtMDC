package gwt.material.design.components.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.HasCheked;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.InputType;
import gwt.material.design.components.client.constants.Ripple;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Input;
import gwt.material.design.components.client.ui.html.Label;

public class MaterialRadioButton extends MaterialFormField implements HasCheked, HasName, HasText {

	public static native void radioInit(Element element)/*-{
		$wnd.mdc.radio.MDCRadio.attachTo(element);
	}-*/;

	// /////////////////////////////////////////////////////////////
	// Radio
	// /////////////////////////////////////////////////////////////
	private Div radio = new Div(CssName.MDC_RADIO);
	private Input input = new Input(InputType.RADIO, CssName.MDC_RADIO_NATIVE_CONTROL);
	private Div divBackground = new Div(CssName.MDC_RADIO_BACKGROUND);
	private Div divOuterCircle = new Div(CssName.MDC_RADIO_OUTER_CIRCLE);
	private Div divInnerCircle = new Div(CssName.MDC_RADIO_INNER_CIRCLE);
	
	// /////////////////////////////////////////////////////////////
	// Label
	// /////////////////////////////////////////////////////////////
	private Label label = new Label(); 
	
	@Override
	protected void onLoad() {
		super.onLoad();

		divBackground.add(divOuterCircle);
		divBackground.add(divInnerCircle);
		
		radio.add(input);
		radio.add(divBackground);
		
		radio.setRipple(Ripple.DEFAULT);
		radio.setCircle(true);
		
		add(radio);
		add(label);
	}
	
	@Override
	public void setId(String id) {
		input.setId(id);
		label.getElement().setAttribute("for", input.getId());
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

	@Override
	public String getText() {
		return label.getText();
	}

	@Override
	public void setText(String text) {
		label.setText(text);
	}

}
