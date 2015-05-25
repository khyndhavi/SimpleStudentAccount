package npu.student.app.exception;

public class StudentDoesNotExistException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -41066012230936850L;

	public StudentDoesNotExistException(String message) {
		super(message);
	}
}
