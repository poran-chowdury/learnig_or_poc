/**
 * @author Towfiqul Islam
 * @since ১৬/৮/২২ ৬:২১ PM
 */

package ch.company.poc.project;

public class MbusApplication {

    private static final String hexValueForDecode = "68 1B 1B 68 08 0172 07 20 18 00 E6 1E 35 07 4C 00 00 00 0C 78 07 20 18 00 0C 16 69 02 00 00 96 16";

    static {
        System.loadLibrary("native");
    }

    public static void main(String[] args) {
        String result = new MbusApplication().decodeHexValue(hexValueForDecode);
        System.out.println(result);
    }

    // Declare a native method sayHello() that receives no arguments and returns void
    public native String decodeHexValue(String data);
}
