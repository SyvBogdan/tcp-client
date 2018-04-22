package packet;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Boss on 22.04.2018.
 */
@Data
@AllArgsConstructor
public class PacketImpl implements Packet {
    private byte[] data;
}
