/**
 * @author Towfiqul Islam
 * @since ১৬/৮/২২ ৬:২১ PM
 */

package ch.company.poc.project;

public class MbusApplication {

//    private static final String hexValueForDecode = "68 1B 1B 68 08 0172 07 20 18 00 E6 1E 35 07 4C 00 00 00 0C 78 07 20 18 00 0C 16 69 02 00 00 96 16";
    private static final String hexValueForDecode = "68 AE AE 68 28 01 72 95 08 12 11 83 14 02 04 17 00 00 00 84 00 86 3B 23 00 00 00 84 0086 3C D1 01 00 00 84 40 86 3B 00 00 00 00 84 40 86 3C 00 00 00 00 85 00 5B 2B 4B AC 41 85 00 5F 20 D7 AC 41 85 40 5B 00 00 B8 42 85 40 5F 00 00 B8 42 85 00 3B 84 00 35 3F 85 40 3B 00 00 00 00 95 00 3B 95 CF B2 43 95 40 3B 00 00 00 00 85 00 2B 00 00 00 00 8540 2B 00 00 00 00 95 00 2B D3 9F 90 46 95 40 2B 00 00 00 00 04 6D 19 0F 8A 17 84 00 7C 01 43 F3 0D 00 00 84 40 7C 01 43 9D 01 00 00 84 00 7C 01 63 01 00 00 00 84 40 7C 01 63 01 00 00 00 0F 2F 16";

    static {
        System.loadLibrary("native");
    }

    public static void main(String[] args) {
        String result = new MbusApplication().decodeHexValue(hexValueForDecode);
        System.out.println("Inout => " + hexValueForDecode);
        System.out.println("result from C => " + result);
    }

    // Declare a native method sayHello() that receives no arguments and returns void
    public native String decodeHexValue(String data);
}
