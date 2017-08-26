package trng.imcs.exception;

public class DuplicateEmployeeException extends Exception{

	private static final long serialVersionUID = -6411469824935379020L;

	public DuplicateEmployeeException() {
		super();
	}

	public DuplicateEmployeeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DuplicateEmployeeException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateEmployeeException(String message) {
		super(message);
	}

	public DuplicateEmployeeException(Throwable cause) {
		super(cause);
	}
	

}
