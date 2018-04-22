package exceptions;

import static exceptions.CustomExceptionEnum.WRITE_OS_ERROR;

/**
 * Created by Boss on 22.04.2018.
 */
public class WriteStreamException extends RuntimeException{
    public WriteStreamException() {
        super(WRITE_OS_ERROR.name());
    }

    public WriteStreamException(Throwable cause) {
        super(WRITE_OS_ERROR.name(), cause);
    }
}
