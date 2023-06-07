package com.krunal.demo.kotlinpractice.oop.properties

fun main() {
    val wizard = Wizard(Wizard.Name("Harry", "Potter"))
    println(wizard.school)
    println(wizard.house)
    while (!wizard.changeHouse("slytherin")) {
    }
    println(wizard.house)

    val sq = Square()
    sq.draw()
}

var Wizard.fullName: String
    get() = "${name.fname} ${name.lname}"
    set(value) {
        value.split(" ").run {
            name.fname = first()
            name.lname = last()
        }
    }
