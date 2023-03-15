package com.krunal.demo.kotlinpractice.visibilitymod

class Subclass : Outer() {

    //    override val a = 4 a is not visible
    override val b = 5   // 'b' is protected
    override val c = 7   // 'c' is internal

    init {
        Nested().e
    }
}
