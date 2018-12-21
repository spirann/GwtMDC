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
package gwt.material.design.components.client.base.mixin;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import gwt.material.design.components.client.base.interfaces.HasValidation;
import gwt.material.design.components.client.base.mixin.base.AbstractMixin;
import gwt.material.design.components.client.base.widget.MaterialUIObject;
import gwt.material.design.components.client.constants.State;
import gwt.material.design.components.client.validation.Validation;
import gwt.material.design.components.client.validation.Validation.Result;
import gwt.material.design.components.client.validation.ValidationRegistration;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class ValidationMixin<UIO extends MaterialUIObject, V extends Validation<UIO>> extends AbstractMixin<UIO> implements HasValidation<UIO, V> {

	protected final List<Validation<UIO>> validations = new LinkedList<>();

	public ValidationMixin(final UIO uiObject) {
		super(uiObject);
	}

	@Override
	public ValidationRegistration addValidation(V validation) {
		if (validation == null)
			throw new IllegalArgumentException("Validation can not be null");

		validations.add(validation);
		return () -> {
			validations.remove(validation);
		};
	}

	/**
	 * Apply all validations
	 * 
	 * @return
	 */
	@Override
	public Collection<Result> validate() {
		return validations.stream().map(validation -> validation.validate(uiObject)).collect(Collectors.toList());
	}

	public final static Result toResult(final Collection<Result> results) {

		final Collection<Result> erros = filterResults(results, State.ERROR);
		if (!erros.isEmpty())
			return erros.iterator().next();

		final Collection<Result> warnings = filterResults(results, State.WARNING);
		if (!warnings.isEmpty())
			return warnings.iterator().next();

		final Collection<Result> success = filterResults(results, State.SUCCESS);
		if (!success.isEmpty())
			return success.iterator().next();

		final Collection<Result> nones = filterResults(results, State.NONE);
		if (!nones.isEmpty())
			return nones.iterator().next();

		return new Result(State.NONE);

	}

	public final static Collection<Result> filterResults(final Collection<Result> results, final State state) {
		return results.stream().filter(result -> result.getState().equals(state)).sorted((a, b) -> a.getMessage().compareTo(b.getMessage())).collect(Collectors.toList());
	}

}
