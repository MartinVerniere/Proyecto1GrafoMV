package Excepciones;

@SuppressWarnings("serial")
public class InvalidPositionException extends Exception  {
	public InvalidPositionException(String msg) {
		super(msg);
	}
}
