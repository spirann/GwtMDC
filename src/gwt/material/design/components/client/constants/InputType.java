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
package gwt.material.design.components.client.constants;

import gwt.material.design.components.client.utils.helper.EnumHelper;

/**
 * 
 * @author Richeli Vargas
 *
 */
public enum InputType implements CssType {

//	Not applicable to text field
	BUTTON("button"),
	CHECKBOX("checkbox"),
	COLOR("color"),	
	DATE("date"),
	DATETIME_LOCAL("datetime-local"),
	HIDDEN("hidden"),
	FILE("file"),
	RADIO("radio"),
	RANGE("range"),
	RESET("reset"),
	SUBMIT("submit"),
	TIME("time"),
	WEEK("week"),
//	Applicable to text field
	EMAIL("email"),
	NUMBER("number"),
	PASSWORD("password"),
	SEARCH("search"),
	TEL("tel"),
	TEXT("text"),
	URL("url");

    private final String cssClass;

    InputType(final String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public String getCssName() {
        return cssClass;
    }

    public static InputType fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, InputType.class, TEXT);
    }
}
