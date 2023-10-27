package es.upm.pproject.exceptions;

/*
 * An exception thrown when a student is not enrolled
 * into a given course
 */
public class NoSuchEnrollmentException extends Exception {
	
	private static final long serialVersionUID = 5L;
	
	public NoSuchEnrollmentException(String message) {
		super(message);
	}

}