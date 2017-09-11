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
	//
	private String ripplePrimary;
	private String rippleSecondary;
	//
	private String textPrimaryOnPrimary;
	private String textSecondaryOnPrimary;
	private String textHintOnPrimary;
	private String textDisabledOnPrimary;
	private String textIconOnPrimary;
	private String rippleOnPrimary;
	//
	private String textPrimaryOnSecondary;
	private String textSecondaryOnSecondary;
	private String textHintOnSecondary;
	private String textDisabledOnSecondary;
	private String textIconOnSecondary;
	private String rippleOnSecondary;
	//
	private String textPrimaryOnBackground;
	private String textSecondaryOnBackground;
	private String textHintOnBackground;
	private String textDisabledOnBackground;
	private String textIconOnBackground;
	private String rippleOnBackground;
	//
	private String textPrimaryOnLight;
	private String textSecondaryOnLight;
	private String textHintOnLight;
	private String textDisabledOnLight;
	private String textIconOnLight;
	private String rippleOnLight;
	//
	private String textPrimaryOnDark;
	private String textSecondaryOnDark;
	private String textHintOnDark;
	private String textDisabledOnDark;
	private String textIconOnDark;
	private String rippleOnDark;
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

	public String getText() {

		final StringBuilder text = new StringBuilder();
		text.append(":root {");
		//
		text.append(loadProperty(ThemeAttribute.MDC_THEME_PRIMARY, primary));
		text.append(loadProperty(ThemeAttribute.MDC_THEME_SECONDARY, secondary));
		text.append(loadProperty(ThemeAttribute.MDC_THEME_BACKGROUND, background));
		//
		text.append(loadProperty(ThemeAttribute.MDC_THEME_RIPPLE_PRIMARY, ripplePrimary));
		text.append(loadProperty(ThemeAttribute.MDC_THEME_RIPPLE_SECONDARY, rippleSecondary));
		//
		text.append(loadProperty(ThemeAttribute.MDC_THEME_TEXT_PRIMARY_ON_PRIMARY, textPrimaryOnPrimary));
		text.append(loadProperty(ThemeAttribute.MDC_THEME_TEXT_SECONDARY_ON_PRIMARY, textSecondaryOnPrimary));
		text.append(loadProperty(ThemeAttribute.MDC_THEME_TEXT_HINT_ON_PRIMARY, textHintOnPrimary));
		text.append(loadProperty(ThemeAttribute.MDC_THEME_TEXT_DISABLED_ON_PRIMARY, textDisabledOnPrimary));
		text.append(loadProperty(ThemeAttribute.MDC_THEME_TEXT_ICON_ON_PRIMARY, textIconOnPrimary));
		text.append(loadProperty(ThemeAttribute.MDC_THEME_RIPPLE_ON_PRIMARY, rippleOnPrimary));
		//
		text.append(loadProperty(ThemeAttribute.MDC_THEME_TEXT_PRIMARY_ON_SECONDARY, textPrimaryOnSecondary));
		text.append(loadProperty(ThemeAttribute.MDC_THEME_TEXT_SECONDARY_ON_SECONDARY, textSecondaryOnSecondary));
		text.append(loadProperty(ThemeAttribute.MDC_THEME_TEXT_HINT_ON_SECONDARY, textHintOnSecondary));
		text.append(loadProperty(ThemeAttribute.MDC_THEME_TEXT_DISABLED_ON_SECONDARY, textDisabledOnSecondary));
		text.append(loadProperty(ThemeAttribute.MDC_THEME_TEXT_ICON_ON_SECONDARY, textIconOnSecondary));
		text.append(loadProperty(ThemeAttribute.MDC_THEME_RIPPLE_ON_SECONDARY, rippleOnSecondary));
		//
		text.append(loadProperty(ThemeAttribute.MDC_THEME_TEXT_PRIMARY_ON_BACKGROUND, textPrimaryOnBackground));
		text.append(loadProperty(ThemeAttribute.MDC_THEME_TEXT_SECONDARY_ON_BACKGROUND, textSecondaryOnBackground));
		text.append(loadProperty(ThemeAttribute.MDC_THEME_TEXT_HINT_ON_BACKGROUND, textHintOnBackground));
		text.append(loadProperty(ThemeAttribute.MDC_THEME_TEXT_DISABLED_ON_BACKGROUND, textDisabledOnBackground));
		text.append(loadProperty(ThemeAttribute.MDC_THEME_TEXT_ICON_ON_BACKGROUND, textIconOnBackground));
		text.append(loadProperty(ThemeAttribute.MDC_THEME_RIPPLE_ON_BACKGROUND, rippleOnBackground));
		//
		text.append(loadProperty(ThemeAttribute.MDC_THEME_TEXT_PRIMARY_ON_LIGHT, textPrimaryOnLight));
		text.append(loadProperty(ThemeAttribute.MDC_THEME_TEXT_SECONDARY_ON_LIGHT, textSecondaryOnLight));
		text.append(loadProperty(ThemeAttribute.MDC_THEME_TEXT_HINT_ON_LIGHT, textHintOnLight));
		text.append(loadProperty(ThemeAttribute.MDC_THEME_TEXT_DISABLED_ON_LIGHT, textDisabledOnLight));
		text.append(loadProperty(ThemeAttribute.MDC_THEME_TEXT_ICON_ON_LIGHT, textIconOnLight));
		text.append(loadProperty(ThemeAttribute.MDC_THEME_RIPPLE_ON_LIGHT, rippleOnLight));
		//
		text.append(loadProperty(ThemeAttribute.MDC_THEME_TEXT_PRIMARY_ON_DARK, textPrimaryOnDark));
		text.append(loadProperty(ThemeAttribute.MDC_THEME_TEXT_SECONDARY_ON_DARK, textSecondaryOnDark));
		text.append(loadProperty(ThemeAttribute.MDC_THEME_TEXT_HINT_ON_DARK, textHintOnDark));
		text.append(loadProperty(ThemeAttribute.MDC_THEME_TEXT_DISABLED_ON_DARK, textDisabledOnDark));
		text.append(loadProperty(ThemeAttribute.MDC_THEME_TEXT_ICON_ON_DARK, textIconOnDark));
		text.append(loadProperty(ThemeAttribute.MDC_THEME_RIPPLE_ON_DARK, rippleOnDark));
		//
		text.append(loadProperty("--mdc-theme-code-background", codeBackground));
		text.append(loadProperty("--mdc-theme-code-String", codeString));
		text.append(loadProperty("--mdc-theme-code-token-comment", codeTokenComment));
		text.append(loadProperty("--mdc-theme-code-token-prolog", codeTokenProlog));
		text.append(loadProperty("--mdc-theme-code-token-doctype", codeTokenDoctype));
		text.append(loadProperty("--mdc-theme-code-token-cdata", codeTokenCdata));
		text.append(loadProperty("--mdc-theme-code-token-punctuation", codeTokenPunctuation));
		text.append(loadProperty("--mdc-theme-code-token-property", codeTokenProperty));
		text.append(loadProperty("--mdc-theme-code-token-tag", codeTokenTag));
		text.append(loadProperty("--mdc-theme-code-token-constant", codeTokenConstant));
		text.append(loadProperty("--mdc-theme-code-token-symbol", codeTokenSymbol));
		text.append(loadProperty("--mdc-theme-code-token-deleted", codeTokenDeleted));
		text.append(loadProperty("--mdc-theme-code-token-boolean", codeTokenBoolean));
		text.append(loadProperty("--mdc-theme-code-token-number", codeTokenNumber));
		text.append(loadProperty("--mdc-theme-code-token-selector", codeTokenSelector));
		text.append(loadProperty("--mdc-theme-code-token-attr-name", codeTokenAttrName));
		text.append(loadProperty("--mdc-theme-code-token-String", codeTokenString));
		text.append(loadProperty("--mdc-theme-code-token-char", codeTokenChar));
		text.append(loadProperty("--mdc-theme-code-token-builtin", codeTokenBuiltin));
		text.append(loadProperty("--mdc-theme-code-token-inserted", codeTokenInserted));
		text.append(loadProperty("--mdc-theme-code-token-operator", codeTokenOperator));
		text.append(loadProperty("--mdc-theme-code-token-entity", codeTokenEntity));
		text.append(loadProperty("--mdc-theme-code-token-url", codeTokenUrl));
		text.append(loadProperty("--mdc-theme-code-token-variable", codeTokenVariable));
		text.append(loadProperty("--mdc-theme-code-token-atrule", codeTokenAtrule));
		text.append(loadProperty("--mdc-theme-code-token-attr-value", codeTokenAttrValue));
		text.append(loadProperty("--mdc-theme-code-token-function", codeTokenFunction));
		text.append(loadProperty("--mdc-theme-code-token-keyword", codeTokenKeyword));
		text.append(loadProperty("--mdc-theme-code-token-regex", codeTokenRegex));
		text.append(loadProperty("--mdc-theme-code-token-important", codeTokenImportant));
		//
		text.append("}");

		return text.toString();

	}

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

	public String getTextPrimaryOnPrimary() {
		return textPrimaryOnPrimary;
	}

	public void setTextPrimaryOnPrimary(String textPrimaryOnPrimary) {
		this.textPrimaryOnPrimary = textPrimaryOnPrimary;
	}

	public String getTextSecondaryOnPrimary() {
		return textSecondaryOnPrimary;
	}

	public void setTextSecondaryOnPrimary(String textSecondaryOnPrimary) {
		this.textSecondaryOnPrimary = textSecondaryOnPrimary;
	}

	public String getTextHintOnPrimary() {
		return textHintOnPrimary;
	}

	public void setTextHintOnPrimary(String textHintOnPrimary) {
		this.textHintOnPrimary = textHintOnPrimary;
	}

	public String getTextDisabledOnPrimary() {
		return textDisabledOnPrimary;
	}

	public void setTextDisabledOnPrimary(String textDisabledOnPrimary) {
		this.textDisabledOnPrimary = textDisabledOnPrimary;
	}

	public String getTextIconOnPrimary() {
		return textIconOnPrimary;
	}

	public void setTextIconOnPrimary(String textIconOnPrimary) {
		this.textIconOnPrimary = textIconOnPrimary;
	}

	public String getTextPrimaryOnSecondary() {
		return textPrimaryOnSecondary;
	}

	public void setTextPrimaryOnSecondary(String textPrimaryOnSecondary) {
		this.textPrimaryOnSecondary = textPrimaryOnSecondary;
	}

	public String getTextSecondaryOnSecondary() {
		return textSecondaryOnSecondary;
	}

	public void setTextSecondaryOnSecondary(String textSecondaryOnSecondary) {
		this.textSecondaryOnSecondary = textSecondaryOnSecondary;
	}

	public String getTextHintOnSecondary() {
		return textHintOnSecondary;
	}

	public void setTextHintOnSecondary(String textHintOnSecondary) {
		this.textHintOnSecondary = textHintOnSecondary;
	}

	public String getTextDisabledOnSecondary() {
		return textDisabledOnSecondary;
	}

	public void setTextDisabledOnSecondary(String textDisabledOnSecondary) {
		this.textDisabledOnSecondary = textDisabledOnSecondary;
	}

	public String getTextIconOnSecondary() {
		return textIconOnSecondary;
	}

	public void setTextIconOnSecondary(String textIconOnSecondary) {
		this.textIconOnSecondary = textIconOnSecondary;
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

	public String getTextPrimaryOnLight() {
		return textPrimaryOnLight;
	}

	public void setTextPrimaryOnLight(String textPrimaryOnLight) {
		this.textPrimaryOnLight = textPrimaryOnLight;
	}

	public String getTextSecondaryOnLight() {
		return textSecondaryOnLight;
	}

	public void setTextSecondaryOnLight(String textSecondaryOnLight) {
		this.textSecondaryOnLight = textSecondaryOnLight;
	}

	public String getTextHintOnLight() {
		return textHintOnLight;
	}

	public void setTextHintOnLight(String textHintOnLight) {
		this.textHintOnLight = textHintOnLight;
	}

	public String getTextDisabledOnLight() {
		return textDisabledOnLight;
	}

	public void setTextDisabledOnLight(String textDisabledOnLight) {
		this.textDisabledOnLight = textDisabledOnLight;
	}

	public String getTextIconOnLight() {
		return textIconOnLight;
	}

	public void setTextIconOnLight(String textIconOnLight) {
		this.textIconOnLight = textIconOnLight;
	}

	public String getTextPrimaryOnDark() {
		return textPrimaryOnDark;
	}

	public void setTextPrimaryOnDark(String textPrimaryOnDark) {
		this.textPrimaryOnDark = textPrimaryOnDark;
	}

	public String getTextSecondaryOnDark() {
		return textSecondaryOnDark;
	}

	public void setTextSecondaryOnDark(String textSecondaryOnDark) {
		this.textSecondaryOnDark = textSecondaryOnDark;
	}

	public String getTextHintOnDark() {
		return textHintOnDark;
	}

	public void setTextHintOnDark(String textHintOnDark) {
		this.textHintOnDark = textHintOnDark;
	}

	public String getTextDisabledOnDark() {
		return textDisabledOnDark;
	}

	public void setTextDisabledOnDark(String textDisabledOnDark) {
		this.textDisabledOnDark = textDisabledOnDark;
	}

	public String getTextIconOnDark() {
		return textIconOnDark;
	}

	public void setTextIconOnDark(String textIconOnDark) {
		this.textIconOnDark = textIconOnDark;
	}

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

	public String getRipplePrimary() {
		return ripplePrimary;
	}

	public void setRipplePrimary(String ripplePrimary) {
		this.ripplePrimary = ripplePrimary;
	}

	public String getRippleSecondary() {
		return rippleSecondary;
	}

	public void setRippleSecondary(String rippleSecondary) {
		this.rippleSecondary = rippleSecondary;
	}

	public String getRippleOnPrimary() {
		return rippleOnPrimary;
	}

	public void setRippleOnPrimary(String rippleOnPrimary) {
		this.rippleOnPrimary = rippleOnPrimary;
	}

	public String getRippleOnSecondary() {
		return rippleOnSecondary;
	}

	public void setRippleOnSecondary(String rippleOnSecondary) {
		this.rippleOnSecondary = rippleOnSecondary;
	}

	public String getRippleOnBackground() {
		return rippleOnBackground;
	}

	public void setRippleOnBackground(String rippleOnBackground) {
		this.rippleOnBackground = rippleOnBackground;
	}

	public String getRippleOnLight() {
		return rippleOnLight;
	}

	public void setRippleOnLight(String rippleOnLight) {
		this.rippleOnLight = rippleOnLight;
	}

	public String getRippleOnDark() {
		return rippleOnDark;
	}

	public void setRippleOnDark(String rippleOnDark) {
		this.rippleOnDark = rippleOnDark;
	}
	
}
