package es.upm.pproject.exceptions;

/*
 * An exception thrown when a student is not
 * registered in the system
 */
public class NoSuchStudentException extends Exception {
	
	private static final long serialVersionUID = 6L;
	
	public NoSuchStudentException(String message) {
		super(message);
	}

}