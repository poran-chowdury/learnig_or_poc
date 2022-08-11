/**
 * @author Towfiqul Islam
 * @since ১১/৮/২২ ১২:২১ PM
 */

package com.cinjava.poc.service;

public class ProcessCProgram {
    //  Tell the VM to load our shared library.
    static {
        System.loadLibrary("ProcessWatcher Library");
    }

    //  Here are definitions for several simple native methods. Note that the data types
    //  we specify here may not be the same in the corresponding header file, due to
    //  size differences between Java's primitive types and the Macintosh's data types.
    //  The stubs file should handle thetranslation of the arguments and return types
    //  for us.

    //  This method invokes the Process Manager portion of the shared library.
    private native void nativeGetProcessInfo();

    //  The following methods retrieve various pieces of information
    //  about the current processes.
    private native int nativeGetNumElements();

    private native int nativeGetCurrentSize(int i);

    private native int nativeGetProcessSize(int i);

    private native int nativeGetProcessNameLength(int i);

    private native char
    nativeGetProcessNameChar(int i, int j);

    //  Our class constructor. It calls our native method
    //  that updates the Process Manager information.
    public NProcessWatcher() {
        this.nativeGetProcessInfo();
    }

    //  Update the Process Manager information on request.
    public void updateNow() {
        this.nativeGetProcessInfo();
    }

    //  Accessor methods used by our other Java classes to get process info. By hiding
    //  the actual calls to the native methods, we can change the implementation
    //  without affecting the classes calling in.
    public int getNumElements() {
        return (this.nativeGetNumElements());
    }

    public int getCurrentSize(int i) {
        return (this.nativeGetCurrentSize(i));
    }

    public int getProcessSize(int i) {
        return (this.nativeGetProcessSize(i));
    }

    public int getProcessNameLength(int i) {
        return (this.nativeGetProcessNameLength(i));
    }

    public char getProcessNameChar(int i, int j) {
        return (this.nativeGetProcessNameChar(i, j));
    }
}