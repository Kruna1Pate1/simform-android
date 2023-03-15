package com.krunal.demo.kotlinpractice.oop

import kotlin.math.max

fun main() {
    var animal: Animal = object : Animal(name = "Tom") {
        override fun canSecure(): Boolean {
            return false
        }
    }
    println(animal.speak())
    animal = Dog("dog")
    println(animal.speak())
    animal = Tommy()
    println(animal::class.java)
    println(animal.sleepTime)

    var derived: Base// = Derived("Hello", "World")
    derived = Derived("Hello", "World")
    println(derived.name)
    println(derived.lastName)
}

abstract class Animal(val name: String) {
    abstract fun canSecure(): Boolean

    open fun speak(): String {
        return "hello!!"
    }
}

open class Dog(name: String) : Animal(name) {
    override fun canSecure(): Boolean {
        return true
    }

    override fun speak(): String {
        return "bark"
    }
}

class Tommy : Dog("Tommy") {
    var sleepTime: Int = 0
        set(value) {
            max(value, 0)
        }
}

open class Base(val name: String) {

    init { println("Initializing a base class") }

    open val size: Int =
        name.length.also { println("Initializing size in the base class: $it") }
}

class Derived(
    name: String,
    val lastName: String,
) : Base(name.replaceFirstChar { it.uppercase() }.also { println("Argument for the base class: $it") }) {

    init { println("Initializing a derived class") }

    override val size: Int =
        (super.size + lastName.length).also { println("Initializing size in the derived class: $it") }
}
