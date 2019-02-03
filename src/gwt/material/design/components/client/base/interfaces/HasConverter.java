package gwt.material.design.components.client.base.interfaces;

import gwt.material.design.components.client.base.widget.MaterialUIObject;

public interface HasConverter<S extends MaterialUIObject, V, K> {

	public Converter<S, V, K> getConverter();
	
	public void setConverter(final Converter<S, V, K> converter);
	
	
}
