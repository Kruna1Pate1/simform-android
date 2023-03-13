package com.krunal.demo.javaPractice;

import androidx.annotation.NonNull;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class DifferentObjCreation {
    public static void main(String[] args) {
        System.out.println("Hello World");
        createObjs();
    }

    public static void createObjs() {
        Demo2 obj = new Demo2();

        try {
            obj = (Demo2) Class.forName("com.krunal.demo.javaPractice.Demo2").newInstance();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Demo2 obj2 = (Demo2) obj.clone();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("demo2"));
            Demo2 obj4 = (Demo2) is.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

class Demo2 implements Serializable, Cloneable {
    static {
        System.out.println("Class initialized");
    }

    {
        System.out.println("Class obj initialized");
    }

    Demo2() {
        System.out.println("Demo2 constructor");
    }

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}