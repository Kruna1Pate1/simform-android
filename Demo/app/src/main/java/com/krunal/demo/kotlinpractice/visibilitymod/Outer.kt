package com.krunal.demo.kotlinpractice.visibilitymod

open class Outer {
    
    private val a = 1
    protected open val b = 2
    internal open val c = 3
    val d = 4  // public by default

    protected class Nested {
        val e: Int = 5
    }
}
