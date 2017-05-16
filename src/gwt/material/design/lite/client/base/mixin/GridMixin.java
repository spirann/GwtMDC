/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
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
package gwt.material.design.lite.client.base.mixin;

import com.google.gwt.user.client.ui.UIObject;

import gwt.material.design.lite.client.base.HasGrid;

/**
 * @author Ben Dol
 */
public class GridMixin<T extends UIObject & HasGrid> extends AbstractMixin<T> implements HasGrid {

	private final static String cssCell = "mdl-cell--";

	private final static String cssCol = "-col";
	private final static String cssColDesktop = "-col-desktop";
	private final static String cssColTablet = "-col-tablet";
	private final static String cssColPhone = "-col-phone";

	private final static String cssOffset = "-offset";
	private final static String cssOffsetDesktop = "-offset-desktop";
	private final static String cssOffsetTablet = "-offset-tablet";
	private final static String cssOffsetPhone = "-offset-phone";

	private int cols = 12;
	private int colsDesktop = 12;
	private int colsTablet = 12;
	private int colsPhone = 12;

	private int offset = 0;
	private int offsetDesktop = 0;
	private int offsetTablet = 0;
	private int offsetPhone = 0;

	public GridMixin(final T widget) {
		super(widget);
	}

	@Override
	public void setCols(int cols) {
		uiObject.removeStyleName(cssCell + this.cols + cssCol);
		this.cols = cols;
		uiObject.addStyleName(cssCell + cols + cssCol);
	}

	@Override
	public void setColsDesktop(int colsDesktop) {
		uiObject.removeStyleName(cssCell + this.colsDesktop + cssColDesktop);
		this.colsDesktop = colsDesktop;
		uiObject.addStyleName(cssCell + colsDesktop + cssColDesktop);
	}

	@Override
	public void setColsTablet(int colsTablet) {
		uiObject.removeStyleName(cssCell + this.colsTablet + cssColTablet);
		this.colsTablet = colsTablet;
		uiObject.addStyleName(cssCell + colsTablet + cssColTablet);
	}

	@Override
	public void setColsPhone(int colsPhone) {
		uiObject.removeStyleName(cssCell + this.colsPhone + cssColPhone);
		this.colsPhone = colsPhone;
		uiObject.addStyleName(cssCell + colsPhone + cssColPhone);
	}

	@Override
	public void setOffset(int offset) {
		uiObject.removeStyleName(cssCell + this.offset + cssOffset);
		this.offset = offset;
		uiObject.addStyleName(cssCell + offset + cssOffset);
	}

	@Override
	public void setOffsetDesktop(int offsetDesktop) {
		uiObject.removeStyleName(cssCell + this.offsetDesktop + cssOffsetDesktop);
		this.offsetDesktop = offsetDesktop;
		uiObject.addStyleName(cssCell + offsetDesktop + cssOffsetDesktop);
	}

	@Override
	public void setOffsetTablet(int offsetTablet) {
		uiObject.removeStyleName(cssCell + this.offsetTablet + cssOffsetTablet);
		this.offsetTablet = offsetTablet;
		uiObject.addStyleName(cssCell + offsetTablet + cssOffsetTablet);
	}

	@Override
	public void setOffsetPhone(int offsetPhone) {
		uiObject.removeStyleName(cssCell + this.offsetPhone + cssOffsetPhone);
		this.offsetPhone = offsetPhone;
		uiObject.addStyleName(cssCell + offsetPhone + cssOffsetPhone);

	}
}
