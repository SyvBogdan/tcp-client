public class Main {

    public static void main(String[] args) {


        int integer;
        integer = 360;


        byte[] bytes = new byte[4];
        for (int i = 3; i >= 0; i--) {
            bytes[i] = (byte) (integer >>> (i * 8));
        }


        System.out.println(Integer.toHexString((integer >>> 24)));
        System.out.println(Integer.toBinaryString((integer >>> 16)));
        System.out.println(Integer.toBinaryString((integer >>> 8)));

        byte[] bytes2 =  new byte[] {
                (byte)(integer >>> 24),
                (byte)(integer >>> 16),
                (byte)(integer >>> 8),
                (byte)integer};
    }
}
