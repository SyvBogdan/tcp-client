package client.Impl;

import client.TCPClient;
import connector.Connector;
import connector.Receiver;
import connector.Transmitter;
import org.apache.commons.lang3.SerializationUtils;
import packet.Packet;

/**
 * Created by Boss on 21.04.2018.
 */
public class TCPClientImpl implements TCPClient {

    private Connector connector;
    private Transmitter transmitter;
    private Receiver receiver;

    public TCPClientImpl(Connector connector, Transmitter transmitter, Receiver receiver) {
        this.connector = connector;
        this.transmitter = transmitter;
        this.receiver = receiver;

        connector.connect();
    }


    public Packet sendRequest(Packet packet) {
        transmitter.transmit(SerializationUtils.serialize(packet));
        //YourObject yourObject = SerializationUtils.deserialize(data);

        return receiver.receive();
    }

    public void setTimeOut(long milliseconds) {
        connector.setTimeOut((int) milliseconds);
    }

    public void connect() {
        connector.connect();
    }

    public void disconnect() {
        connector.disconnect();
    }
}
