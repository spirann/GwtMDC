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

/**
 * 
 * @author Richeli Vargas
 *
 */
public class ObjectHelper {

	public final static boolean isNullOrEmpty(final Object object) {
		return object == null || object.toString().trim().isEmpty()
				|| (object instanceof Object[] ? ((Object[]) object).length == 0 : false);
	}

	public final static boolean isNotNullAndNotEmpty(final Object object) {
		return object != null && !object.toString().trim().isEmpty()
				&& (object instanceof Object[] ? ((Object[]) object).length > 0 : true);
	}

	public final static boolean isNull(final Object object) {
		return object == null;
	}

	public final static boolean isNotNull(final Object object) {
		return object != null;
	}

	public static Integer toInteger(final String string) {
		return toInteger(string, null);
	}

	public static Integer toInteger(final String string, final Integer defaultValue) {
		final String value = string == null ? null : string.replaceAll("[^0-9]", "");
		return value == null || value.isEmpty() ? defaultValue : Integer.valueOf(value);
	}

	public static native char noNull(final char variable) /*-{
		if (typeof variable === 'undefined' || variable === undefined
				|| variable === null)
			return 0;
		return variable;
	}-*/;

	public static native byte noNull(final byte variable) /*-{
		if (typeof variable === 'undefined' || variable === undefined
				|| variable === null)
			return 0;
		return variable;
	}-*/;

	public static native int noNull(final int variable) /*-{
		if (typeof variable === 'undefined' || variable === undefined
				|| variable === null)
			return 0;
		return variable;
	}-*/;

	public static native double noNull(final double variable) /*-{
		if (typeof variable === 'undefined' || variable === undefined
				|| variable === null)
			return 0;
		return variable;
	}-*/;

	public static native float noNull(final float variable) /*-{
		if (typeof variable === 'undefined' || variable === undefined
				|| variable === null)
			return 0;
		return variable;
	}-*/;

	public static native boolean noNull(final boolean variable) /*-{
		if (typeof variable === 'undefined' || variable === undefined
				|| variable === null)
			return 0;
		return variable;
	}-*/;

}
