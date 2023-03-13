package com.krunal.demo.javaPractice;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.Predicate;

public class Lambda {

    public static void main(String[] args) {
        // Body with a single expression
        Runnable greet = () -> System.out.println("Hello World!!");
        greet.run();

        // Return without parameter
        GetValue<Double> piValue = () -> 3.14;
        System.out.println(piValue.value());

        // Body with parameter
        Consumer<String> greetName = (String name) -> System.out.println("Hello " + name);
        Consumer<String> greetName2 = System.out::println;
        greetName.accept("Krunal");
        greetName2.accept("Hello Krunal!!");

        // Accept and return modified data
        Function<String, Integer> toInt = (String num) -> Integer.parseInt(num);
        Function<String, Integer> toInt2 = Integer::parseInt;
        System.out.println(toInt.apply("5"));
        System.out.println(toInt2.apply("5"));

        // Check condition
        Predicate<Integer> isEven = (Integer n) -> n % 2 == 0;
        Integer[] numArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Arrays.stream(numArr).filter(isEven).forEach(System.out::print);
        System.out.println();

        new Controller();
    }
}

@FunctionalInterface
interface GetValue<T> {

    T value();
}

class Util {

    Function<String, String> capitalize = (String str) -> str.toUpperCase();
    Runnable contentBreak = () -> System.out.println("**************************");

    public void randomValue(GetValue<Integer> getValue) {
        System.out.println("value: " + getValue.value());
    }
}

class Controller {

    final Util util = new Util();
    String name = "Demogorgon";

    Controller() {
        System.out.println(util.capitalize.apply(name));
        GetValue<Integer> random = () -> new Random().nextInt();
        util.randomValue(random);
        util.contentBreak.run();
    }
}
