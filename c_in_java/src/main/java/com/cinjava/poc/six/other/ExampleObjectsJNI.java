/**
 * @author Towfiqul Islam
 * @since ১৬/৮/২২ ৪:৫৭ PM
 */

package com.cinjava.poc.six.other;

public class ExampleObjectsJNI {

    static {
        System.loadLibrary("native");
    }

    public static void main(String[] args) {
        ExampleObjectsJNI instance = new ExampleObjectsJNI();
        UserData newUser = instance.createUser("John Doe", 450.67);
        instance.printUserData(newUser);
    }

    public native UserData createUser(String name, double balance);

    public native String printUserData(UserData user);
}
