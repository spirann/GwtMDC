package gwt.material.design.components.client.ui;


import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.Typography;
import gwt.material.design.components.client.ui.html.Label;

public class MaterialLabel extends Label {

	private boolean initialize = false;

	public MaterialLabel() {
		super();
		setInitialClasses(CssName.MDC_TYPOGRAPHY_ADJUST_MARGIN);
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		
		if (!initialize) {
			
			if(getTypography() == null){
				setTypography(Typography.BODY_2);
			}
			
			getElement().appendChild(HtmlElements.BR.createElement());
			initialize = true;
		}
	}
}
