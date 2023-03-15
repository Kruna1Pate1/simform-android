package com.krunal.demo.kotlinpractice.oop.enum

import com.krunal.demo.kotlinpractice.oop.enum.House.*

fun main() {
    var house: House = GRYFFINDOR
    house.value = "a"

    when (house) {
        GRYFFINDOR -> println(house)
        RAVENCLAW -> println(house)
        HUFFLEPUFF -> println(house)
        SLYTHERIN -> println(house)
    }

    house = SLYTHERIN
    println(house)
}
