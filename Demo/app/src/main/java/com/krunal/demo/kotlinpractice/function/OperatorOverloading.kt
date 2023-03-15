package com.krunal.demo.kotlinpractice.function

fun main() {
    var gr1 = Group(mutableListOf("one", "two", "three", "four"))
    val gr2 = Group(mutableListOf("three", "four", "five", "six"))

    gr1 - gr2
    println(gr1)

    gr1 + gr2
    println(gr1)

    gr1--
    println(gr1)

    +gr1
    println(gr1)

    println(gr1 == gr2)
}
