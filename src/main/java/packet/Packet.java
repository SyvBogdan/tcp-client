package packet;

import java.io.*;

public interface Packet extends Serializable{

    static Packet toPacket(Object obj){
        //TODO mapper
        return (Packet) obj;
    }

    static byte[] toByteArray(Packet packet) {

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutput out = new ObjectOutputStream(bos)) {

            out.writeObject(packet);
            out.flush();

            return bos.toByteArray();
        } catch (IOException ex) {
            throw new RuntimeException("Can't serialize packet to ByteArray");
        }
    }

    byte [] getData();

}
