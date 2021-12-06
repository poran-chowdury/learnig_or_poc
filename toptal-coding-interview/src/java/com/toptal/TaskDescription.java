package com.toptal;

/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 11/30/21 12:25 AM
 */

public class TaskDescription {
    public static void main(String[] args) {

        System.out.println(numberOfCarryOperations(123, 456)); // 0
        System.out.println(numberOfCarryOperations(555, 555)); // 3
        System.out.println(numberOfCarryOperations(900, 11)); // 0
        System.out.println(numberOfCarryOperations(145, 55)); // 2
        System.out.println(numberOfCarryOperations(0, 0)); // 0
        System.out.println(numberOfCarryOperations(1, 99999)); // 5
        System.out.println(numberOfCarryOperations(999045, 1055)); // 5
        System.out.println(numberOfCarryOperations(101, 809)); // 1
        System.out.println(numberOfCarryOperations(189, 209)); // 1

    }

    private static int numberOfCarryOperations(Integer number1, Integer number2) {
        String numberOne = new StringBuilder(number1.toString()).reverse().toString();
        String numberTwo = new StringBuilder(number2.toString()).reverse().toString();
        int result = 0;
        int carr = 0;
        int j;
        int minSize = Math.min(numberOne.length(), numberTwo.length());
        int maxSize = Math.max(numberOne.length(), numberTwo.length());
        String maxNum = numberOne.length() > numberTwo.length() ? numberOne : numberTwo;
        String minNum = numberOne.length() < numberTwo.length() ? numberOne : numberTwo;
        if(minSize == maxSize){
            maxNum = numberOne;
            minNum = numberTwo;
        }

        int num1 = 0;
        int num2 = 0;
        for (j = 0; j < maxSize; j++) {
            num1 = Integer.parseInt(String.valueOf(maxNum.charAt(j)));

            if (j < minSize) {
                num2 = Integer.parseInt(String.valueOf(minNum.charAt(j)));
            }
            int num = num1 + num2 + carr;
            if (num > 9) {
                carr = num / 10;
                result++;
            } else {
                carr = 0;
            }
            num1 = 0;
            num2 = 0;
        }

        return result;
    }
}
