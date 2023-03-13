package com.krunal.demo.javaPractice;

import java.util.Map;

import kotlin.Triple;

public class Operators {

    public static void main(String[] args) {
        int n1 = 5;
        int n2 = 10;

        // Arithmetic Operators
        int sum = n1 + n2;
        System.out.println("sum: " + sum);
        int sub = n1 - n2;
        System.out.println("sub: " + sub);
        int mul = n1 * n2;
        System.out.println("mul: " + mul);
        float div = ((float) n1) / n2;
        System.out.println("div: " + div);
        int mod = n2-- % ++n1;
        System.out.println("div: " + mod);

        // Relational Operators
        if (n1 == n2) {
            System.out.println("Both are same");
        } else if (n1 > n2) {
            System.out.println("N1 is larger");
        } else if (n1 <= n2) {
            System.out.println("N1 is same or smaller");
        }

        // Logical Operators
        if (echo(true) || echo(false)) {
            System.out.println("Inside ||");
        }

        if (!echo(true) && echo(false)) {
            System.out.println("Inside &&");
        }

        // Assignment Operators
        int num = n1;
        System.out.println("num: " + num);
        num += num;
        System.out.println("num: " + num);
        num /= --num;
        System.out.println("num: " + num);

        // Ternary Operator
        System.out.println(n1 > n2 ? "n1" : "n2");

        // instance of
        String s = "Hello";
        if (s instanceof String) {
            System.out.println("It's String");
        }

        // Round off error
        double a = 1.2;
        double b = 1.1;
        double c = a - b;
        System.out.println(c);
    }

    static boolean echo(boolean flag) {
        System.out.println("flag is " + flag);
        return flag;
    }
}
