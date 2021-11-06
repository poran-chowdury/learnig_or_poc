package com.towfiq.completereference.example;

/**
 * @author TOWFIQUL ISLAM
 * @since 15/08/2021 23:45
 */

public class DemoObject {

    public static void main(String[] args) {

        Car carOne = new Car();

        carOne.seatNo = 4;
        carOne.color = "Red";
        carOne.wheel = "Decorated";

        carOne.printPropertyValues();

        Car carTwo = new Car();

        carTwo.seatNo = 8;
        carTwo.color = "White";
        carTwo.wheel = "Simple";

        carTwo.printPropertyValues();

    }
}
