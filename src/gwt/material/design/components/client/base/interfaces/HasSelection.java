package gwt.material.design.components.client.base.interfaces;

public interface HasSelection<T> {

	public void setSelection(final T selected);
	
	public void setSelection(final T selected, final boolean fireEvents);
	
	public T getSelection();
	
}
