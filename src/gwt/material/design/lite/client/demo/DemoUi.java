package gwt.material.design.lite.client.demo;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DemoUi extends Composite {

	private static DemoUiUiBinder uiBinder = GWT.create(DemoUiUiBinder.class);

	interface DemoUiUiBinder extends UiBinder<Widget, DemoUi> {
	}
	
	public DemoUi() {
		initWidget(uiBinder.createAndBindUi(this));			
	}

}
