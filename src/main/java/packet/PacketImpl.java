package packet;

import lombok.Data;

/**
 * Created by Boss on 22.04.2018.
 */
@Data
public class PacketImpl implements Packet {
    private byte[] data;
}
