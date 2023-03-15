package com.krunal.demo.kotlinpractice.errorhandling

import java.util.UUID

object LoginRepo {

    private val userDetails = UserDetails2("Kruna1Pate1", "Krunal@123")

    @Throws
    fun login(user: String, password: String): UUID {
        if (user != userDetails.userName) {
            throw UserNotFoundException("User not found")
        }
        validate(password)
        println("Logged in successfully")
        return UUID.randomUUID()
    }

    fun validate(password: String): Boolean {
        if (password.isEmpty()) {
            throw IllegalArgumentException("Password can't be empty")
        } else if (password != userDetails.password) {
            throw InvalidPasswordException("Invalid password!")
        } else {
            println("Password is valid")
            return true
        }
    }
}
