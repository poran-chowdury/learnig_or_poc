/**
 * @author Towfiqul Islam
 * @since ১৬/৮/২২ ৩:২৯ PM
 */

package com.cinjava.poc.five;

public class HelloWorldJNI {

    static {
        System.loadLibrary("native");
    }

    public static void main(String[] args) {
        new HelloWorldJNI().sayHello();
    }

    // Declare a native method sayHello() that receives no arguments and returns void
    private native void sayHello();
}