package com.krunal.demo.kotlinpractice.controlflow

import kotlin.random.Random

fun main() {
    // if-else
    val a = 5
    val b = 7
    val max = if (a > b) a else b
    println("max $max")

    val name: String? = "KP"
    if (name != null) {
        println(name)
    }

    // when
    val char = 'a'
    val type = when (char) {
        'a', 'e', 'i', 'o', 'u' -> "small vowel"
        'A', 'E', 'I', 'O', 'U' -> "capital vowel"
        in 'a'..'z' -> "small constant"
        in 'A'..'Z' -> "capital constant"
        else -> "invalid char"
    }
    println("$char is $type")

    val password = "Krunal@123"
    when {
        password.isNullOrEmpty() -> println("password can't be empty")
        password.length < 5 -> println("password is too short")
        password.contains("Krunal") -> println("password should not contain name")
        else -> println("valid password")
    }

    // Loops
    for (i in 1 until 11) {
        print(i)
    }
    println()

    for (i in 10 downTo 0 step 3) {
        print(i)
    }
    println()

    var str = "abcba"
    while (str.isNotEmpty()) {
        str = str.removeRange(str.length - 1, str.length)
    }
    println("str $str")

    do {
        val num = Random.nextInt(50, 200)
        println("num: $num")
    } while (num < 100)

    // Break & Continue
    small@ for (i in 1..100) {
        if (i % 2 == 0) continue
        print(i)
        if (i > 10) break@small
    }

    // Custom double range
    for (i in 1.1..2.2) {
        println(i)
    }
}

operator fun Double.rangeTo(end: Double): DoubleClosedRange {

    return object : DoubleClosedRange {
        override val endInclusive: Double
            get() = end
        override val start: Double
            get() = this@rangeTo

        private var current = this@rangeTo

        override fun iterator(): Iterator<Double> {
            return object : Iterator<Double> {
                override fun hasNext(): Boolean {
                    return current <= end
                }

                override fun next(): Double {
                    current += 0.1
                    return current
                }
            }
        }
    }
}

interface DoubleClosedRange : ClosedRange<Double>, Iterable<Double>
