package gwt.material.design.components.client.ui;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.MaterialFormField;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.InputType;
import gwt.material.design.components.client.constants.Ripple;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Input;
import gwt.material.design.components.client.ui.html.Label;

public class MaterialRadioButton extends MaterialFormField<Boolean> implements HasName, HasText {

	// ////////////////////////////////////////////////////////////////
	// Control for change value in RadioButton group
	// Because the event is not trigger when another radio is selected
	// ////////////////////////////////////////////////////////////////
	private final static Map<String, MaterialRadioButton> history = new HashMap<>();

	protected void putInHistory() {
		putInHistory(true);
	}
	
	protected void putInHistory(final boolean fireEvent) {

		final MaterialRadioButton old = history.get(getName());

		if (fireEvent && old != null && old != this) {
			old.fireChangeEvent();
		}

		history.put(getName(), this);

	}

	protected void removeFromHistory() {

		final MaterialRadioButton old = history.get(getName());

		if (old == this) {
			history.remove(getName());
		}

	}

	// /////////////////////////////////////////////////////////////
	// Initialize RadioButton, but it is not necessary
	// /////////////////////////////////////////////////////////////
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

		radio.setDisabledClass(CssName.MDC_RADIO_DISABLED);
		radio.add(input);
		radio.add(divBackground);

		setRipple(Ripple.DEFAULT);
		setCircle(true);

		label.setTextColor(Color.MDC_THEME_TEXT_PRIMARY_ON_BACKGROUND);

		add(radio);
		add(label);

		if (getValue()) {
			putInHistory();
		}

		addValueChangeListener(input.getElement());

	}

	protected native void addValueChangeListener(Element element)/*-{
		var _this = this;
		element.addEventListener('change',
			function(event) {
				if (element.checked) {
					_this.@gwt.material.design.components.client.ui.MaterialRadioButton::putInHistory()();
				}
			});
	}-*/;

	@Override
	public void setId(String id) {
		input.setId(id);
		label.getElement().setAttribute("for", input.getId());
	}

	@Override
	public String getId() {
		return input.getId();
	}

	@Override
	public void setValue(Boolean value, boolean fireEvents) {
		setValue(input.getElement(), value);

		putInHistory(fireEvents);

		if (fireEvents) {
			fireChangeEvent();
		}
	}

	@Override
	public Boolean getValue() {
		return getValue(input.getElement());
	}

	private native Boolean getValue(Element element)/*-{
		return element.checked;
	}-*/;

	private native void setValue(Element element, boolean value)/*-{
		element.checked = value;
	}-*/;

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
	
	@Override
	public void setRipple(Ripple ripple) {
		radio.setRipple(ripple);
	}
	
	@Override
	public Ripple getRipple() {
		return radio.getRipple();
	}
	
	@Override
	public void setCircle(boolean circle) {
		radio.setCircle(circle);
	}
	
	@Override
	public void setTextColor(Color color) {
		label.setTextColor(color);
	}
}
