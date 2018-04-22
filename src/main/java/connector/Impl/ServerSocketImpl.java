package connector.Impl;

import packet.Packet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Optional;

import static packet.Packet.toPacket;

/**
 * Created by Boss on 21.04.2018.
 */
public class ServerSocketImpl {

    public static final Integer DEFAULT_SERVER_SOCKET_PORT = 8080;

    private static ServerSocket server;
    private static int port;

    public ServerSocketImpl(int port) {
        this.port = port;

        if (this.server != null) {
            if (this.server.getLocalPort() != port) {
                closeServer();
                getServer();
            }
        } else {
            getServer();
        }
    }

    public void runServer(){
        //TODO logging collaboration between Server and Client

        while (true){
            // Listen for connection
            Socket listener = getSocketListener();

            //Once client has connected, use socket stream to send a prompt message to client.
            this.transmit("Send Message to Client".getBytes(), listener);

            // Prompt for client.

            // Get input stream produced by client (to read sent message).
            Packet receivePacket = this.receive(listener);

            // Output sent message from client.
            System.out.println("Receive Message from Client" + receivePacket.toString());

            // Close writer and socket.
            closeSocketListener(listener);

            // Output message from client.
            //Logging.log(String.format("[FROM Client] %s", output));

            // Loop back, awaiting a new client connection.
        }
    }

    private static ServerSocket getServer(){
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return server;
    }

    private static void closeServer() {
        if (server != null && !server.isClosed()) {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    public static void setServerTimeOut(int milliseconds){
        try {
            server.setSoTimeout(milliseconds);
        } catch (SocketException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public Socket getSocketListener() {
        try {
            return server.accept();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void closeSocketListener(final Socket listener){
        Optional.ofNullable(listener)
                .ifPresent(sct -> {
                    try {
                        listener.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e.getMessage());
                    }
                });
    }

    public static void transmit(byte[] dataFromClient, final Socket listener) {
        try (OutputStream outputStream = listener.getOutputStream()) {
            outputStream.write(dataFromClient);
        } catch (IOException e) {
            closeSocketListener(listener);
            throw new RuntimeException(e.getMessage());
        }
    }

    public static Packet receive(final Socket listener) {
        try (ObjectInputStream objStream = new ObjectInputStream(listener.getInputStream())) {
            try {
                return toPacket(objStream.readObject());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        } catch (IOException e) {
            closeSocketListener(listener);
            throw new RuntimeException(e.getMessage());
        }
    }

    /*private static Packet toPacket(Object obj){
        //TODO mapper
        return (Packet) obj;
    }*/
}
