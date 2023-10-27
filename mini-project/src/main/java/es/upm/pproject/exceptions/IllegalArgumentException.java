package es.upm.pproject.exceptions;

/*
 * An exception thrown when the input arguments are
 * blank, invalid or missing
 */
public class IllegalArgumentException extends Exception {
	
	private static final long serialVersionUID = 3L;
	
	public IllegalArgumentException(String message) {
		super(message);
	}

}