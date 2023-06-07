package com.krunal.demo.kotlinpractice.function;

public class ExtentionFun {

    public static void main(String[] args) {
        Person person = new Person();
        System.out.println("name: " + person.getLname());
        System.out.println("cap name: " + FunctionsKt.capName(person));
    }
}
