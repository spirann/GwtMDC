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
package gwt.material.design.components.client.ui;

import gwt.material.design.components.client.base.interfaces.HasColumns;
import gwt.material.design.components.client.base.interfaces.HasGap;
import gwt.material.design.components.client.base.mixin.ApplyStyleMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.ui.html.Ul;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialImageList extends Ul implements HasColumns, HasGap {

	private final ApplyStyleMixin<MaterialImageList> masontyMixin = new ApplyStyleMixin<>(this,
			CssName.MDC_IMAGE_LIST__MASONRY);
	private final ApplyStyleMixin<MaterialImageList> withTextProtectionMixin = new ApplyStyleMixin<>(this,
			CssName.MDC_IMAGE_LIST__WITH_TEXT_PROTECTION);

	public MaterialImageList() {
		super(CssName.MDC_IMAGE_LIST);
	}

	public boolean isMasonry() {
		return masontyMixin.isApplied();
	}

	public void setMasonry(boolean masonry) {
		masontyMixin.setApply(masonry);
	}

	public boolean isWithTextProtection() {
		return withTextProtectionMixin.isApplied();
	}

	public void setWithTextProtection(boolean withTextProtection) {
		withTextProtectionMixin.setApply(withTextProtection);
	}

	@Override
	public void setCols(int columns) {
		setDesktopCols(columns);
		setTabletCols(columns);
		setPhoneCols(columns);
	}
	
	@Override
	public void setDesktopCols(int columns) {
		setStyleProperty(CssMixin.MDC_IMAGE_LIST_COLUMN_COUNT_DESKTOP, String.valueOf(columns));
	}

	@Override
	public void setTabletCols(int columns) {
		setStyleProperty(CssMixin.MDC_IMAGE_LIST_COLUMN_COUNT_TABLET, String.valueOf(columns));
	}

	@Override
	public void setPhoneCols(int columns) {
		setStyleProperty(CssMixin.MDC_IMAGE_LIST_COLUMN_COUNT_PHONE, String.valueOf(columns));
	}

	@Override
	public void setGap(int gap) {
		setDesktopGap(gap);
		setTabletGap(gap);
		setPhoneGap(gap);
	}

	@Override
	public void setDesktopGap(int gap) {
		setStyleProperty(CssMixin.MDC_IMAGE_LIST_COLUMN_GAP_DESKTOP, gap + "px");
	}

	@Override
	public void setTabletGap(int gap) {
		setStyleProperty(CssMixin.MDC_IMAGE_LIST_COLUMN_GAP_TABLET, gap + "px");
	}

	@Override
	public void setPhoneGap(int gap) {
		setStyleProperty(CssMixin.MDC_IMAGE_LIST_COLUMN_GAP_PHONE, gap + "px");
	}
}
