package exceptions;

import static exceptions.CustomExceptionEnum.DISCONNECT_ERROR;

/**
 * Created by Boss on 22.04.2018.
 */
public class DisconnectException extends RuntimeException {
    public DisconnectException() {
        super(DISCONNECT_ERROR.name());
    }

    public DisconnectException(Throwable cause) {
        super(DISCONNECT_ERROR.name(), cause);
    }
}
