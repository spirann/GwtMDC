package gwt.material.design.components.client.base;

import gwt.material.design.components.client.constants.CaptionIconPosition;
import gwt.material.design.components.client.constants.CaptionPosition;
import gwt.material.design.components.client.constants.CaptionType;

public interface HasCaptionContent {

	public void setCaptionType(CaptionType type);
	
	public CaptionType getCaptionType();
	
	public void setCaptionPosition(CaptionPosition position);
	
	public CaptionPosition getCaptionPosition();
	
	public void setCaptionIconPosition(CaptionIconPosition position);
	
	public CaptionIconPosition getCaptionIconPosition();
	
}
