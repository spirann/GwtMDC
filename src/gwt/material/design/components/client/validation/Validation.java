package gwt.material.design.components.client.validation;

import gwt.material.design.components.client.constants.State;

public interface Validation {

	public static class Result {

		private final State state;
		private final String message;

		public Result(final State state, final String message) {
			this.state = state;
			this.message = message;
		}

		public State getState() {
			return state;
		}

		public String getMessage() {
			return message;
		}

	}

}
