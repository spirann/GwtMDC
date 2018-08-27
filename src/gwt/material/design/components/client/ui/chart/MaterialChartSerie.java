package gwt.material.design.components.client.ui.chart;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, name = "Object", namespace = JsPackage.GLOBAL)
public class MaterialChartSerie {

	@JsProperty
    public double value;

    @JsProperty
    public String name;

    @JsProperty
    public String className;
    
    @JsProperty
    public String meta;
	
}
