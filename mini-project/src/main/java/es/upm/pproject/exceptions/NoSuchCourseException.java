package es.upm.pproject.exceptions;

/*
 * An exception thrown when course is not registered
 */
public class NoSuchCourseException extends Exception {
	
	private static final long serialVersionUID = 4L;
	
	public NoSuchCourseException(String message) {
		super(message);
	}

}