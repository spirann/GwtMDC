package gwt.material.design.components.client.ui.misc.tree.js;

import com.google.gwt.core.client.JavaScriptObject;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, name = "Object", namespace = JsPackage.GLOBAL)
public class JsItem {

	@JsProperty
	public String id;

	@JsProperty
	public String name;

	@JsProperty
	public String parent;

	@JsProperty
	public boolean selected;
	
	@JsProperty
	public Object data;
	
	@JsProperty
	public JavaScriptObject onClick;

	@JsProperty
	public JavaScriptObject onSelect;

	@JsProperty
	public JavaScriptObject onUnselect;

}
