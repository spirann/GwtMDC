package gwt.material.design.components.client.ui.chart;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, name = "Object", namespace = JsPackage.GLOBAL)
public class MaterialChartSerie {

	@JsProperty
    protected double value;

    @JsProperty
    protected String name;

    @JsProperty
    protected String className;
    
    @JsProperty
    protected String meta;

}
