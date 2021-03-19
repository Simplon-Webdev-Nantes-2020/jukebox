package co.simplon.jukebox.common;


import org.springframework.http.HttpStatus;

public class InternalException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final HttpStatus httpStatus;

	public InternalException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
