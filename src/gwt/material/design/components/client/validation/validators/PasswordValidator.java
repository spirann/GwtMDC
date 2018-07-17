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
 *
 */
public class PasswordValidator {

	/**
	 * 
	 * @param value
	 * @return
	 * 0 - Very weak
	 * 1 - Weak
	 * 2 - Medium
	 * 3 - Strong
	 */
	public static native int passwordLevel(final String value)/*-{

		var strongRegex = new RegExp(
				"^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
		
		var mediumRegex = new RegExp(
				"^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$",
				"g");
		
		var enoughRegex = new RegExp("(?=.{6,}).*", "g");

		if (value.length == 0) {
			// Empty
			return -1;
		} else if (false == enoughRegex.test(value)) {
			// Very Weak
			return 0;
		} else if (strongRegex.test(value)) {
			// Strong
			return 3;
		} else if (mediumRegex.test(value)) {
			// Medium
			return 2;
		} else {
			// Weak
			return 1;
		}

	}-*/;

}
