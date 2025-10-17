package com;
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        System.out.println("2 + 3 = " + add(2, 3));
        System.out.println("5 + 7 = " + add1(5, 7));
    }

    // Adds two integers and returns the sum
    public static int add(int a, int b) {
        return a + b;
    }
}
