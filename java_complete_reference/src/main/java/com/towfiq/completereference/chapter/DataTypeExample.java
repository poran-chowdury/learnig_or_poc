package com.towfiq.completereference.chapter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author TOWFIQUL ISLAM
 * @since 05/08/2021 16:52
 */

public class DataTypeExample {

    private static int intType = 1;
    private static Integer intTypePrimitive = 1;

    private static byte byteType = 1;
    private static short shortType = 1;

    private static float floatType = 2.00F;
    private static Float floatTypePrimitive = 2.00F;

    private static double doubleType = 1.0;
    private static Double doubleTypePrimitive = 2.00;

    private static long longType = 2L;
    private static Long longTypePrimitive = 2L;

    private static BigDecimal bigDecimal = new BigDecimal(1);

    private static char charType = 'A';
    private static Character charTypePrimitive = 'A';

    private static String stringTypePrimitive = "ABC";

    private static boolean boolType = true;
    private static Boolean boolTypePrimitive = false;

    public static void main(String[] args) {

        System.out.println(intType);

        System.out.println(intTypePrimitive);

        System.out.println(bigDecimal.add(new BigDecimal(10.0)).doubleValue());

        // AUTO BOXING
        // int <-> Integer
        List<Integer> listOfInteger = new ArrayList<>();
        for (int i = 1; i < 50; i += 2)
            listOfInteger.add(i);

        System.out.println(listOfInteger);
    }

}

