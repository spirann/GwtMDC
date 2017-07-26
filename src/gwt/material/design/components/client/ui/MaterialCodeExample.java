package gwt.material.design.components.client.ui;



import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;

public class MaterialCodeExample extends MaterialWidget  {

	public MaterialCodeExample() {
		super(HtmlElements.PRE.createElement(), CssName.PRISM_LANGUAGE_MARKUP, CssName.MDC_CODE_EXAMPLE);
	}

}
