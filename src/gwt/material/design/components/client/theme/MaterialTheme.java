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
package gwt.material.design.components.client.theme;

import java.io.Serializable;

import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.ThemeAttribute;
import gwt.material.design.components.client.utils.helper.StyleHelper;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialTheme implements Serializable {

	private static final long serialVersionUID = 4421038495355108683L;

	private String primary;
	private String secondary;
	private String background;
	private String surface;
	//
	private String themeOnPrimary;
	private String themeOnSecondary;
	private String themeOnSurface;
	//
	private String textPrimaryOnBackground;
	private String textSecondaryOnBackground;
	private String textHintOnBackground;
	private String textDisabledOnBackground;
	private String textIconOnBackground;
	//
	private String codeBackground;
	private String codeString;
	private String codeTokenComment;
	private String codeTokenProlog;
	private String codeTokenDoctype;
	private String codeTokenCdata;
	private String codeTokenPunctuation;
	private String codeTokenProperty;
	private String codeTokenTag;
	private String codeTokenConstant;
	private String codeTokenSymbol;
	private String codeTokenDeleted;
	private String codeTokenBoolean;
	private String codeTokenNumber;
	private String codeTokenSelector;
	private String codeTokenAttrName;
	private String codeTokenString;
	private String codeTokenChar;
	private String codeTokenBuiltin;
	private String codeTokenInserted;
	private String codeTokenOperator;
	private String codeTokenEntity;
	private String codeTokenUrl;
	private String codeTokenVariable;
	private String codeTokenAtrule;
	private String codeTokenAttrValue;
	private String codeTokenFunction;
	private String codeTokenKeyword;
	private String codeTokenRegex;
	private String codeTokenImportant;

	public String getAsText() {
		return getAsText("\n", false);
	}

	public String getAsText(final boolean withCodeStyle) {
		return getAsText("\n", withCodeStyle);
	}
	
	private final String identation = "\t";
	
	public String getAsText(final String separator, final boolean withCodeStyle) {

		final StringBuilder text = new StringBuilder();
		text.append(":root {").append(separator);
		//
		text.append(identation).append(loadProperty(ThemeAttribute.MDC_THEME_PRIMARY, primary)).append(separator);
		text.append(identation).append(loadProperty(ThemeAttribute.MDC_THEME_SECONDARY, secondary)).append(separator);
		text.append(identation).append(loadProperty(ThemeAttribute.MDC_THEME_BACKGROUND, background)).append(separator);
		text.append(identation).append(loadProperty(ThemeAttribute.MDC_THEME_SURFACE, surface)).append(separator);
		//
		text.append(identation).append(loadProperty(ThemeAttribute.MDC_THEME_ON_PRIMARY, themeOnPrimary))
				.append(separator);
		text.append(identation).append(loadProperty(ThemeAttribute.MDC_THEME_ON_SECONDARY, themeOnSecondary))
				.append(separator);
		text.append(identation).append(loadProperty(ThemeAttribute.MDC_THEME_ON_SURFACE, themeOnSurface)).append(separator);
		//
		text.append(identation).append(loadProperty(ThemeAttribute.MDC_THEME_TEXT_PRIMARY_ON_BACKGROUND, textPrimaryOnBackground))
				.append(separator);
		text.append(identation).append(loadProperty(ThemeAttribute.MDC_THEME_TEXT_SECONDARY_ON_BACKGROUND, textSecondaryOnBackground))
				.append(separator);
		text.append(identation).append(loadProperty(ThemeAttribute.MDC_THEME_TEXT_HINT_ON_BACKGROUND, textHintOnBackground))
				.append(separator);
		text.append(identation).append(loadProperty(ThemeAttribute.MDC_THEME_TEXT_DISABLED_ON_BACKGROUND, textDisabledOnBackground))
				.append(separator);
		text.append(identation).append(loadProperty(ThemeAttribute.MDC_THEME_TEXT_ICON_ON_BACKGROUND, textIconOnBackground))
				.append(separator);
		//
		if (withCodeStyle) {
			text.append(identation).append(loadProperty("--mdc-theme-code-background", codeBackground)).append(separator);
			text.append(identation).append(loadProperty("--mdc-theme-code-String", codeString)).append(separator);
			text.append(identation).append(loadProperty("--mdc-theme-code-token-comment", codeTokenComment)).append(separator);
			text.append(identation).append(loadProperty("--mdc-theme-code-token-prolog", codeTokenProlog)).append(separator);
			text.append(identation).append(loadProperty("--mdc-theme-code-token-doctype", codeTokenDoctype)).append(separator);
			text.append(identation).append(loadProperty("--mdc-theme-code-token-cdata", codeTokenCdata)).append(separator);
			text.append(identation).append(loadProperty("--mdc-theme-code-token-punctuation", codeTokenPunctuation)).append(separator);
			text.append(identation).append(loadProperty("--mdc-theme-code-token-property", codeTokenProperty)).append(separator);
			text.append(identation).append(loadProperty("--mdc-theme-code-token-tag", codeTokenTag)).append(separator);
			text.append(identation).append(loadProperty("--mdc-theme-code-token-constant", codeTokenConstant)).append(separator);
			text.append(identation).append(loadProperty("--mdc-theme-code-token-symbol", codeTokenSymbol)).append(separator);
			text.append(identation).append(loadProperty("--mdc-theme-code-token-deleted", codeTokenDeleted)).append(separator);
			text.append(identation).append(loadProperty("--mdc-theme-code-token-boolean", codeTokenBoolean)).append(separator);
			text.append(identation).append(loadProperty("--mdc-theme-code-token-number", codeTokenNumber)).append(separator);
			text.append(identation).append(loadProperty("--mdc-theme-code-token-selector", codeTokenSelector)).append(separator);
			text.append(identation).append(loadProperty("--mdc-theme-code-token-attr-name", codeTokenAttrName)).append(separator);
			text.append(identation).append(loadProperty("--mdc-theme-code-token-String", codeTokenString)).append(separator);
			text.append(identation).append(loadProperty("--mdc-theme-code-token-char", codeTokenChar)).append(separator);
			text.append(identation).append(loadProperty("--mdc-theme-code-token-builtin", codeTokenBuiltin)).append(separator);
			text.append(identation).append(loadProperty("--mdc-theme-code-token-inserted", codeTokenInserted)).append(separator);
			text.append(identation).append(loadProperty("--mdc-theme-code-token-operator", codeTokenOperator)).append(separator);
			text.append(identation).append(loadProperty("--mdc-theme-code-token-entity", codeTokenEntity)).append(separator);
			text.append(identation).append(loadProperty("--mdc-theme-code-token-url", codeTokenUrl)).append(separator);
			text.append(identation).append(loadProperty("--mdc-theme-code-token-variable", codeTokenVariable)).append(separator);
			text.append(identation).append(loadProperty("--mdc-theme-code-token-atrule", codeTokenAtrule)).append(separator);
			text.append(identation).append(loadProperty("--mdc-theme-code-token-attr-value", codeTokenAttrValue)).append(separator);
			text.append(identation).append(loadProperty("--mdc-theme-code-token-function", codeTokenFunction)).append(separator);
			text.append(identation).append(loadProperty("--mdc-theme-code-token-keyword", codeTokenKeyword)).append(separator);
			text.append(identation).append(loadProperty("--mdc-theme-code-token-regex", codeTokenRegex)).append(separator);
			text.append(identation).append(loadProperty("--mdc-theme-code-token-important", codeTokenImportant)).append(separator);
		}
		//
		text.append("}");

		return text.toString();

	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////////////////////////
	// AUTOMATE
	// ////////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////////////////////////

	private final double mainAlpha = 1.0;
	private final double textPrimaryAlpha = 1.0;
	private final double textSecondaryAlpha = 0.7;
	private final double textHintAlpha = 0.6;
	private final double textDisabledAlpha = 0.5;
	private final double textIconAlpha = 0.6;

	public void setPrimaryColor(final Color color) {
		setPrimary(color.getCssName(mainAlpha));
	}

	public void setPrimaryTextColor(final Color color) {
		setThemeOnPrimary(color.getCssName(textPrimaryAlpha));
	}

	public void setSecondaryColor(final Color color) {
		setSecondary(color.getCssName(mainAlpha));
	}

	public void setSecondaryTextColor(final Color color) {
		setThemeOnSecondary(color.getCssName(textPrimaryAlpha));
	}

	public void setBackgroundColor(final Color color) {
		setBackground(color.getCssName(mainAlpha));
	}

	public void setBackgroundTextColor(final Color color) {
		setTextPrimaryOnBackground(color.getCssName(textPrimaryAlpha));
		setTextSecondaryOnBackground(color.getCssName(textSecondaryAlpha));
		setTextHintOnBackground(color.getCssName(textHintAlpha));
		setTextDisabledOnBackground(color.getCssName(textDisabledAlpha));
		setTextIconOnBackground(color.getCssName(textIconAlpha));
	}

	public void setSurfaceColor(final Color color) {
		setSurface(color.getCssName(mainAlpha));
	}

	public void setSurfaceTextColor(final Color color) {
		setThemeOnSurface(color.getCssName(textPrimaryAlpha));
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////////////////////////
	// CREATE CSS FILE
	// ////////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////////////////////////

	private String loadProperty(final String property, String value) {

		if (property == null) {
			return "";
		}

		final String color;

		if (value == null) {
			color = StyleHelper.getComputedProperty(property);
		} else {
			color = value;
		}

		return property + ": " + color + ";";
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////////////////////////
	// ACCESSIBLE METHODS
	// ////////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////////////////////////

	public String getPrimary() {
		return primary;
	}

	public void setPrimary(String primary) {
		this.primary = primary;
	}

	public String getSecondary() {
		return secondary;
	}

	public void setSecondary(String secondary) {
		this.secondary = secondary;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String getThemeOnPrimary() {
		return themeOnPrimary;
	}

	public void setThemeOnPrimary(String themeOnPrimary) {
		this.themeOnPrimary = themeOnPrimary;
	}

	public String getThemeOnSecondary() {
		return themeOnSecondary;
	}

	public void setThemeOnSecondary(String themeOnSecondary) {
		this.themeOnSecondary = themeOnSecondary;
	}

	public String getTextPrimaryOnBackground() {
		return textPrimaryOnBackground;
	}

	public void setTextPrimaryOnBackground(String textPrimaryOnBackground) {
		this.textPrimaryOnBackground = textPrimaryOnBackground;
	}

	public String getTextSecondaryOnBackground() {
		return textSecondaryOnBackground;
	}

	public void setTextSecondaryOnBackground(String textSecondaryOnBackground) {
		this.textSecondaryOnBackground = textSecondaryOnBackground;
	}

	public String getTextHintOnBackground() {
		return textHintOnBackground;
	}

	public void setTextHintOnBackground(String textHintOnBackground) {
		this.textHintOnBackground = textHintOnBackground;
	}

	public String getTextDisabledOnBackground() {
		return textDisabledOnBackground;
	}

	public void setTextDisabledOnBackground(String textDisabledOnBackground) {
		this.textDisabledOnBackground = textDisabledOnBackground;
	}

	public String getTextIconOnBackground() {
		return textIconOnBackground;
	}

	public void setTextIconOnBackground(String textIconOnBackground) {
		this.textIconOnBackground = textIconOnBackground;
	}

	public String getSurface() {
		return surface;
	}

	public void setSurface(String surface) {
		this.surface = surface;
	}

	public String getThemeOnSurface() {
		return themeOnSurface;
	}

	public void setThemeOnSurface(String themeOnSurface) {
		this.themeOnSurface = themeOnSurface;
	}	

	/*
	 * 
	 * 
	 */

	public String getCodeBackground() {
		return codeBackground;
	}

	public void setCodeBackground(String codeBackground) {
		this.codeBackground = codeBackground;
	}

	public String getCodeString() {
		return codeString;
	}

	public void setCodeString(String codeString) {
		this.codeString = codeString;
	}

	public String getCodeTokenComment() {
		return codeTokenComment;
	}

	public void setCodeTokenComment(String codeTokenComment) {
		this.codeTokenComment = codeTokenComment;
	}

	public String getCodeTokenProlog() {
		return codeTokenProlog;
	}

	public void setCodeTokenProlog(String codeTokenProlog) {
		this.codeTokenProlog = codeTokenProlog;
	}

	public String getCodeTokenDoctype() {
		return codeTokenDoctype;
	}

	public void setCodeTokenDoctype(String codeTokenDoctype) {
		this.codeTokenDoctype = codeTokenDoctype;
	}

	public String getCodeTokenCdata() {
		return codeTokenCdata;
	}

	public void setCodeTokenCdata(String codeTokenCdata) {
		this.codeTokenCdata = codeTokenCdata;
	}

	public String getCodeTokenPunctuation() {
		return codeTokenPunctuation;
	}

	public void setCodeTokenPunctuation(String codeTokenPunctuation) {
		this.codeTokenPunctuation = codeTokenPunctuation;
	}

	public String getCodeTokenProperty() {
		return codeTokenProperty;
	}

	public void setCodeTokenProperty(String codeTokenProperty) {
		this.codeTokenProperty = codeTokenProperty;
	}

	public String getCodeTokenTag() {
		return codeTokenTag;
	}

	public void setCodeTokenTag(String codeTokenTag) {
		this.codeTokenTag = codeTokenTag;
	}

	public String getCodeTokenConstant() {
		return codeTokenConstant;
	}

	public void setCodeTokenConstant(String codeTokenConstant) {
		this.codeTokenConstant = codeTokenConstant;
	}

	public String getCodeTokenSymbol() {
		return codeTokenSymbol;
	}

	public void setCodeTokenSymbol(String codeTokenSymbol) {
		this.codeTokenSymbol = codeTokenSymbol;
	}

	public String getCodeTokenDeleted() {
		return codeTokenDeleted;
	}

	public void setCodeTokenDeleted(String codeTokenDeleted) {
		this.codeTokenDeleted = codeTokenDeleted;
	}

	public String getCodeTokenBoolean() {
		return codeTokenBoolean;
	}

	public void setCodeTokenBoolean(String codeTokenBoolean) {
		this.codeTokenBoolean = codeTokenBoolean;
	}

	public String getCodeTokenNumber() {
		return codeTokenNumber;
	}

	public void setCodeTokenNumber(String codeTokenNumber) {
		this.codeTokenNumber = codeTokenNumber;
	}

	public String getCodeTokenSelector() {
		return codeTokenSelector;
	}

	public void setCodeTokenSelector(String codeTokenSelector) {
		this.codeTokenSelector = codeTokenSelector;
	}

	public String getCodeTokenAttrName() {
		return codeTokenAttrName;
	}

	public void setCodeTokenAttrName(String codeTokenAttrName) {
		this.codeTokenAttrName = codeTokenAttrName;
	}

	public String getCodeTokenString() {
		return codeTokenString;
	}

	public void setCodeTokenString(String codeTokenString) {
		this.codeTokenString = codeTokenString;
	}

	public String getCodeTokenChar() {
		return codeTokenChar;
	}

	public void setCodeTokenChar(String codeTokenChar) {
		this.codeTokenChar = codeTokenChar;
	}

	public String getCodeTokenBuiltin() {
		return codeTokenBuiltin;
	}

	public void setCodeTokenBuiltin(String codeTokenBuiltin) {
		this.codeTokenBuiltin = codeTokenBuiltin;
	}

	public String getCodeTokenInserted() {
		return codeTokenInserted;
	}

	public void setCodeTokenInserted(String codeTokenInserted) {
		this.codeTokenInserted = codeTokenInserted;
	}

	public String getCodeTokenOperator() {
		return codeTokenOperator;
	}

	public void setCodeTokenOperator(String codeTokenOperator) {
		this.codeTokenOperator = codeTokenOperator;
	}

	public String getCodeTokenEntity() {
		return codeTokenEntity;
	}

	public void setCodeTokenEntity(String codeTokenEntity) {
		this.codeTokenEntity = codeTokenEntity;
	}

	public String getCodeTokenUrl() {
		return codeTokenUrl;
	}

	public void setCodeTokenUrl(String codeTokenUrl) {
		this.codeTokenUrl = codeTokenUrl;
	}

	public String getCodeTokenVariable() {
		return codeTokenVariable;
	}

	public void setCodeTokenVariable(String codeTokenVariable) {
		this.codeTokenVariable = codeTokenVariable;
	}

	public String getCodeTokenAtrule() {
		return codeTokenAtrule;
	}

	public void setCodeTokenAtrule(String codeTokenAtrule) {
		this.codeTokenAtrule = codeTokenAtrule;
	}

	public String getCodeTokenAttrValue() {
		return codeTokenAttrValue;
	}

	public void setCodeTokenAttrValue(String codeTokenAttrValue) {
		this.codeTokenAttrValue = codeTokenAttrValue;
	}

	public String getCodeTokenFunction() {
		return codeTokenFunction;
	}

	public void setCodeTokenFunction(String codeTokenFunction) {
		this.codeTokenFunction = codeTokenFunction;
	}

	public String getCodeTokenKeyword() {
		return codeTokenKeyword;
	}

	public void setCodeTokenKeyword(String codeTokenKeyword) {
		this.codeTokenKeyword = codeTokenKeyword;
	}

	public String getCodeTokenRegex() {
		return codeTokenRegex;
	}

	public void setCodeTokenRegex(String codeTokenRegex) {
		this.codeTokenRegex = codeTokenRegex;
	}

	public String getCodeTokenImportant() {
		return codeTokenImportant;
	}

	public void setCodeTokenImportant(String codeTokenImportant) {
		this.codeTokenImportant = codeTokenImportant;
	}

}
