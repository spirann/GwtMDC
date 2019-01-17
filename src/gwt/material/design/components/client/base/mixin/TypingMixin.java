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
package gwt.material.design.components.client.base.mixin;

import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.mixin.base.AbstractMixin;
import gwt.material.design.components.client.base.widget.MaterialUIObject;
import gwt.material.design.components.client.events.TypingEvent;
import gwt.material.design.components.client.events.TypingEvent.HasTypingHandlers;
import gwt.material.design.components.client.events.TypingEvent.TypingHandler;
import gwt.material.design.components.client.utils.helper.TimerHelper;

/**
 * @author Richeli Vargas
 */
public class TypingMixin<UIO extends MaterialUIObject & HasKeyUpHandlers & HasTypingHandlers & HasText> extends AbstractMixin<UIO> implements HasTypingHandlers  {

	private int typingDelay = 350;
	protected long lastTyping = 0L;
	protected Timer typingTimer;
	
	public TypingMixin(final UIO uiObject) {
		super(uiObject);
		
		uiObject.addKeyUpHandler(event -> {
			
			if(typingTimer != null)
				typingTimer.cancel();
			
			typingTimer = TimerHelper.schedule(typingDelay, () -> fireTypingEvent());
			
		});
	}

	@Override
	public void setTypeingDelay(int typingDelay) {
		this.typingDelay = typingDelay;
	}

	@Override
	public void fireEvent(GwtEvent<?> event) {
		typingTimer = null;
		uiObject.fireEvent(event);
	}
	
	protected void fireTypingEvent() {
		TypingEvent.fire(uiObject, uiObject.getText());
	}

	@Override
	public HandlerRegistration addTypingHandler(TypingHandler handler) {
		return uiObject.addHandler(handler, TypingEvent.getType());
	}
}
