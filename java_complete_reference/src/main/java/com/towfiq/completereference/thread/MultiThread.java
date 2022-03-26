package com.towfiq.completereference.thread;

/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 3/24/22 3:18 PM
 */

public class MultiThread {

    public static void main(String[] args) {
        System.out.println("===========Application Started===========");

        Printer printer = new Printer();

        MyThread myThread = new MyThread(printer);
        YourThread yourThread = new YourThread(printer);
        myThread.start();

//        try {
//            myThread.join();
//        } catch (InterruptedException ex) {
//            ex.printStackTrace();
//        }

        yourThread.start();
        System.out.println("===========Application Finished===========");
    }
}


class Printer {
    //    synchronized void printDocument(int numOfCopies, String docName) {
    void printDocument(int numOfCopies, String docName) {
        for (int i = 1; i <= numOfCopies; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(">> Printing " + docName + " : " + i);
        }
    }
}

class MyThread extends Thread {
    Printer pRef;

    MyThread(Printer p) {
        pRef = p;
    }

    //    @Override
//    public void run() {
//        pRef.printDocument(10, "MyThread");
//    }
    @Override
    public void run() {
        synchronized (pRef) {
            pRef.printDocument(10, "MyThread");
        }
    }
}

class YourThread extends Thread {
    Printer pRef;

    YourThread(Printer p) {
        pRef = p;
    }

//    @Override
//    public void run() {
//        pRef.printDocument(10, "YourThread");
//    }

    @Override
    public void run() {
        synchronized (pRef) {
            pRef.printDocument(10, "YourThread");
        }
    }
}
