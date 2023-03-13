package com.krunal.demo.javaPractice;

import java.util.Random;
import java.util.Scanner;

public class NumGuessGame {

    public static void main(String[] args) {
        new Game().start();
    }
}

class Game {

    private final Scanner sc = new Scanner(System.in);
    private final int num = new Random().nextInt(100);
    private int tries = 0;

    public void start() {
        guess:
        while (true) {
            System.out.print("Guess no: ");
            int guess = sc.nextInt();

            if (guess == num) {
                System.out.println("Guessed correct in " + tries + " try" );
                break guess;
            } else if (guess < num) {
                System.out.println("Try larger number");
                tries++;
            } else {
                System.out.println("Try smaller number");
                tries++;
            }
        }
    }
}
