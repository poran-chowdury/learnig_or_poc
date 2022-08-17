/**
 * @author Towfiqul Islam
 * @since ১৬/৮/২২ ২:৩৯ PM
 */

package com.cinjava.poc.four;

public class MyJNI {
    static {
        try{
            /* MyJNI.dll (Windows) or libMyJNI.so (Linux) */
            System.loadLibrary("MyJNI");
        } catch(java.lang.UnsatisfiedLinkError err){
            /* Error output when native binary not present */
            System.out.println("Unable to load MyJNI library");
            System.exit(0);
        }
    }
    private boolean z;
    private byte    b;
    private char    c;
    private short   s;
    private int     i;
    private long    j;
    private float   f;
    private double  d;
    private String  str;

    /* JNI simple interface take no arguments and no returns */
    private native void HelloWorld();
    /* JNI Call by Value */
    private native int CallC (boolean z,
                              byte    b,
                              char    c,
                              short   s,
                              int     i,
                              long    j,
                              float   f,
                              double  d,
                              String  str);
    /* JNI Call by Value */
    private native void SetMembers();

    public static void main(String[] args) {
        MyJNI myjni = new MyJNI();
        myjni.HelloWorld();
        System.out.println("Calling by Value");
        myjni.CallC (true,
                (byte)'J',
                'K',
                (short)10,
                100,
                (long)1000,
                (float)1.2,
                1.5,
                "Hello From Java!");
        myjni.z = true;
        myjni.b = (byte)'C';
        myjni.c = 'D';
        myjni.s = 20;
        myjni.i = 200;
        myjni.j = 2000;
        myjni.f = (float)2.2;
        myjni.d = 2.5;
        myjni.str = "Hello from Java!";
        System.out.println("Calling by Reference");
        myjni.SetMembers();
        System.out.println("End of Calling by Reference");
        System.out.println("Java Members (changed)");
        System.out.println("z (boolean) = " + myjni.z);
        System.out.println("b (byte) = " + (char)myjni.b);
        System.out.println("c (char) = " + (char)myjni.c);
        System.out.println("s (short) = " + (short)myjni.s);
        System.out.println("i (int) = " + myjni.i);
        System.out.println("l (long) = " + myjni.j);
        System.out.println("f (float) = " + myjni.f);
        System.out.println("d (double) = " + myjni.d);
        System.out.println("str (string) = " + myjni.str);
    }
}