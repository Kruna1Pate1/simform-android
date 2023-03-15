package com.krunal.demo.kotlinpractice.nullsafety

import kotlin.random.Random

fun main() {
    // Making variable nullable
    val num: Int?
    num = null // must be initialized
    println(num)

    // Checking for null in conditions
    val password: String? = "abcde"
    if (password != null && password.length >= 5) {
        println("valid password")
    }

    // Safe calls
    var str: String? = "Hello World!!"
    println(str?.length)
    str?.let {
        println("$it is not null")
    }
    str.checkNull()

    // Elvis operator
    str = null
    println(str ?: "null string")

    // not-null assertion operator
    str = if (Random.nextBoolean()) "" else null
    println(str!!.length)

    // Safe casts
    val number: Number = 5.5
//    println(number as Int) ClassCastException
    println(number as? Int ?: 5)
}

fun String?.checkNull() {
    if (this == null) {
        println("null")
    } else {
        println("not null")
    }
}
