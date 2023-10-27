package es.upm.pproject.exceptions;

/*
 * An exception thrown when trying enroll a student 
 * who's already enrolled into a course
 */
public class AlreadyEnrolledException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public AlreadyEnrolledException(String message) {
		super(message);
	}

}