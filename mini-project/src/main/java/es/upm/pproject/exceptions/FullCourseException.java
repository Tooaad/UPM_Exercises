package es.upm.pproject.exceptions;

/*
 * An exception thrown when the maximum number of
 * enrollments (50) has been surpassed 
 */
public class FullCourseException extends Exception {
	
	private static final long serialVersionUID = 2L;
	
	public FullCourseException(String message) {
		super(message);
	}

}