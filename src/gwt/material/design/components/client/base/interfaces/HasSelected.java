package gwt.material.design.components.client.base.interfaces;

public interface HasSelected {

	public void setSelected(final boolean selected);
	
	public void setSelected(boolean selected, boolean fireEvents);
	
	public boolean isSelected();
	
}
