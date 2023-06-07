package com.krunal.demo.javaPractice;

import java.util.Random;

public class Conditions {

    public static void main(String[] args) {
        if (false) {
            if (4 >= 5) {
                System.out.println("4 > 5");
            } else {
                System.out.println("5 > 4");
            }
        } else if (new Random().nextBoolean()) {
            System.out.println("In else-if");
        } else {
            System.out.println("In else");
        }

        isVowel('a');
        isVowel('E');
        isVowel('0');
    }

    public static void isVowel(char ch) {
        switch (ch) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                System.out.println("small case");
            case 'A':
            case 'E':
            case 'I':
            case 'O':
            case 'U':
                System.out.println("Vowel");
                break;
            default:
                System.out.println("Constant");
        }
    }
}
