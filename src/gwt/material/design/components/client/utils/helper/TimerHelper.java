/*
 * #%L
 * Gwt Material Design Components
 * %%
 * Copyright (C) 2017 - 2017 Gwt Material Design Components
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package gwt.material.design.components.client.utils.helper;

import com.google.gwt.user.client.Timer;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class TimerHelper {

	public static Timer schedule(final int schedule, final Runnable runnable) {		
		final Timer timer = new Timer() {
			@Override
			public void run() {
				runnable.run();
			}
		};
		timer.schedule(schedule);
		return timer;
	}
	
	public static Timer scheduleRepeating(final int schedule, final Runnable runnable) {
		final Timer timer = new Timer() {
			@Override
			public void run() {
				runnable.run();
			}
		};
		timer.scheduleRepeating(schedule);
		return timer;
	}
}
