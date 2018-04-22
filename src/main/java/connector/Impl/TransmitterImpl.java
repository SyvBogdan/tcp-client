package connector.Impl;

import connector.Transmitter;
import exceptions.WriteStreamException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Boss on 22.04.2018.
 */
public class TransmitterImpl implements Transmitter {

    private OutputStream outputStream;

    public TransmitterImpl(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void transmit(byte[] data) {
        try {
            outputStream.write(data);
            outputStream.flush();
        } catch (IOException e) {
            throw new WriteStreamException(e);
        }


    }
}
