/**
 * @author Towfiqul Islam
 * @since ১৭/৮/২২ ১:৪১ AM
 */

package com.cinjava.poc.seven;

import org.openmuc.jmbus.MBusMessage;

public class JMBusApplication {

    private static final String hexValueForDecodeOne = "68 1B 1B 68 08 0172 07 20 18 00 E6 1E 35 07 4C 00 00 00 0C 78 07 20 18 00 0C 16 69 02 00 00 96 16";
    private static final String hexValueForDecodeTwo = "68 AE AE 68 28 01 72 95 08 12 11 83 14 02 04 17 00 00 00 84 00 86 3B 23 00 00 00 84 0086 3C D1 01 00 00 84 40 86 3B 00 00 00 00 84 40 86 3C 00 00 00 00 85 00 5B 2B 4B AC 41 85 00 5F 20 D7 AC 41 85 40 5B 00 00 B8 42 85 40 5F 00 00 B8 42 85 00 3B 84 00 35 3F 85 40 3B 00 00 00 00 95 00 3B 95 CF B2 43 95 40 3B 00 00 00 00 85 00 2B 00 00 00 00 8540 2B 00 00 00 00 95 00 2B D3 9F 90 46 95 40 2B 00 00 00 00 04 6D 19 0F 8A 17 84 00 7C 01 43 F3 0D 00 00 84 40 7C 01 43 9D 01 00 00 84 00 7C 01 63 01 00 00 00 84 40 7C 01 63 01 00 00 00 0F 2F 16";
    private static final byte[] msgByte1 = new byte[]{(byte) 0x68, (byte) 0x40, (byte) 0x40, (byte) 0x68, (byte) 0x08, (byte) 0x00,
            (byte) 0x72, (byte) 0x71, (byte) 0x22, (byte) 0x23, (byte) 0x10, (byte) 0x65, (byte) 0x32, (byte) 0x18,
            (byte) 0x0E, (byte) 0x17, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x0C, (byte) 0x22, (byte) 0x22,
            (byte) 0x37, (byte) 0x00, (byte) 0x00, (byte) 0x04, (byte) 0x6D, (byte) 0x30, (byte) 0x10, (byte) 0xDA,
            (byte) 0x19, (byte) 0x06, (byte) 0xFD, (byte) 0x0C, (byte) 0x18, (byte) 0x00, (byte) 0x0E, (byte) 0x00,
            (byte) 0x22, (byte) 0x03, (byte) 0x0D, (byte) 0xFD, (byte) 0x0B, (byte) 0x05, (byte) 0x36, (byte) 0x31,
            (byte) 0x54, (byte) 0x54, (byte) 0x57, (byte) 0x32, (byte) 0x6C, (byte) 0xFF, (byte) 0xFF, (byte) 0x02,
            (byte) 0xFA, (byte) 0x3D, (byte) 0x00, (byte) 0x01, (byte) 0x01, (byte) 0x7C, (byte) 0x06, (byte) 0x54,
            (byte) 0x54, (byte) 0x41, (byte) 0x42, (byte) 0x20, (byte) 0x25, (byte) 0x61, (byte) 0x43,
            (byte) 0x16};
    private static final byte[] msgByte2 = new byte[]{(byte) 0x68, (byte) 0x40, (byte) 0x40, (byte) 0x68, (byte) 0x08, (byte) 0x00,
            (byte) 0x72, (byte) 0x71, (byte) 0x22, (byte) 0x23, (byte) 0x10, (byte) 0x65, (byte) 0x32, (byte) 0x18,
            (byte) 0x0E, (byte) 0x1E, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x0C, (byte) 0x22, (byte) 0x23,
            (byte) 0x37, (byte) 0x00, (byte) 0x00, (byte) 0x04, (byte) 0x6D, (byte) 0x36, (byte) 0x11, (byte) 0xDA,
            (byte) 0x19, (byte) 0x06, (byte) 0xFD, (byte) 0x0C, (byte) 0x18, (byte) 0x00, (byte) 0x0E, (byte) 0x00,
            (byte) 0x22, (byte) 0x03, (byte) 0x0D, (byte) 0xFD, (byte) 0x0B, (byte) 0x05, (byte) 0x36, (byte) 0x31,
            (byte) 0x54, (byte) 0x54, (byte) 0x57, (byte) 0x32, (byte) 0x6C, (byte) 0xFF, (byte) 0xFF, (byte) 0x02,
            (byte) 0xFA, (byte) 0x3D, (byte) 0x00, (byte) 0x01, (byte) 0x01, (byte) 0x7C, (byte) 0x06, (byte) 0x54,
            (byte) 0x54, (byte) 0x41, (byte) 0x42, (byte) 0x20, (byte) 0x25, (byte) 0x61, (byte) 0x52,
            (byte) 0x16};
    private static final byte[] msgByte3 = new byte[]{(byte) 0x68, (byte) 0x40, (byte) 0x40, (byte) 0x68, (byte) 0x08, (byte) 0x00,
            (byte) 0x72, (byte) 0x71, (byte) 0x22, (byte) 0x23, (byte) 0x10, (byte) 0x65, (byte) 0x32, (byte) 0x18,
            (byte) 0x0E, (byte) 0x1F, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x0C, (byte) 0x22, (byte) 0x23,
            (byte) 0x37, (byte) 0x00, (byte) 0x00, (byte) 0x04, (byte) 0x6D, (byte) 0x0D, (byte) 0x12, (byte) 0xDA,
            (byte) 0x19, (byte) 0x06, (byte) 0xFD, (byte) 0x0C, (byte) 0x18, (byte) 0x00, (byte) 0x0E, (byte) 0x00,
            (byte) 0x22, (byte) 0x03, (byte) 0x0D, (byte) 0xFD, (byte) 0x0B, (byte) 0x05, (byte) 0x36, (byte) 0x31,
            (byte) 0x54, (byte) 0x54, (byte) 0x57, (byte) 0x32, (byte) 0x6C, (byte) 0xFF, (byte) 0xFF, (byte) 0x02,
            (byte) 0xFA, (byte) 0x3D, (byte) 0x00, (byte) 0x01, (byte) 0x01, (byte) 0x7C, (byte) 0x06, (byte) 0x54,
            (byte) 0x54, (byte) 0x41, (byte) 0x42, (byte) 0x20, (byte) 0x25, (byte) 0x61, (byte) 0x2B,
            (byte) 0x16};

    public static void main(String[] args) {
        try {

//            MBusMessage result = MBusMessage.decode(hexValueForDecodeOne.getBytes(), hexValueForDecodeOne.getBytes().length);
//            MBusMessage result = MBusMessage.decode(hexValueForDecodeTwo.getBytes(), hexValueForDecodeTwo.getBytes().length);
            MBusMessage result = MBusMessage.decode(msgByte1, msgByte1.length);
            System.out.println(result);
            result = MBusMessage.decode(msgByte2, msgByte2.length);
            System.out.println(result);
            result = MBusMessage.decode(msgByte3, msgByte3.length);
            System.out.println(result);
        } catch (Exception exception) {
            System.out.println("exception.getMessage() " + exception.getMessage());
            exception.printStackTrace();
        }
    }
}
