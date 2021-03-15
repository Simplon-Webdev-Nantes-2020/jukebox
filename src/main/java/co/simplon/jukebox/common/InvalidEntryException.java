package co.simplon.jukebox.common;


public class InvalidEntryException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final String category;

	public InvalidEntryException(String category, String message) {
        super(message);
        this.category = category;
    }

	public String getCategory() {
		return category;
	}
}
