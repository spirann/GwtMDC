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
package gwt.material.design.components.client.base;

/**
 * 
 * @author Richeli Vargas
 *
 */
public interface HasToolbarFixedAdjust {

	/**
	 * Adjusting sibling elements of fixed toolbars
	 * 
	 * When using mdc-toolbar--fixed, you need to set the margin of the content
	 * to prevent toolbar overlaying your content. You can add the
	 * mdc-toolbar-fixed-adjust helper class to the toolbar’s adjacent sibling
	 * element, which will add default margin-top.
	 * 
	 * When you are using mdc-toolbar with JavaScript, you should assgin your
	 * content wrapper element to mdc-toolbar’s instance property
	 * fixedAdjustElement. This will make mdc-toolbar aware of the wrapper class
	 * and adjust the margin-top correspondingly.
	 * 
	 * @param toolbarFixedAdjust
	 */
	public void setToolbarFixedAdjust(boolean toolbarFixedAdjust);

	public boolean isToolbarFixedAdjust();

}
