package connector.Impl;

import connector.Connector;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by Boss on 22.04.2018.
 */
public class ConnectorImpl implements Connector {

    public static String DEFAULT_HOST = "127.0.0.1";
    public static int DEFAULT_PORT = 8080;

    private String host;
    private int port;
    private int timeOut;
    private Socket clientSocket;

    public ConnectorImpl() {
        this(DEFAULT_HOST, DEFAULT_PORT);
    }

    public ConnectorImpl(String host, int port) {
        this(host, port, 0);
    }

    public ConnectorImpl(String host, int port, int timeOut) {
        this.host = host;
        this.port = port;
        this.timeOut = timeOut;
        this.clientSocket = getClientSocket();
    }

    @Override
    public void connect() {
        // Connect to socket by host, port, and with specified timeout
        try {
            clientSocket.connect(new InetSocketAddress(InetAddress.getByName(host), port), timeOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setTimeOut(int milliseconds) {
        //Set time out for term of request processing (if time out fires the exception will appears)
        try {
            clientSocket.setSoTimeout(milliseconds);
        } catch (SocketException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't set TimeOut");
        }
    }

    @Override
    public void disconnect() {
        if(!clientSocket.isInputShutdown()){
            try {
                clientSocket.getInputStream().close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Can't close Client socket's InputStream");
            }
        }

        if(!clientSocket.isOutputShutdown()){
            try {
                clientSocket.getOutputStream().close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Can't close Client socket's OutputStream");
            }
        }

        if(!clientSocket.isConnected() || !clientSocket.isClosed()){
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Can't close Client's socket");
            }
        }
    }

    @Override
    public void reconnect() {
        //close old socket connections
        disconnect();

        //create new socket object and try to connect to host, port
        connect();

        //reuse the address when trying to reconnect
        try {
            clientSocket.setReuseAddress(true);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    private Socket getClientSocket() {
        if (this.clientSocket == null) {
            try {
                clientSocket = new Socket(this.host, this.port);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return clientSocket;
    }
}
