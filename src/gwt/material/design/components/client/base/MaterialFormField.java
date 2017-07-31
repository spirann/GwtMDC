package gwt.material.design.components.client.base;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;

import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;

public abstract class MaterialFormField<T> extends MaterialWidget implements HasValue<T> {

	private boolean valueChangeHandlerInitialized;

	private T value;

	public MaterialFormField() {
		super(HtmlElements.DIV.createElement(), CssName.MDC_FORM_FIELD);
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();
	}

	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		return addDomHandler(handler, ChangeEvent.getType());
	}

	protected void fireChangeEvent() {
		ValueChangeEvent.fire(MaterialFormField.this, getValue());
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<T> handler) {
		// Initialization code
		if (!valueChangeHandlerInitialized) {
			valueChangeHandlerInitialized = true;
			addChangeHandler(new ChangeHandler() {
				public void onChange(ChangeEvent event) {
					fireChangeEvent();
				}
			});
		}
		return addHandler(handler, ValueChangeEvent.getType());
	}

	@Override
	public void setValue(T value) {
		setValue(value, true);
	}

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public void setValue(T value, boolean fireEvents) {
		this.value = value;
		if (fireEvents) {
			fireChangeEvent();
		}
	}

}
