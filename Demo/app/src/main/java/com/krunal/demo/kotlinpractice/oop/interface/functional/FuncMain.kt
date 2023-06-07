package com.krunal.demo.kotlinpractice.oop.`interface`.functional

fun main() {
    fun cap(str: String, mod: KModifier<String>) {
        println(mod(str))
    }

    val mod: KModifier<String> = KModifier { it.uppercase() }
    cap("Hello") { it.uppercase() }
    cap("Hello", mod = mod)
}
