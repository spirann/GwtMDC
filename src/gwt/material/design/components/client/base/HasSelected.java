package gwt.material.design.components.client.base;

public interface HasSelected {

	public void setSelected(final boolean selected);
	
	public void setSelected(boolean selected, boolean fireEvents);
	
	public boolean isSelected();
	
}
