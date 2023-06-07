package com.krunal.demo.kotlinpractice.oop.properties

import kotlin.random.Random

class Wizard(var name: Name) {

    var age = 20
    val school = "Hogwarts"
    var house: String? = null
        get() = "$school.$field"
        private set(value) {
            field = value?.replaceFirstChar { it.uppercase() }
        }

    fun changeHouse(newHouse: String): Boolean {
        return if (Random.nextBoolean()) {
            house = newHouse
            println("house changed to $house")
            true
        } else {
            println("no luck")
            false
        }
    }

    class Name(var fname: String, var lname: String)
}
