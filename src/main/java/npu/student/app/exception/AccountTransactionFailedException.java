package npu.student.app.exception;

public class AccountTransactionFailedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8969412701939659380L;

	public AccountTransactionFailedException(String message) {
		super(message);
	}
}
