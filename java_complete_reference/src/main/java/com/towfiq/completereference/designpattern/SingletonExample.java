package com.towfiq.completereference.designpattern;


class SingletonExample {
    private static SingletonExample object;

    public static void getOutput(){
        System.out.println("This is singleton class");
    }

    synchronized public static SingletonExample getObject(){
        if(object == null){
            object = new SingletonExample();
        }
        return object;
    }
}
