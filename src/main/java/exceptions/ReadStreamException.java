package exceptions;

import static exceptions.CustomExceptionEnum.READ_IS_ERROR;

/**
 * Created by Boss on 22.04.2018.
 */
public class ReadStreamException extends RuntimeException {
    public ReadStreamException() {
        super(READ_IS_ERROR.name());
    }

    public ReadStreamException(Throwable cause) {
        super(READ_IS_ERROR.name(), cause);
    }
}
