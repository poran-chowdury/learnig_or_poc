///**
// * @author Towfiqul Islam
// * @since ১১/৮/২২ ১:৫৬ PM
// */
//
//package com.cinjava.poc.two;
//
//public class ProcessWatcher {
//    public void readProcessInfo() {
//        //  Retrieve the reference to our native class, maintained by the Updater.
//        NProcessWatcher tempNProcessWatcher =
//                theUpdaterThread.doGetNativeClass();
//
//        //  If the native class has not been instantiated yet, leave.
//        if (tempNProcessWatcher == null)
//            return;
//
//        //  Clear out the Vector.
//        theProcessList.removeAllElements();
//
//        //  Counters for traversing the array of processes,
//        //  and the individual process names.
//        int i = 0, j = 0;
//
//        //  So we know when we've retrieved all the process elements...
//        int max = tempNProcessWatcher.getNumElements();
//
//        //  Walk through the process elements one at a time.
//        for (i = 0; i < max; i++) {
//            //  Retrieve process size info from the native class.
//            int theCurRAM =
//                    tempNProcessWatcher.getCurrentSize(i);
//            int thePartition =
//                    tempNProcessWatcher.getProcessSize(i);
//
//            //  How long is the process name?
//            int theLength =
//                    tempNProcessWatcher.getProcessNameLength(i);
//
//            //  We'll hardcode a max name size. It must be at least as
//            //  large as the name size in the C code (32).
//            char[] theCharArray = new char[32];
//
//            //  Get the process name, one character at a time. Note the dual
//            //  counters used to get the char:
//            //    i is the current process array element, and
//            //    j + 1 is a character in the name (Str32) for that process.
//            //  Our character array starts its element numbering at 0, but the Str32 data
//            //  type (used in the C code) uses location 0 for the length of the string, so
//            //  we have to adjust the counter j to start one element beyond it.
//            for (j = 0; j < theLength; j++)
//                theCharArray[j] =
//                        tempNProcessWatcher.getProcessNameChar(i, j + 1);
//
//            //  Once the char array is filled in, create a Java String from it.
//            String theNameString = new String(theCharArray);
//
//            //  Create a new instance of the ProcessInfo class, fill in
//            //  the values, then place the reference to the object into the Vector.
//            ProcessInfo tempProcessInfo = new ProcessInfo();
//            tempProcessInfo.setCurRAM(theCurRAM);
//            tempProcessInfo.setPartition(thePartition);
//            tempProcessInfo.setName(theNameString);
//            theProcessList.addElement(tempProcessInfo);
//
//            //  Force changes to redraw.
//            repaint();
//        }
//    }
//}
