package connector.impl;

import connector.Connector;
import connector.Receiver;
import connector.Transmitter;
import exceptions.ConnectException;
import exceptions.DisconnectException;
import exceptions.Event;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

import static exceptions.Event.DISCONNECT_ERROR;
import static exceptions.Event.SET_TIME_OUT_ERROR;

/**
 * Created by Boss on 22.04.2018.
 */
public class SocketConnectorImpl implements Connector {

    public static String DEFAULT_HOST = "127.0.0.1";
    public static int DEFAULT_PORT = 8080;

    private String host;
    private int port;
    private int timeOut;
    private Socket clientSocket;
    private Transmitter transmitter;
    private Receiver receiver;

    public SocketConnectorImpl() {
        this(DEFAULT_HOST, DEFAULT_PORT);
    }

    public SocketConnectorImpl(String host, int port) {
        this(host, port, 0);
    }

    public SocketConnectorImpl(String host, int port, int timeOut) {
        this.host = host;
        this.port = port;
        this.timeOut = timeOut;
        this.clientSocket = createClientSocket();
        configure();
    }

    private void configure() {
        try {
            this.receiver = new ReceiverImpl(clientSocket.getInputStream());
            this.transmitter = new TransmitterImpl(clientSocket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void connect() {
        // Connect to socket by host, port, and with specified timeout
        try {
            if (clientSocket != null)
                clientSocket.connect(new InetSocketAddress(InetAddress.getByName(host), port), timeOut);
        } catch (IOException e) {
            throw new ConnectException(Event.CONNECT_ERROR.name(), e);
        }
    }

    @Override
    public void setTimeOut(int milliseconds) {
        //Set time out for term of request processing (if time out fires the exception will appears)
        try {
            clientSocket.setSoTimeout(milliseconds);
        } catch (SocketException e) {
            e.printStackTrace();
            throw new RuntimeException(SET_TIME_OUT_ERROR.name());
        }
    }

    @Override
    public void disconnect() {
        if (!clientSocket.isClosed()) {
            try {
                clientSocket.close();
            } catch (IOException e) {
                throw new DisconnectException(DISCONNECT_ERROR.name(), e);
            }
        }
    }

    @Override
    public void reconnect() {
        //close old socket connections
        disconnect();
        //create new socket object and try to connect to host, port
        connect();
    }

    private Socket createClientSocket() {
        try {
            clientSocket = new Socket(this.host, this.port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clientSocket;
    }

    @Override
    public Receiver getReceiver() {
        return receiver;
    }

    @Override
    public Transmitter getTransmitter() {
        return transmitter;
    }

    @Override
    public boolean isConnected() {
        return clientSocket.isConnected();
    }

}
