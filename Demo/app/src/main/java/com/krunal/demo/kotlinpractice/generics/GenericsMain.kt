package com.krunal.demo.kotlinpractice.generics

fun main() {

    val stack: MyStack<String> = MyStack()
    stack.push("Hello")
    stack.push("World")
    stack.push("!!")
    println(stack.items)

    type("")
    type(5)
    type(mutableListOf(4, 3, 2))
}

inline fun <reified T> type(e: T) {
    println("type: ${e!!::class.java}")
}
