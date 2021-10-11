package com.towfiq.completereference.example;

/**
 * @author TOWFIQUL ISLAM
 * @since 15/08/2021 23:49
 */

public class Car {

    public int seatNo;
    public String color;
    public String wheel;

    public void printPropertyValues() {

        String value = "Seat No " + this.seatNo + " ";
        value = value + "Color " + this.color + " ";
        value = value + "Wheel " + this.wheel + " ";

        System.out.println(value);

    }

}
