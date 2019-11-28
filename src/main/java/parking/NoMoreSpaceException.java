package parking;

public class NoMoreSpaceException extends RuntimeException {

    NoMoreSpaceException(String message) {
        super(message);
    }
}
