package com.krunal.demo.javaPractice;

import com.krunal.demo.notExist.javaPractice.DummyPackage;

public class OopDemo {

    public static void main(String[] args) {
        Animal animal = new Animal() {
            @Override
            public boolean canProtect() {
                return false;
            }
        };
        System.out.println(animal.speak());

        animal = new Dog();
        ((Dog) animal).setName("Tommy");
        System.out.println(animal.speak());

        animal = new Lion();
        System.out.println(animal.speak());

        DummyPackage dp = new DummyPackage();
    }
}

interface Animal {

    boolean canProtect();
    default String speak() {
        System.out.println("Default method");
        return "Hello world!!";
    }
}

abstract class Domestic implements Animal {

    abstract public String getName();

    @Override
    public String speak() {
        return "Hello!!";
    }
}

class Dog extends Domestic {

    private String name = "";

    @Override
    public boolean canProtect() {
        return true;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Lion implements Animal {

    @Override
    public boolean canProtect() {
        return false;
    }

    @Override
    public String speak() {
        return "Roar";
    }
}
