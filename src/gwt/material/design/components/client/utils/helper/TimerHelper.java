package gwt.material.design.components.client.utils.helper;

import com.google.gwt.user.client.Timer;

public class TimerHelper {

	public static void schedule(final int schedule, final Runnable runnable) {
		
		final Timer timer = new Timer() {
			@Override
			public void run() {
				runnable.run();
			}
		};
		timer.schedule(schedule);
		
	}
	
	
}