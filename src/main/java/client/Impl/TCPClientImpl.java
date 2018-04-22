package client.Impl;

import client.TCPClient;
import connector.Connector;
import connector.Receiver;
import connector.Transmitter;
import exceptions.OperationException;
import org.apache.commons.lang3.SerializationUtils;
import packet.Packet;

/**
 * Created by Boss on 21.04.2018.
 */
public class TCPClientImpl implements TCPClient {

    private Connector connector;

    public TCPClientImpl(Connector connector) {
        this.connector = connector;
    }

    public Packet sendRequest(Packet packet) {

        try {
            synchronized (this) {
                checkConnection();
                connector.getTransmitter().transmit(packet);
                return connector.getReceiver().receive();
            }
        } catch (RuntimeException e) {
            throw new OperationException(e);
        }
    }

    public void setTimeOut(long milliseconds) {
        connector.setTimeOut((int) milliseconds);
    }

    private void transmit(Packet packet) {
        connector.getTransmitter().transmit(packet);
    }

    private Packet receive(Packet packet) {
        return connector.getReceiver().receive();
    }


    public void connect() {
        connector.connect();
    }

    public void disconnect() {
        connector.disconnect();
    }

    void checkConnection(){
        if(!connector.isConnected()){
            connector.reconnect();
        }
    }

}
