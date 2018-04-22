package exceptions;

import static exceptions.Event.CONNECT_ERROR;

/**
 * Created by Boss on 22.04.2018.
 */
public class ConnectException extends RuntimeException {

    public ConnectException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectException(String message) {
        super(message);
    }
}
