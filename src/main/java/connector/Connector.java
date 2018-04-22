package connector;


public interface Connector {

    //будет transmit and receive

    void connect();

    void setTimeOut(long milliseconds);

    void disconnect();

    void reconnect();
}
