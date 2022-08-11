/**
 * @author Towfiqul Islam
 * @since ১১/৮/২২ ১২:১৪ PM
 */

package com.cinjava.poc;

public class CInJavaApplication {

    public native void helloC();

    static {
        System.loadLibrary("HelloWorld");
    }

    public static void main(String[] args) {
        new CInJavaApplication().helloC();

    }
}
