package connector.impl;

import connector.Receiver;
import exceptions.ReadStreamException;
import org.apache.commons.lang3.SerializationUtils;
import packet.Packet;
import packet.PacketImpl;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Boss on 22.04.2018.
 */
public class ReceiverImpl implements Receiver {

    private InputStream inputStream;

    public ReceiverImpl(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public Packet receive() {
        try {
            byte[] byteArr = new byte[inputStream.available()];
            inputStream.read(byteArr);
            return new PacketImpl(byteArr);
        } catch (IOException e) {
            throw new ReadStreamException(e);
        }
    }
}
