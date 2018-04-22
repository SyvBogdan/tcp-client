package connector;


public interface Connector {

    //will be transmit and receive

    void connect();

    void setTimeOut(int milliseconds);

    void disconnect();

    void reconnect();
}
