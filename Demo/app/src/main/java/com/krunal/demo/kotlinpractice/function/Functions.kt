package com.krunal.demo.kotlinpractice.function

fun main() {
    fun isEven(num: Int): Boolean {
        return num % 2 == 0
    }
    println("5 isEven ${isEven(5)}")

    // Default parameter
    fun greet(num: Int = 90, name: String = "") {
        println("Hello $name $num")
    }
    greet(name = "")

    // Single-expression functions
    fun square(n: Int) = n * n
    println(square(5))

    // Variable number of arguments (varargs)
    fun <T> printFormatted(delimiter: String = ",", vararg value: T) {
        value.forEach { print("$it$delimiter") }
    }
    printFormatted(", ", "a", "b", "c", "d")
    printFormatted("-", *arrayOf(1, 2, 3, 4, 5))

    // infix notation
    println("10 divisible by 5 ${10 isDivisible 5}")
    println("10 divisible by 3 ${10 isDivisible 3}")

    // Extensions
    val str: String = "Hello World!!"
    println(str.removeAll("l"))

    str.let {
        println(it)
    }
    println(str)

    // inline
    calculator(
        { s: String -> print(s) },
        { n1: Int, n2: Int -> (n1 * n2) }
    )

    // reified
    val status1: Int = hpCheck(12)
    println(status1)
    val status2: String = hpCheck(0)
    println(status2)

    printFormattedType(", ", "a", "b", "c", "d")
    printFormattedType("-", *arrayOf(1, 2, 3, 4, 5))
    println()

    // cross inline
    fun fooCaller() {
        foo {
            println("In foo")
            return@foo
        }
        println("foo completed")
    }
    fooCaller()
}

private fun String.removeAll(str: String): String {
    return this.replace(str, "")
}

infix fun Int.isDivisible(x: Int): Boolean {
    return this % x == 0
}

inline fun calculator(expression: (String) -> Unit, noinline value: (Int, Int) -> Int) {
    expression("5 * 5 = $value(5, 5)")
}

class Person {
    private val fname = "Ron"
    val lname = "Weasley"
}

fun Person.capName(): String {
//    return fname.uppercase()
    return lname.uppercase()
}

inline fun <reified T> hpCheck(hp: Int): T {
    return when (T::class) {
        Int::class -> {
            when (hp) {
                in 1..20 -> 20
                in 20..100 -> hp
                else -> hp
            } as T
        }
        String::class -> {
            when (hp) {
                0 -> "dead"
                100 -> "win"
                else -> "invalid"
            } as T
        }
        else -> "Invalid type" as T
    }
}

inline fun <reified T> printFormattedType(delimiter: String = ",", vararg value: T) {
    println("type: ${T::class.java}")
    value.forEach { print("$it$delimiter") }
}

inline fun foo(crossinline f: () -> Unit) {
    f()
    println("f() completed")
}

