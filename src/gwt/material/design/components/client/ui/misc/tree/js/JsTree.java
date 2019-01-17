package gwt.material.design.components.client.ui.misc.tree.js;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, name = "Object", namespace = JsPackage.GLOBAL)
public class JsTree	 {

	@JsMethod
	public native void setData(final JsItem[] data);
	
	@JsMethod
	public native void select(final JsItem[] items);
	
	@JsMethod
	public native void unselect(final JsItem[] items);
	
	@JsMethod
	public native JsItem[] getSelectedItems();
	
	@JsMethod
	public native int countSelectedItems();
		
	@JsMethod
	public native void filter(final String filter);
	
	@JsMethod
	public native JsItem[] getFilteredItems();
	
	@JsMethod
	public native int countFilteredItems();
	
	@JsMethod
	public native JsItem[] getChildren(final String parentId);
	
	@JsMethod
	public native JsItem[] getAllChildren(final String parentId);
	
	@JsMethod
	public native JsItem[] getAllParents(final String parentId);
}
