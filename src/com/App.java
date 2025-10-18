package com;
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        System.out.println("2 + 3 = " + add(2, 3));
        System.out.println("7 + 3 = " + add(7, 3));
        System.out.println("7 - 3 = " + subtract(7, 3));
    }

    // Adds two integers and returns the sum
    public static int add(int a, int b) {
        return a + b;
    }

    // Subtracts second integer from first and returns the difference
    public static int subtract(int a, int b) {
        return a - b;
    }
}
