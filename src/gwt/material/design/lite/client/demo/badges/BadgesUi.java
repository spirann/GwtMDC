package gwt.material.design.lite.client.demo.badges;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class BadgesUi extends Composite {

	private static DemoUiUiBinder uiBinder = GWT.create(DemoUiUiBinder.class);

	interface DemoUiUiBinder extends UiBinder<Widget, BadgesUi> {
	}
	
	public BadgesUi() {
		initWidget(uiBinder.createAndBindUi(this));			
	}

}
