package gwt.material.design.components.client.utils;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;

public class ResponsiveListener {

	public static interface Handler {
		
		public void onPhone();
		
		public void onTablet();
		
		public void onDesktop();
		
	}
	
	private ResponsiveListener() {
		
	}
	
	public static native void fire() /*-{
		
		$wnd.dispatchEvent(new Event('resize'));
		
	}-*/;
	
	public static HandlerRegistration register(final Handler handler) {
		
		
		
		return Window.addResizeHandler(event -> {
			
			final int width = event.getWidth();
			
			if(width < 480) {
				handler.onPhone();
			} else if(width >= 480 && width < 840) {
				handler.onTablet();
			} else {
				handler.onDesktop();
			}
			
		});
		
	}
	
}
