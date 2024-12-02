package model.exceptions;

/**
 * Exception thrown when a card is used invalidly.
 */
public class InvalidCardUsageException extends RuntimeException {

    /**
     * Constructs an InvalidCardUsageException with a specific message.
     *
     * @param message the detail message
     */
    public InvalidCardUsageException(String message) {
        super(message);
    }
}
