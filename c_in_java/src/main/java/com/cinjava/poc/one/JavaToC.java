/**
 * @author Towfiqul Islam
 * @since ১১/৮/২২ ১২:১৪ PM
 */

package com.cinjava.poc.one;

public class JavaToC {

    public native void helloC();

    static {
        System.loadLibrary("HelloWorld");
    }

    public static void main(String[] args) {
        new JavaToC().helloC();
    }
}

