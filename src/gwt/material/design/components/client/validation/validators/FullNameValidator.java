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
package gwt.material.design.components.client.validation.validators;

/**
 *
 * @author Richeli Vargas
 */
public class FullNameValidator {

    public static boolean isValid(final String fullName) {

        if (fullName == null || fullName.isEmpty()) {
            return false;
        }
        
        final String[] names = fullName.split(" ");
        
        if(names == null){
        	return false;
        }
        
        return fullName.replaceAll(" ", "").length() >= 5 && (names.length >= 2 ? !names[0].equalsIgnoreCase(names[1]) : false);
    }

}
