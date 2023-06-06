package com.krunal.demo.kotlinpractice.oop.dataclass

fun main() {
    val userDetails = UserDetails("Krunal", 20, "Male")
//    println(userDetails.password) lateinit property password has not been initialized
    userDetails.password = "Krunal@123"

    val (name, age, _) = userDetails
    println("$name info: $userDetails")

    val userDetails2 = userDetails.copy()
    userDetails2.password = "Changed"
    println("$name info: $userDetails2")

    println(userDetails == userDetails2)

    changer(userDetails)
    println(userDetails)
}

fun changer(userDetails: UserDetails) {
    userDetails.age = 44
    println(userDetails)
}
