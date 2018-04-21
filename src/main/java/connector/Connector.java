package connector;


public interface Connector {

    void connect();

    void setTimeOut(long milliseconds);

    void disconnect();

    void reconnect();
}
