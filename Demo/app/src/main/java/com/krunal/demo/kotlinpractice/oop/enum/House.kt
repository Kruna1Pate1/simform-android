package com.krunal.demo.kotlinpractice.oop.enum

import kotlin.random.Random

enum class House(var value: String) {

    GRYFFINDOR("Bravery"),
    RAVENCLAW("Intelligence"),
    HUFFLEPUFF("Empathy"),
    SLYTHERIN("Ambition");

    val canAdd: Boolean
        get() = Random.nextBoolean()

    override fun toString(): String {
        return "[$ordinal] $name $value"
    }
}
