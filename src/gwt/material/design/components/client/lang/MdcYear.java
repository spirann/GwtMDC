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
package gwt.material.design.components.client.lang;

import java.util.Date;

import gwt.material.design.components.client.base.interfaces.HasNext;
import gwt.material.design.components.client.base.interfaces.HasPrevious;
import gwt.material.design.components.client.resources.message.IMessages;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MdcYear implements HasPrevious<MdcYear>, HasNext<MdcYear>, Comparable<MdcYear> {

	private int year;

	public MdcYear() {
		this(new Date());
	}

	public MdcYear(final long timestamp) {
		this(new Date(timestamp));
	}

	@SuppressWarnings("deprecation")
	public MdcYear(final Date date) {
		this(date.getYear() + 1900);
	}

	public MdcYear(final int year) {
		super();

		this.year = year;

		setYear(this.year);
	}

	public int getYear() {
		return year;
	}

	public void setYear(final int year) {

		if (year < 0)
			throw new IllegalArgumentException(IMessages.INSTANCE.mdc_date__err__year_out_of_range(year));

		this.year = year;

	}

	public MdcYear next() {
		return new MdcYear(year + 1);
	}

	public MdcYear previous() {
		return new MdcYear(year - 1);
	}

	@Override
	public String toString() {
		return String.valueOf(year);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MdcYear other = (MdcYear) obj;
		if (year != other.year)
			return false;
		return true;
	}

	@Override
	public int compareTo(MdcYear year) {
		if (year == null)
			return -1;
		return Integer.compare(this.year, year.getYear());
	}

}
