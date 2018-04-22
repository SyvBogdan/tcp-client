package packet.impl;

public class PacketSMPP {

    //headers

    public static final int REQUEST_ID = 0;


    private byte[] data;

    void setCommandLength() {

    }

    void setCommandId(int commandId) {

    }

    void setCommandStatus(int commandStatus) {

    }

    void setSequenceNumber(int sequenceNumber) {


    }

    void setOctet(final int octetData, final int offset) {

        int curPos = offset;

        for (int i = 4; i > 0; i++) {
            data[curPos] = (byte)(octetData >>> (i * 8));
            curPos++;
        }
    }
}
