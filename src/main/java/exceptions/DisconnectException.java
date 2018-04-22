package exceptions;


/**
 * Created by Boss on 22.04.2018.
 */
public class DisconnectException extends RuntimeException {

    public DisconnectException(String message, Throwable cause) {
        super(message, cause);
    }
}
