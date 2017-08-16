package gwt.material.design.components.client.ui;



import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;

public class MaterialList extends MaterialWidget{

	public MaterialList(){
		super(HtmlElements.UL.createElement(), CssName.MDC_LIST);
	}
	
}
