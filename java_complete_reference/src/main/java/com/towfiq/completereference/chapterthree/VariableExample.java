package com.towfiq.completereference.chapterthree;

/**
 * @author TOWFIQUL ISLAM
 * @since 05/08/2021 16:52
 */

public class VariableExample {

    private static int numberOneIsGlobal = 1;

    public static void main(String[] args) {
        int numberTwoIsLocal = 2;
        System.out.println(numberOneIsGlobal);
        System.out.println(numberTwoIsLocal);
    }
}
