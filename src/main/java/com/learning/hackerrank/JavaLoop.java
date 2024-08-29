package com.learning.hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JavaLoop {
    public static void main(String ars[]) {
        List<InputData> inputDataList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int totalQuery = scanner.nextInt();
        while (totalQuery > 0) {
            InputData input = new InputData();
            input.a = scanner.nextInt();
            input.b = scanner.nextInt();
            input.n = scanner.nextInt();
            inputDataList.add(input);
            totalQuery--;
        }
        new JavaLoop().problem1(inputDataList);
    }

    //(a+2^0*b),(a+2^0*b+2^1*b),....,(a+2^0*b+2^1*b+...+2^(n-1)*b)
    public void problem1(List<InputData> inputDataList) {
        for (InputData input : inputDataList) {
            calculateQuery(input);
            System.out.print("\n");
        }
    }

    private void calculateQuery(InputData input) {
        double result = input.a;
        for (int n = 0; n < input.n; n++) {
            result = result + Math.pow(2, Double.valueOf(n)) * input.b;
            System.out.print((int) result + " ");
        }
    }
}

class InputData {
    int a;
    int b;
    int n;
}

