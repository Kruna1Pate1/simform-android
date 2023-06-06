package com.krunal.demo.kotlinpractice

fun main() {
    // Numbers
    val intNum = 1 // Int
    val longNum = 10_00_000_000 // Long
    val longNum2 = 1L // Long
    val byteNum: Byte = 1
    val float = 4.4F
    val double = 4.4

    // Unsigned Numbers
    val uByte: UByte = 1u
    val uShort: UShort = 1u
    val uLong: ULong = 1u
    val uLong2: ULong = 1UL

    // Boolean
    val boolVal = true
    println(boolVal.not())

    // Character
    val charA = 'A'
    val smile = "😄"
    println('\uF604')
    println(smile)

    // String
    val str = "Hello World!!"
    println(str.uppercase())
    val mulStr = """
        Line 1
        Line 2
            Line 3
    """.trimIndent()
    println(mulStr)
    val name = "Krunal"
    println("Hello $name, Welcome")

    // Type checks and casts
    val num: Number = 5
//    println(num + num) Unresolved reference `+`.
    if (num is Int) {
        println(num * num)
    }
    if ((num as Any) is String) {
        println("string")
    }
}
