package com.krunal.demo.kotlinpractice.function;

@FunctionalInterface
public interface Modifier<T> {

    T invoke(T val);
}
