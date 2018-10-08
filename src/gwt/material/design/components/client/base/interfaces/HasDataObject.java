package gwt.material.design.components.client.base.interfaces;

public interface HasDataObject {

	public <O> void setDataObject(O object);
	
	public <O> O getDataObject();
	
}
