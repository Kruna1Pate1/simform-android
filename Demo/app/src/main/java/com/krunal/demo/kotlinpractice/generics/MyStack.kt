package com.krunal.demo.kotlinpractice.generics

class MyStack<T> {

    var items: MutableList<T> = mutableListOf()

    fun push(element: T) {
        items.add(element)
    }

    fun pop(): T? {
        if (items.isEmpty()) return null
        return items.removeLast()
    }
}
