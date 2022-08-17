/**
 * @author Towfiqul Islam
 * @since ১১/৮/২২ ২:০১ PM
 */

package com.cinjava.poc.three;

public class HelloWorld {
    private native void print();

    public static void main(String[] args) {
        new HelloWorld().print();
    }

    static {
        System.loadLibrary("HelloWorld");
    }
}
