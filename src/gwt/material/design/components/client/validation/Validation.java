package gwt.material.design.components.client.validation;

import gwt.material.design.components.client.constants.State;

public interface Validation {

	public static class Result {

		private final State state;
		private final int code;
		private final String message;

		public Result(final State state, final int code, final String message) {
			this.state = state;
			this.code = code;
			this.message = message;
		}
		
		public Result(final State state, final String message) {
			this(state, 0, message);
		}

		public State getState() {
			return state;
		}

		public int getCode() {
			return code;
		}

		public String getMessage() {
			return message;
		}

	}

}
