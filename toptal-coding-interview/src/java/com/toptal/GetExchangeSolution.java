package com.toptal;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author TOWFIQUL ISLAM
 * @since 22/09/2021 02:15
 */

public class GetExchangeSolution {


    public static void main(String[] args) {
        System.out.println(Arrays.toString(getChange(5.0, 0.99)));          //should return [1,0,0,0,0,4]

        System.out.println(Arrays.toString(getChange(3.14, 1.99)));         //should return [0,1,1,0,0,1]
        System.out.println(Arrays.toString(getChange(4.0, 3.14)));          //should return [1,0,1,1,1,0]
        System.out.println(Arrays.toString(getChange(0.45, 0.34)));         //should return [1,0,1,0,0,0]

    }

    private static Integer[] getChange(Double money, Double price) {
        ArrayList<Integer> coins = new ArrayList<>();
        coins.add(1);
        coins.add(5);
        coins.add(10);
        coins.add(25);
        coins.add(50);
        coins.add(100);

        Integer mon = (int) (money * 100);
        Integer pri = (int) (price * 100);


        Integer[] result = new Integer[6];
        Integer rest = mon-pri;
        int i = 5;
        while (i > -1) {
            result[i] = rest / coins.get(i);
            rest = getRest(rest, coins.get(i));
//            System.err.println(rest);
            i--;
        }

        return result;
    }

    private static Integer getRest(Integer current, Integer subtraction) {
//        System.err.println(current);
//        System.err.println(subtraction);
        return current % subtraction;
    }
}
