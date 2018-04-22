package client;

import packet.Packet;

public interface TCPClient {

    Packet sendRequest(Packet packet);

    void setTimeOut(long milliseconds);

    void connect();

    void disconnect();
}
