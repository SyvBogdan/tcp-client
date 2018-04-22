package connector;


public interface Connector {

    //will be transmit and receive

    void connect();

    void setTimeOut(int milliseconds);

    Receiver getReceiver();

    Transmitter getTransmitter();

    boolean isConnected();

    void disconnect();

    void reconnect();
}
