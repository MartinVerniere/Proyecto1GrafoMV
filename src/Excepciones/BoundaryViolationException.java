package Excepciones;

@SuppressWarnings("serial")
public class BoundaryViolationException extends Exception {
	public BoundaryViolationException(String msg) {
		super(msg);
	}
}
