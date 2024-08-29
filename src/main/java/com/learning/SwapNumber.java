package com.learning;

public class SwapNumber {
    public static void main(String ars[]){
        int num1 = 10;
        int num2 = 20;
        System.out.println("Before swap: "+num1+", "+num2);
        swapNumberMethod1(10, 20);
        swapNumberMethod2(10, 20);
    }

    private static void swapNumberMethod1(int num1, int num2) {
        int temp = num1;
        num1 = num2;
        num2 = temp;
        System.out.println("After swap: "+num1+", "+num2);
    }

    private static void swapNumberMethod2(int num1, int num2) {
        num1 = num1+num2;
        num2 = num1 - num2;
        num1 = num1 - num2;
        System.out.println("After swap: "+num1+", "+num2);
    }
}
