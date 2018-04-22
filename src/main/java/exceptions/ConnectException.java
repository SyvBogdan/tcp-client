package exceptions;

import static exceptions.CustomExceptionEnum.CONNECT_ERROR;

/**
 * Created by Boss on 22.04.2018.
 */
public class ConnectException extends RuntimeException {
    public ConnectException() {
        super(CONNECT_ERROR.name());
    }

    public ConnectException(Throwable cause) {
        super(CONNECT_ERROR.name(), cause);
    }
}
