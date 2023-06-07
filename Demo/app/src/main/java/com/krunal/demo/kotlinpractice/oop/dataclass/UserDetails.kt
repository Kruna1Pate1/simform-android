package com.krunal.demo.kotlinpractice.oop.dataclass

data class UserDetails(
    val name: String, var age: Int, val gender: String
) {
    lateinit var password: String

    operator fun component4(): String {
        return password
    }
}
